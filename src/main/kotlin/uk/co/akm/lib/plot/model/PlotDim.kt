package uk.co.akm.lib.plot.model

import uk.co.akm.lib.plot.functions.checkOrder
import uk.co.akm.lib.plot.functions.checkPositive

/**
 * Dimensional specifications of a plot. The pixel width and height are specified here, as well as the x and y ranges
 * of the space used for plotting.
 *
 * Created by Thanos Mavroidis on 05/01/2019.
 */
class PlotDim(val width: Int, val height: Int, val xMin: Double, val xMax: Double, val yMin: Double, val yMax: Double) {

    init {
        checkPositive(width, "Input pixel width ($width) must be greater than zero.")
        checkPositive(height, "Input pixel height ($height) must be greater than zero.")
        checkOrder(xMin, xMax, "xMax ($xMax) must be greater than xMin ($xMin).")
        checkOrder(yMin, yMax, "yMax ($yMax) must be greater than yMin ($yMin).")
    }
}