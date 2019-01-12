package uk.co.akm.lib.plot.model

import org.junit.Test
import uk.co.akm.lib.plot.testGraphPlot
import java.awt.Color

/**
 * Created by Thanos Mavroidis on 07/01/2019.
 */
class FunctionTest {

    @Test
    fun shouldPlotFunction() {
        val dimensions = PlotDim(754, 240, -Math.PI, Math.PI, -1.0, 1.0)
        val axesDim = AxesDim(0.0, 0.0, Math.PI/8, 0.1, 0.05, Math.PI/80)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))

        val xStep = Math.PI/40
        val f = { x: Double -> Math.sin(x) }

        underTest.addFunctionPlot(f, xStep, Color.GREEN)

        testGraphPlot(underTest, "sin")
    }

    @Test
    fun shouldPlotFunctions() {
        val dimensions = PlotDim(754, 240, -Math.PI, Math.PI, -1.0, 1.0)
        val axesDim = AxesDim(0.0, 0.0, Math.PI/8, 0.1, 0.05, Math.PI/80)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))

        val xStep = Math.PI/40
        val f1 = { x: Double -> Math.cos(x) }
        val f2 = { x: Double -> Math.sin(x) }

        underTest.addFunctionPlot(f1, xStep, Color.RED)
        underTest.addFunctionPlot(f2, xStep, Color.GREEN)

        val redLine = arrayOf(arrayOf(-Math.PI + 0.15, 0.83), arrayOf(-Math.PI + 0.45, 0.83))
        underTest.addPlot(ColouredItem(Path(redLine), Color.RED))

        val greenLine = arrayOf(arrayOf(-Math.PI + 0.15, 0.63), arrayOf(-Math.PI + 0.45, 0.63))
        underTest.addPlot(ColouredItem(Path(greenLine), Color.GREEN))

        underTest.addText(ColouredItem(TextItem("cos(x)", -Math.PI + 0.5, 0.8, 12), Color.BLACK))
        underTest.addText(ColouredItem(TextItem("sin(x)", -Math.PI + 0.5, 0.6, 12), Color.BLACK))

        testGraphPlot(underTest, "trigs")
    }
}