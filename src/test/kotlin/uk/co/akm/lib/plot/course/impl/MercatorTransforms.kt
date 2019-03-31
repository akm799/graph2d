package uk.co.akm.lib.plot.course.impl


/**
 * https://luckytoilet.wordpress.com/2010/11/07/notes-on-mercators-projection/
 *
 * Created by Thanos Mavroidis on 31/03/2019.
 */
class MercatorTransforms(r: Double) : AbstractTransforms(r) {

    override fun fromLonToProjectionX(lon: Double): Double = r * lon

    override fun fromLatToProjectionY(lat: Double): Double = r * Math.log(sec(lat) + Math.tan(lat))

    private fun sec(theta: Double) = 1/Math.cos(theta)
}