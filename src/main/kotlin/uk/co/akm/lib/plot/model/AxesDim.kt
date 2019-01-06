package uk.co.akm.lib.plot.model

/**
 * Plot axes specification. The coordinates of the x and y axes intercept are specified as well as the spacing between
 * and the size of the marks on these axes.
 *
 * Created by Thanos Mavroidis on 05/01/2019.
 */
class AxesDim(
        val xCentre: Double,
        val yCentre: Double,
        val xMarkSpacing: Double,
        val yMarkSpacing: Double,
        val xMarkSize: Double,
        val yMarkSize: Double
)