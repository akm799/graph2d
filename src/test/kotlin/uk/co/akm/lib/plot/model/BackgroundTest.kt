package uk.co.akm.lib.plot.model

import org.junit.Test
import uk.co.akm.lib.plot.testGraphPlot
import java.awt.Color

/**
 * Created by Thanos Mavroidis on 05/01/2019.
 */
class BackgroundTest {

    @Test
    fun shouldPlotImageWithBackground() {
        val dimensions = PlotDim(240, 240, -1.0, 10.0, -1.0, 10.0)
        val axesDim = AxesDim(0.0, 0.0, 1.0, 1.0, 0.2, 0.2)

        val underTest = Graph(ColouredItem(dimensions, Color.RED), ColouredItem(axesDim, Color.BLACK))
        testGraphPlot(underTest, "red-background")
    }
}