package uk.co.akm.lib.plot.course.impl

import uk.co.akm.lib.plot.course.Coordinates
import uk.co.akm.lib.plot.course.Transforms

/**
 * Created by Thanos Mavroidis on 31/03/2019.
 */
abstract class AbstractTransforms(protected val r: Double) : Transforms {

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

    override fun fromCoordinatesToProjectionPoint(coordinates: Array<Double>, point2d: Array<Double>) {
        point2d[Transforms.X_INDEX] = fromLonToProjectionX(coordinates[Coordinates.LON_INDEX])
        point2d[Transforms.Y_INDEX] = fromLatToProjectionY(coordinates[Coordinates.LAT_INDEX])
    }
}