package uk.co.akm.lib.plot.model

/**
 * Plot axes specification. The coordinates of the x and y axes intercept are specified as well as the spacing between
 * and the size of the marks on these axes. Please note that a zero axis mark spacing or size means that marks on the
 * corresponding axis will not be drawn.
 *
 * Created by Thanos Mavroidis on 05/01/2019.
 */
class AxesDim(
        val xCentre: Double,
        val yCentre: Double,
        val xMarkSpacing: Double = 0.0,
        val yMarkSpacing: Double = 0.0,
        val xMarkSize: Double = 0.0,
        val yMarkSize: Double = 0.0
)