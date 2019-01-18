package uk.co.akm.lib.plot.functions

import uk.co.akm.lib.plot.draw.ImageDrawer
import uk.co.akm.lib.plot.draw.ImageDrawerImpl
import uk.co.akm.lib.plot.model.Graph
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
    graph.getTexts().forEach { imageDrawer.drawText(it, g) }
}

fun writeToFileAsPNG(image: RenderedImage, filePath: String): File {
    val file = File(filePath)
    ImageIO.write(image, "png", file)

    return file
}