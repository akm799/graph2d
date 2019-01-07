package uk.co.akm.lib.plot.functions

import uk.co.akm.lib.plot.model.ColouredItem
import uk.co.akm.lib.plot.model.Path
import uk.co.akm.lib.plot.model.PlotDim
import java.awt.Color

/**
 * Created by Thanos Mavroidis on 05/01/2019.
 */


fun generateEmptyPointsArray(size: Int): Array<Array<Double>> = Array(size, { Array(N_DIM, { 0.0 }) })

fun addPoint(dims: PlotDim, x: Double, y: Double, points: MutableCollection<Array<Double>>) {
    if (isInsidePlot(dims, x, y)) {
        points.add(arrayOf(x, y))
    }
}

fun setPoint(index: Int, dims: PlotDim, x: Double, y: Double, points: Array<Array<Double>>) {
    if (isInsidePlot(dims, x, y)) {
        points[index][X_INDEX] = x
        points[index][Y_INDEX] = y
    }
}

fun buildPathForFunction(dims: PlotDim, f: (Double) -> Double, step: Double, colour: Color): ColouredItem<Path> {
    if (step <= 0) {
        throw IllegalArgumentException("Input function plot step ($step) must be greater than zero.")
    }

    val nPoints = Math.round((dims.xMax - dims.xMin)/step).toInt() + 1
    val pointsList = ArrayList<Array<Double>>(nPoints)
    addFunctionPoints(dims, f, step, pointsList)

    return ColouredItem(Path(pointsList.toTypedArray()), colour)
}

private fun addFunctionPoints(dims: PlotDim, f: (Double) -> Double, step: Double, pointsList: MutableCollection<Array<Double>>) {
    var x = dims.xMin
    while (x <= dims.xMax) {
        addPoint(dims, x, f(x), pointsList)
        x += step
    }
}