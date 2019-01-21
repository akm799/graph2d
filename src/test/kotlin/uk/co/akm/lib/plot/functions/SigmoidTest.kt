package uk.co.akm.lib.plot.functions

import org.junit.Test
import uk.co.akm.lib.plot.model.*
import uk.co.akm.lib.plot.testGraphPlot
import java.awt.Color

/**
 * Created by Thanos Mavroidis on 21/01/2019.
 */
class SigmoidTest {

    @Test
    fun shouldPlotSigmoids() {
        val dimensions = PlotDim(560, 280, -15.0, 15.0, 0.0, 10.0)
        val axesDim = AxesDim(0.0, 0.0, 1.0, 1.0, 0.2, 0.2)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))

        val a = 10.0
        val r = 0.5
        val c = 0.0
        val b = 0.0
        val f1 = { x: Double -> a/(1 + Math.exp(-r*(x - c))) + b }
        val f2 = { x: Double -> a/(1 + Math.exp( r*(x - c))) + b }

        val xStep = 0.01
        underTest.addFunctionPlot(f1, xStep, Color.RED)
        underTest.addFunctionPlot(f2, xStep, Color.GREEN)

        addSigmoidText(underTest)

        testGraphPlot(underTest, "sigmoids")
    }

    private fun addSigmoidText(underTest: Graph) {
        val redLine = arrayOf(arrayOf(-14.5, 6.64), arrayOf(-12.3, 6.64))
        underTest.addPlot(ColouredItem(Path(redLine), Color.RED))

        val greenLine = arrayOf(arrayOf(-14.5, 6.14), arrayOf(-12.3, 6.14))
        underTest.addPlot(ColouredItem(Path(greenLine), Color.GREEN))

        val fractionLine = arrayOf(arrayOf(-12.55, 8.65), arrayOf(-8.2, 8.65))

        underTest.addText(ColouredItem(TextItem("a", -10.7, 8.8, 12), Color.BLACK)) // Numerator
        underTest.addText(ColouredItem(TextItem("f(x) =", -14.5, 8.5, 12), Color.BLACK)) // LHS
        underTest.addText(ColouredItem(TextItem("+ b", -7.8, 8.51, 12), Color.BLACK)) // After fraction
        underTest.addPlot(ColouredItem(Path(fractionLine), Color.BLACK)) // Fraction line
        underTest.addText(ColouredItem(TextItem("-r(x - c)", -10.8, 8.2, 12), Color.BLACK)) // Exponents in denominator
        underTest.addText(ColouredItem(TextItem("1 + e", -12.5, 8.0, 12), Color.BLACK)) // Bases in denominator

        underTest.addText(ColouredItem(TextItem("a = 10,  c = 0,  b = 0", -12.0, 7.0, 12), Color.BLACK))
        underTest.addText(ColouredItem(TextItem("r =  0.5", -12.0, 6.5, 12), Color.BLACK))
        underTest.addText(ColouredItem(TextItem("r = -0.5", -12.0, 6.0, 12), Color.BLACK))

        underTest.addText(ColouredItem(TextItem("yMax = 10", 11.0, 9.5, 12), Color.BLACK))
        underTest.addText(ColouredItem(TextItem("xMax = 15", 11.0, 9.0, 12), Color.BLACK))

        underTest.addText(ColouredItem(TextItem("yMin =   0", -14.5, 1.0, 12), Color.BLACK))
        underTest.addText(ColouredItem(TextItem("xMin = -15", -14.5, 0.5, 12), Color.BLACK))
    }
}