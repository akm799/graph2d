package uk.co.akm.lib.plot.functions

import uk.co.akm.lib.plot.model.PlotDim

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