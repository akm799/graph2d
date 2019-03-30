package uk.co.akm.lib.plot.course.impl

import uk.co.akm.lib.plot.course.Coordinates
import uk.co.akm.lib.plot.course.Transforms

/**
 * Created by Thanos Mavroidis on 28/01/2019.
 */
class TransformsImpl(private val r: Double) : Transforms {

    override fun fromCoordinatesTo3dPoint(coordinates: Coordinates, point: Array<Double>) {
        val p = r * Math.cos(coordinates.lat)
        point[Transforms.X_INDEX] = p * Math.cos(coordinates.lon)
        point[Transforms.Y_INDEX] = r * Math.sin(coordinates.lat)
        point[Transforms.Z_INDEX] = p * Math.sin(coordinates.lon)
    }

    override fun from3dPointToCoordinates(point3d: Array<Double>, coordinates: Array<Double>) {
        val p = Math.sqrt(point3d[Transforms.X_INDEX]*point3d[Transforms.X_INDEX] + point3d[Transforms.Z_INDEX]*point3d[Transforms.Z_INDEX])
        coordinates[Coordinates.LAT_INDEX] = Math.atan(point3d[Transforms.Y_INDEX] / p)
        coordinates[Coordinates.LON_INDEX] = Math.asin(point3d[Transforms.Z_INDEX] / p)
    }

    override fun fromCoordinatesToCylindricalPoint(coordinates: Array<Double>, point2d: Array<Double>) {
        point2d[Transforms.X_INDEX] = fromLonToCylindricalX(coordinates[Coordinates.LON_INDEX])
        point2d[Transforms.Y_INDEX] = fromLatToCylindricalY(coordinates[Coordinates.LAT_INDEX])
    }

    override fun fromLonToCylindricalX(lon: Double): Double = r * lon

    override fun fromLatToCylindricalY(lat: Double): Double = r * Math.tan(lat)

    override fun fromCoordinatesToMercatorPoint(coordinates: Array<Double>, point2d: Array<Double>) {
        point2d[Transforms.X_INDEX] = fromLonToMercatorX(coordinates[Coordinates.LON_INDEX])
        point2d[Transforms.Y_INDEX] = fromLatToMercatorY(coordinates[Coordinates.LAT_INDEX])
    }

    // https://luckytoilet.wordpress.com/2010/11/07/notes-on-mercators-projection/
    override fun fromLonToMercatorX(lon: Double): Double = r * lon

    // https://luckytoilet.wordpress.com/2010/11/07/notes-on-mercators-projection/
    override fun fromLatToMercatorY(lat: Double): Double = r * (sec(lat) + Math.tan(lat))

    private fun sec(theta: Double) = 1/Math.cos(theta)
}