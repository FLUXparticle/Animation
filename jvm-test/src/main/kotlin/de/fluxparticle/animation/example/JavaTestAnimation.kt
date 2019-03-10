package de.fluxparticle.animation.example

import de.fluxparticle.animation.AnimationQueue
import de.fluxparticle.animation.ClipJvm
import de.fluxparticle.animation.JavaNodeCreator
import javafx.animation.FadeTransition
import javafx.application.Application
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import javafx.util.Duration
import org.fxmisc.easybind.EasyBind

/**
 * Created by sreinck on 10.05.18.
 */
abstract class JavaTestAnimation : Application() {

    protected abstract val title: String

    protected abstract val algorithm: Algorithm

    private lateinit var animationSide: Pane

    private var curPart = 0

    private var clip = ClipJvm()

    private enum class Part {
        CLIP,
        FADE_OUT,
    }

    override fun start(primaryStage: Stage) {
        val root = Pane()
//        root.background = Background(BackgroundFill(Color.BLACK, null, null))

        root.boundsInLocalProperty().addListener { _, _, newValue -> println("root: $newValue") }

        run {
            animationSide = Pane()
            animationSide.background = Background(BackgroundFill(Color.TRANSPARENT, null, null))

            animationSide.boundsInLocalProperty().addListener { _, _, newValue -> println("animationSide: $newValue") }

            animationSide.prefWidth = 1280.0
            animationSide.prefHeight = 720.0

            run {
                val pane = Pane()

                run {
                    val animationQueue = AnimationQueue(clip)
                    val box = algorithm.render(animationQueue)
                    val bounds = box.bounds

                    pane.prefWidth = bounds.width
                    pane.prefHeight = bounds.height

                    run {
                        val rectangle = Rectangle(bounds.minX, bounds.minY, bounds.width, bounds.height)
                        rectangle.fill = Color.TRANSPARENT

                        pane.children.add(rectangle)
                    }

                    val nodes = animationQueue.getVisitedGroup(JavaNodeCreator())
                    pane.children.addAll(nodes)

                    pane.translateXProperty().bind(EasyBind.map(root.widthProperty().asObject()) { w -> (w - bounds.width) / 2 })
                    pane.translateYProperty().bind(EasyBind.map(root.heightProperty().asObject()) { h -> (h - bounds.height) / 2 })

                    //                    System.out.println("box: " + bounds);
                    animationSide.children.add(pane)
                }

                scale(pane, 2.0)
            }

            root.children.add(animationSide)
        }

        println("Scene")

        val scene = Scene(root, 1280.0, 720.0)
        primaryStage.scene = scene
        primaryStage.title = title
        primaryStage.show()

        scene.setOnMouseClicked { _ -> next() }
    }

    operator fun next() {
        println("next()")
        println(Part.values()[curPart])
        if (playNextAnimation()) {
            curPart++
        }
    }

    private fun playNextAnimation(): Boolean {
        return when (Part.values()[curPart]) {
            Part.CLIP -> clip.playNextSequence() == null
            Part.FADE_OUT -> {
                val fadeOutAnimation = FadeTransition(Duration.seconds(1.0), animationSide)
                fadeOutAnimation.toValue = 0.0
                fadeOutAnimation.play()
                true
            }
        }
    }

}

private fun scale(node: Node, factor: Double) {
    node.scaleX = factor
    node.scaleY = factor
}
