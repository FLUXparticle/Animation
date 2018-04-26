package de.fluxparticle.animation.bubblesort

import de.fluxparticle.animation.AnimationQueue
import de.fluxparticle.animation.ClipJvm
import de.fluxparticle.animation.NodeCreator
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
import org.fxmisc.easybind.EasyBind.map


/**
 * Created by sreinck on 09.02.16.
 */
class BubbleSortAnimation : Application() {

    private var animationSide: Pane? = null

    private var curPart = 0

    private var clip = ClipJvm()

    private enum class Part {
        CLIP,
        FADE_OUT,
    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val root = Pane()
        root.background = Background(BackgroundFill(Color.BLACK, null, null))

        root.boundsInLocalProperty().addListener { observable, oldValue, newValue -> println("root: " + newValue) }

        run {
            animationSide = Pane()
            animationSide!!.background = Background(BackgroundFill(Color.TRANSPARENT, null, null))

            animationSide!!.boundsInLocalProperty().addListener { observable, oldValue, newValue -> println("animationSide: " + newValue) }

            animationSide!!.prefWidth = 1280.0
            animationSide!!.prefHeight = 720.0

            run {
                val pane = Pane()

                run {
                    val animationQueue = AnimationQueue(clip)
                    val box = BubbleSortBox(animationQueue)
                    val bounds = box.bounds

                    bubbleSortAlgorithm(box)

                    pane.prefWidth = bounds.width
                    pane.prefHeight = bounds.height

                    run {
                        val rectangle = Rectangle(bounds.minX, bounds.minY, bounds.width, bounds.height)
                        rectangle.fill = Color.TRANSPARENT

                        pane.children.add(rectangle)
                    }

                    val nodes = animationQueue.getVisitedGroup(NodeCreator())
                    pane.children.addAll(nodes)

                    pane.translateXProperty().bind(map(root.widthProperty().asObject()) { w -> (w - bounds.width) / 2 })
                    pane.translateYProperty().bind(map(root.heightProperty().asObject()) { h -> (h - bounds.height) / 2 })

                    //                    System.out.println("box: " + bounds);
                    animationSide!!.children.add(pane)
                }

                scale(pane, 2.0)
            }

            root.children.add(animationSide)
        }

        println("Scene")

        val scene = Scene(root, 1280.0, 720.0)
        primaryStage.scene = scene
        primaryStage.title = "Bubble Sort Animation"
        primaryStage.show()

        scene.setOnMouseClicked { event -> next() }
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

fun main(args: Array<String>) {
    Application.launch(BubbleSortAnimation::class.java, *args)
}
