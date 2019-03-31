package uk.co.akm.lib.plot.course.impl


/**
 * Created by Thanos Mavroidis on 31/03/2019.
 */
class CylindricalTransforms(r: Double) : AbstractTransforms(r) {

    override fun fromLonToProjectionX(lon: Double): Double = r * lon

    override fun fromLatToProjectionY(lat: Double): Double = r * Math.tan(lat)
}