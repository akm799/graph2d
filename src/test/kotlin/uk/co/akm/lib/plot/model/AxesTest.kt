package uk.co.akm.lib.plot.model

import org.junit.Test
import uk.co.akm.lib.plot.testGraphPlot
import java.awt.Color

/**
 * Created by Thanos Mavroidis on 07/01/2019.
 */
class AxesTest {

    @Test
    fun shouldPlotAxes() {
        val dimensions = PlotDim(240, 240, -100.0, 100.0, -100.0, 100.0)
        val axesDim = AxesDim(0.0, 0.0)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))
        testGraphPlot(underTest, "axes")
    }

    @Test
    fun shouldPlotAxesOfCentre() {
        val dimensions = PlotDim(240, 240, -20.0, 100.0, -20.0, 100.0)
        val axesDim = AxesDim(0.0, 0.0)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))
        testGraphPlot(underTest, "axes-of-centre")
    }

    @Test
    fun shouldPlotAxesWithXMarks() {
        val dimensions = PlotDim(240, 240, -100.0, 100.0, -100.0, 100.0)
        val axesDim = AxesDim(0.0, 0.0, xMarkSpacing = 10.0, xMarkSize = 2.0)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))
        testGraphPlot(underTest, "axes-x-marks")
    }

    @Test
    fun shouldPlotAxesWithYMarks() {
        val dimensions = PlotDim(240, 240, -100.0, 100.0, -100.0, 100.0)
        val axesDim = AxesDim(0.0, 0.0, yMarkSpacing = 10.0, yMarkSize = 2.0)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))
        testGraphPlot(underTest, "axes-y-marks")
    }

    @Test
    fun shouldPlotAxesWithXYMarks() {
        val dimensions = PlotDim(240, 240, -100.0, 100.0, -100.0, 100.0)
        val axesDim = AxesDim(0.0, 0.0, 10.0, 10.0, 2.0, 2.0)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))
        testGraphPlot(underTest, "axes-xy-marks")
    }

    @Test
    fun shouldPlotAxesOfCentreWithXYMarks() {
        val dimensions = PlotDim(240, 240, -20.0, 100.0, -20.0, 100.0)
        val axesDim = AxesDim(0.0, 0.0, 10.0, 10.0, 2.0, 2.0)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))
        testGraphPlot(underTest, "axes-of-centre-xy-marks")
    }
}