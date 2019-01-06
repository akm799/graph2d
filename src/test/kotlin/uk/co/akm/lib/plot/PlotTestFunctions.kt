package uk.co.akm.lib.plot

import org.junit.Assert
import uk.co.akm.lib.plot.functions.writeToFileAsPNG
import uk.co.akm.lib.plot.model.Graph
import uk.co.akm.lib.plot.model.PlotDim
import java.awt.image.RenderedImage
import java.io.File

/**
 * Created by Thanos Mavroidis on 05/01/2019.
 */

private val imageDir = "./src/test/images/"

fun testGraphPlot(underTest: Graph, fileName: String) {
    val filePath = "$imageDir$fileName.png"
    deletePreviousFile(filePath)

    val image = underTest.plot()
    assertImageSize(underTest.background.data, image)

    val file = writeToFileAsPNG(image, filePath)
    assertFileCreated(filePath, file)
}

private fun deletePreviousFile(filePath: String) {
    val existingFile = File(filePath)
    if (existingFile.exists()) {
        Assert.assertTrue(existingFile.delete())
    }

    Assert.assertFalse(existingFile.exists())
}

private fun assertImageSize(dimensions: PlotDim, image: RenderedImage) {
    Assert.assertEquals(dimensions.width, image.width)
    Assert.assertEquals(dimensions.height, image.height)
}

private fun assertFileCreated(filePath: String, file: File) {
    Assert.assertNotNull(file)
    Assert.assertTrue(file.absolutePath.endsWith(filePath))
    Assert.assertTrue(file.exists())
    Assert.assertTrue(file.length() > 0)
}