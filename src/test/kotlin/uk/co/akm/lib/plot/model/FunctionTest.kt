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

    @Test
    fun shouldPlotSigmoids() {
        val dimensions = PlotDim(560, 280, -15.0, 15.0, 0.0, 10.0)
        val axesDim = AxesDim(0.0, 0.0, 1.0, 1.0, 0.2, 0.2)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))

        val a = 10.0
        val r = 0.5
        val c = 0.0
        val f1 = { x: Double -> a/(1 + Math.exp(-r*(x - c))) }
        val f2 = { x: Double -> a/(1 + Math.exp( r*(x - c))) }

        val xStep = 0.01
        underTest.addFunctionPlot(f1, xStep, Color.RED)
        underTest.addFunctionPlot(f2, xStep, Color.GREEN)

        val redLine = arrayOf(arrayOf(-14.5, 7.14), arrayOf(-12.3, 7.14))
        underTest.addPlot(ColouredItem(Path(redLine), Color.RED))

        val greenLine = arrayOf(arrayOf(-14.5, 6.64), arrayOf(-12.3, 6.64))
        underTest.addPlot(ColouredItem(Path(greenLine), Color.GREEN))

        underTest.addText(ColouredItem(TextItem("f(x) = a/[1 + exp(-r(x - c))]", -14.5, 8.5, 12), Color.BLACK))
        underTest.addText(ColouredItem(TextItem("a = 10,  c = 0", -12.0, 7.5, 12), Color.BLACK))
        underTest.addText(ColouredItem(TextItem("r =  0.5", -12.0, 7.0, 12), Color.BLACK))
        underTest.addText(ColouredItem(TextItem("r = -0.5", -12.0, 6.5, 12), Color.BLACK))

        underTest.addText(ColouredItem(TextItem("yMax = 10", 11.0, 9.5, 12), Color.BLACK))
        underTest.addText(ColouredItem(TextItem("xMax = 15", 11.0, 9.0, 12), Color.BLACK))

        underTest.addText(ColouredItem(TextItem("yMin =   0", -14.5, 1.0, 12), Color.BLACK))
        underTest.addText(ColouredItem(TextItem("xMin = -15", -14.5, 0.5, 12), Color.BLACK))

        testGraphPlot(underTest, "sigmoids")
    }
}