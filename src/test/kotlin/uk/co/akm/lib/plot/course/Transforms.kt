package uk.co.akm.lib.plot.course

/**
 * Created by Thanos Mavroidis on 28/01/2019.
 */
interface Transforms {

    companion object {
        const val X_INDEX = 0
        const val Y_INDEX = 1
        const val Z_INDEX = 2
    }

    fun fromCoordinatesTo3dPoint(coordinates: Coordinates, point: Array<Double>)

    fun from3dPointToCoordinates(point3d: Array<Double>, coordinates: Array<Double>)

    fun fromCoordinatesToMercatorPoint(coordinates: Array<Double>, point2d: Array<Double>)

    fun fromLonToMercatorX(lon: Double): Double

    fun fromLatToMercatorY(lat: Double): Double
}