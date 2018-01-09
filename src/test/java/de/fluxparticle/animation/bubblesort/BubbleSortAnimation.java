package de.fluxparticle.animation.bubblesort;

import de.fluxparticle.animation.AnimationQueue;
import de.fluxparticle.animation.Clip;
import de.fluxparticle.animation.NodeCreator;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URI;
import java.nio.file.Paths;
import java.util.List;

import static org.fxmisc.easybind.EasyBind.map;


/**
 * Created by sreinck on 09.02.16.
 */
public class BubbleSortAnimation extends Application {

    private MediaView mediaView;
    private Pane animationSide;

    private enum Part {
        INTRO,
        HEADLINE,
        CLIP,
        FADE_OUT,
        HEADLINE2,
        ENDING;
    }

    private MediaPlayer player;

    private int curPart = 0;

    private Clip clip;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        root.boundsInLocalProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("root: " + newValue);
        });

        {
            URI uri = Paths.get("src/test/resources/BubbleSort.m4v").toUri();
            Media backgroundVideo = new Media(uri.toString());

            player = new MediaPlayer(backgroundVideo);
            player.setOnEndOfMedia(() -> {
                player.setStartTime(player.getCurrentTime());
                player.stop();
            });
            player.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("player (currentTime): " + newValue);
            });
            player.statusProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("player (status): " + newValue);
            });

            mediaView = new MediaView(player);
//            mediaView.setScaleX(0.5);
//            mediaView.setScaleY(0.5);

            System.out.println("backgroundVideo: " + backgroundVideo.getWidth() + "x" + backgroundVideo.getHeight());
            System.out.println("mediaView: " + mediaView.getBoundsInLocal());

            mediaView.setVisible(false);

            root.getChildren().add(mediaView);
        }

        {
            animationSide = new Pane();
            animationSide.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

            animationSide.boundsInLocalProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("animationSide: " + newValue);
            });

            animationSide.setPrefWidth(1280);
            animationSide.setPrefHeight(720);

            {
                Pane pane = new Pane();

                {
                    AnimationQueue animationQueue = new AnimationQueue();
                    BubbleSortBox box = new BubbleSortBox(animationQueue);
                    BubbleSortAlgorithm algorithm = new BubbleSortAlgorithm(box);

                    Bounds bounds = box.getBounds();

                    algorithm.run();
                    clip = animationQueue.getClip();

                    pane.setPrefWidth(bounds.getWidth());
                    pane.setPrefHeight(bounds.getHeight());

                    {
                        Rectangle rectangle = new Rectangle(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
                        rectangle.setFill(Color.TRANSPARENT);

                        pane.getChildren().add(rectangle);
                    }

                    List<Node> nodes = animationQueue.getVisitedGroup(new NodeCreator());
                    pane.getChildren().addAll(nodes);

                    pane.translateXProperty().bind(map(root.widthProperty().asObject(), w -> (w - bounds.getWidth()) / 2));
                    pane.translateYProperty().bind(map(root.heightProperty().asObject(), h -> (h - bounds.getHeight()) / 2));

//                    System.out.println("box: " + bounds);
                    animationSide.getChildren().add(pane);
                }

                scale(pane, 2.0);
            }

            root.getChildren().add(animationSide);
        }

        System.out.println("Scene");

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bubble Sort Animation");
        primaryStage.show();

        scene.setOnMouseClicked(event -> next());
    }

    public void next() {
        System.out.println("next()");
        System.out.println(Part.values()[curPart]);
        if (playNextAnimation()) {
            curPart++;
        }
    }

    private boolean playNextAnimation() {
        switch (Part.values()[curPart]) {
            case INTRO:
                mediaView.setVisible(true);
                player.setStopTime(Duration.seconds(4));
                player.play();
                return true;
            case HEADLINE:
                player.setStopTime(Duration.seconds(6));
                player.play();
                return true;
            case CLIP:
                return clip.playNextSequence() == null;
            case FADE_OUT:
                FadeTransition fadeOutAnimation = new FadeTransition(Duration.seconds(1), animationSide);
                fadeOutAnimation.setToValue(0.0);
                fadeOutAnimation.play();
                return true;
            case HEADLINE2:
                player.setStopTime(Duration.seconds(8));
                player.play();
                return true;
            case ENDING:
                player.setStopTime(player.getMedia().getDuration());
                player.play();
                return true;
        }
        return true;
    }

    private static void scale(Node node, double factor) {
        node.setScaleX(factor);
        node.setScaleY(factor);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
