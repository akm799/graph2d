package uk.co.akm.lib.plot.model

import org.junit.Test
import uk.co.akm.lib.plot.testGraphPlot
import java.awt.Color

/**
 * Created by Thanos Mavroidis on 05/01/2019.
 */
class TextTest {

    @Test
    fun shouldPlotImageWithText() {
        val dimensions = PlotDim(240, 240, 0.0, 10.0, 0.0, 10.0)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE))
        underTest.addText(ColouredItem(TextItem("Red size 12", 1.0, 9.0, 12), Color.RED))
        underTest.addText(ColouredItem(TextItem("Green size 24", 3.0, 5.0, 24), Color.GREEN))
        underTest.addText(ColouredItem(TextItem("Blue size 10", 6.0, 1.0, 10), Color.BLUE))

        testGraphPlot(underTest, "text")
    }
}