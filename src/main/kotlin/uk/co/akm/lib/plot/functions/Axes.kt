package uk.co.akm.lib.plot.functions

import uk.co.akm.lib.plot.model.*
import java.awt.Color

/**
 * Created by Thanos Mavroidis on 06/01/2019.
 */



fun buildPathsForAxes(dims: PlotDim, axes: ColouredItem<AxesDim>): Collection<ColouredItem<Path>> {
    val axesDim = axes.data
    val colour = axes.colour

    val paths = ArrayList<ColouredItem<Path>>()
    drawAxes(dims, axesDim, colour, paths)
    drawAxesMarks(dims, axesDim, colour, paths)

    return paths;
}

private fun drawAxes(dims: PlotDim, axes: AxesDim, colour: Color, paths: MutableCollection<ColouredItem<Path>>) {
    val points = generateEmptyPointsArray(4)

    var i = 0
    setPoint(i++, dims, dims.xMin, axes.yCentre, points)
    setPoint(i++, dims, dims.xMax, axes.yCentre, points)
    setPoint(i++, dims, axes.xCentre, dims.yMin, points)
    setPoint(i, dims, axes.xCentre, dims.yMax, points)

    paths.add(ColouredItem(Path(points, false), colour))
}

private fun drawAxesMarks(dims: PlotDim, axes: AxesDim, colour: Color, paths: MutableCollection<ColouredItem<Path>>) {
    drawXAxesMarks(dims, axes, colour, paths)
    drawYAxesMarks(dims, axes, colour, paths)
}

private fun drawXAxesMarks(dims: PlotDim, axes: AxesDim, colour: Color, paths: MutableCollection<ColouredItem<Path>>) {
    if (axes.xMarkSize > 0.0 && axes.xMarkSpacing > 0.0) {
        val pointsList = ArrayList<Array<Double>>()
        val xMarkEnd = axes.yCentre + axes.xMarkSize

        // Positive x-axis marks.
        var x = axes.xCentre + axes.xMarkSpacing
        while (x <= dims.xMax) {
            addPoint(dims, x, axes.yCentre, pointsList)
            addPoint(dims, x, xMarkEnd, pointsList)
            x += axes.xMarkSpacing
        }

        // Negative x-axis marks.
        x = axes.xCentre - axes.xMarkSpacing
        while (x >= dims.xMin) {
            addPoint(dims, x, axes.yCentre, pointsList)
            addPoint(dims, x, xMarkEnd, pointsList)
            x -= axes.xMarkSpacing
        }

        paths.add(ColouredItem(Path(pointsList.toTypedArray(), false), colour))
    }
}

private fun drawYAxesMarks(dims: PlotDim, axes: AxesDim, colour: Color, paths: MutableCollection<ColouredItem<Path>>) {
    if (axes.yMarkSize > 0.0 && axes.yMarkSpacing > 0.0) {
        val pointsList = ArrayList<Array<Double>>()
        val yMarksEnd = axes.xCentre + axes.yMarkSize

        // Positive y-axis marks.
        var y = axes.yCentre + axes.yMarkSpacing
        while (y <= dims.yMax) {
            addPoint(dims, axes.xCentre, y, pointsList)
            addPoint(dims, yMarksEnd, y, pointsList)
            y += axes.yMarkSpacing
        }

        // Negative y-axis marks.
        y = axes.yCentre - axes.yMarkSpacing
        while (y >= dims.yMin) {
            addPoint(dims, axes.xCentre, y, pointsList)
            addPoint(dims, yMarksEnd, y, pointsList)
            y -= axes.yMarkSpacing
        }

        paths.add(ColouredItem(Path(pointsList.toTypedArray(), false), colour))
    }
}