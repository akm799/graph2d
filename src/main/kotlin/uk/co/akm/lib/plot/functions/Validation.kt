package uk.co.akm.lib.plot.functions

import uk.co.akm.lib.plot.model.PlotDim

/**
 * Created by Thanos Mavroidis on 05/01/2019.
 */

fun checkPositive(number: Int, errorMessage: String) {
    if (number <= 0) {
        throw IllegalArgumentException(errorMessage)
    }
}

fun checkOrder(min: Double, max: Double, errorMessage: String) {
    if (min >= max) {
        throw IllegalArgumentException(errorMessage)
    }
}

fun isInsidePlot(dims: PlotDim, x: Int, y: Int): Boolean {
    return (x >= 0 && x < dims.width && y >= 0 && y < dims.height)
}

fun isInsidePlot(dims: PlotDim, x: Double, y: Double): Boolean {
    return (x >= dims.xMin && x <= dims.xMax && y >= dims.yMin && y <= dims.yMax)
}