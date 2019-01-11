package uk.co.akm.lib.plot.functions

import uk.co.akm.lib.plot.draw.ImageDrawer
import uk.co.akm.lib.plot.draw.ImageDrawerImpl
import uk.co.akm.lib.plot.model.Graph
import uk.co.akm.lib.plot.model.PixelSetter
import uk.co.akm.lib.plot.model.impl.BufferedImagePixelSetter
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.awt.image.RenderedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Created by Thanos Mavroidis on 05/01/2019.
 */


fun plotGraph(graph: Graph): RenderedImage {
    val image = BufferedImage(graph.background.data.width, graph.background.data.height, BufferedImage.TYPE_INT_ARGB)
    val pixels = BufferedImagePixelSetter(image)
    plot(graph, pixels)

    return image
}

private fun plot(graph: Graph, pixels: PixelSetter) {
    setBackGroundPixels(graph.background, pixels)

    if (graph.axes != null) {
        setAxesPixels(graph.background.data, graph.axes, pixels)
    }

    graph.getPlots().forEach { setPathPixels(it, graph.background.data, pixels) }
}

fun plotGraph2(graph: Graph): RenderedImage {
    val image = BufferedImage(graph.background.data.width, graph.background.data.height, BufferedImage.TYPE_INT_ARGB)
    val g = image.graphics

    try {
        plot(graph, g)
    } finally {
        g.dispose()
    }

    return image
}

private fun plot(graph: Graph, g: Graphics) {
    val imageDrawer: ImageDrawer = ImageDrawerImpl(graph.background)

    imageDrawer.drawBackground(g)

    if (graph.axes != null) {
        imageDrawer.drawAxes(graph.axes, g)
    }

    graph.getPlots().forEach { imageDrawer.drawPath(it, g) }
}

fun writeToFileAsPNG(image: RenderedImage, filePath: String): File {
    val file = File(filePath)
    ImageIO.write(image, "png", file)

    return file
}