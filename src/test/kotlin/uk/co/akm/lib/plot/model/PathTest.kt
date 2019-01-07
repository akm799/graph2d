package uk.co.akm.lib.plot.model

import org.junit.Test
import uk.co.akm.lib.plot.functions.*
import uk.co.akm.lib.plot.testGraphPlot
import java.awt.Color

/**
 * Created by Thanos Mavroidis on 05/01/2019.
 */
class PathTest {

    @Test
    fun shouldPlotTriangle() {
        val dimensions = PlotDim(240, 240, 0.0, 100.0, 0.0, 100.0)
        val axesDim = AxesDim(0.0, 0.0, 10.0, 10.0, 2.0, 2.0)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))

        val points = generateEmptyPointsArray(4)
        points[0][X_INDEX] = 10.0
        points[0][Y_INDEX] = 10.0
        points[1][X_INDEX] = 50.0
        points[1][Y_INDEX] = 50.0
        points[2][X_INDEX] = 90.0
        points[2][Y_INDEX] = 10.0
        points[3][X_INDEX] = 10.0
        points[3][Y_INDEX] = 10.0
        underTest.addPlot(ColouredItem(Path(points), Color.GREEN))

        testGraphPlot(underTest, "green-triangle")
    }

    @Test
    fun shouldPlotSquare() {
        val dimensions = PlotDim(240, 240, 0.0, 100.0, 0.0, 100.0)
        val axesDim = AxesDim(0.0, 0.0, 10.0, 10.0, 2.0, 2.0)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))

        val points = generateEmptyPointsArray(5)
        points[0][X_INDEX] = 10.0
        points[0][Y_INDEX] = 10.0
        points[1][X_INDEX] = 10.0
        points[1][Y_INDEX] = 90.0
        points[2][X_INDEX] = 90.0
        points[2][Y_INDEX] = 90.0
        points[3][X_INDEX] = 90.0
        points[3][Y_INDEX] = 10.0
        points[4][X_INDEX] = 10.0
        points[4][Y_INDEX] = 10.0
        underTest.addPlot(ColouredItem(Path(points), Color.BLUE))

        testGraphPlot(underTest, "blue-square")
    }

    @Test
    fun shouldPlotTriangleAndSquare() {
        val dimensions = PlotDim(240, 240, 0.0, 100.0, 0.0, 100.0)
        val axesDim = AxesDim(0.0, 0.0, 10.0, 10.0, 2.0, 2.0)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))

        val squarePoints = generateEmptyPointsArray(5)
        squarePoints[0][X_INDEX] = 10.0
        squarePoints[0][Y_INDEX] = 10.0
        squarePoints[1][X_INDEX] = 10.0
        squarePoints[1][Y_INDEX] = 90.0
        squarePoints[2][X_INDEX] = 90.0
        squarePoints[2][Y_INDEX] = 90.0
        squarePoints[3][X_INDEX] = 90.0
        squarePoints[3][Y_INDEX] = 10.0
        squarePoints[4][X_INDEX] = 10.0
        squarePoints[4][Y_INDEX] = 10.0
        val squarePath = ColouredItem(Path(squarePoints), Color.GREEN)

        val trianglePoints = generateEmptyPointsArray(4)
        trianglePoints[0][X_INDEX] = 20.0
        trianglePoints[0][Y_INDEX] = 20.0
        trianglePoints[1][X_INDEX] = 50.0
        trianglePoints[1][Y_INDEX] = 80.0
        trianglePoints[2][X_INDEX] = 80.0
        trianglePoints[2][Y_INDEX] = 20.0
        trianglePoints[3][X_INDEX] = 20.0
        trianglePoints[3][Y_INDEX] = 20.0
        val trianglePath = ColouredItem(Path(trianglePoints), Color.RED)

        val plots = arrayListOf(trianglePath, squarePath)
        underTest.addPlots(plots)

        testGraphPlot(underTest, "square-and-triangle")
    }

    @Test
    fun shouldPlotMarks() {
        val dimensions = PlotDim(240, 240, 0.0, 100.0, 0.0, 100.0)
        val axesDim = AxesDim(0.0, 0.0, 10.0, 10.0, 2.0, 2.0)

        val underTest = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))

        val marks = generateEmptyPointsArray(8)

        // Central vertical line
        marks[0][X_INDEX] = 50.0
        marks[0][Y_INDEX] = 10.0
        marks[1][X_INDEX] = 50.0
        marks[1][Y_INDEX] = 90.0

        // Bottom horizontal mark
        marks[2][X_INDEX] = 40.0
        marks[2][Y_INDEX] = 20.0
        marks[3][X_INDEX] = 60.0
        marks[3][Y_INDEX] = 20.0

        // Middle horizontal mark
        marks[4][X_INDEX] = 40.0
        marks[4][Y_INDEX] = 50.0
        marks[5][X_INDEX] = 60.0
        marks[5][Y_INDEX] = 50.0

        // Top horizontal mark
        marks[6][X_INDEX] = 40.0
        marks[6][Y_INDEX] = 80.0
        marks[7][X_INDEX] = 60.0
        marks[7][Y_INDEX] = 80.0

        underTest.addPlot(ColouredItem(Path(marks, false), Color.BLACK)) // Non-continuous path.

        testGraphPlot(underTest, "marks")
    }
}