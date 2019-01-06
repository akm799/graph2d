package uk.co.akm.lib.plot.model


/**
 * A path defined by a set of points and intended to be plotted. If the path is continuous, then the points are joined
 * together by lines. If not, then each consecutive pair of points are joined together by lines. Consequently a non-continuous
 * path can only contain an even number of points.
 *
 * Created by Thanos Mavroidis on 05/01/2019.
 */
class Path(val points: Array<Array<Double>>, val isContinuous: Boolean = true) {

    init {
        if (points.size < 2) {
            val errorMessageEnd = if (points.size == 0) "it is empty" else "it has just 1"
            throw IllegalArgumentException("A path must contain a minimum of 2 points but $errorMessageEnd.")
        }

        if (!isContinuous && points.size%2 != 0) {
            throw IllegalArgumentException("A non-continuous path must contain an even number of points but is has ${points.size}.")
        }
    }
}