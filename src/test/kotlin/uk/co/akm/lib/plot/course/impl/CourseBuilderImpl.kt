package uk.co.akm.lib.plot.course.impl

import uk.co.akm.lib.plot.course.Coordinates
import uk.co.akm.lib.plot.course.CourseBuilder
import uk.co.akm.lib.plot.course.Transforms

/**
 * Created by Thanos Mavroidis on 28/01/2019.
 */
class CourseBuilderImpl(r: Double) : CourseBuilder {
    private val transforms: Transforms = TransformsImpl(r)

    private val point2d = Array<Double>(2, { 0.0 })
    private val point3d = Array<Double>(3, { 0.0 })

    override fun buildCourse(start: Coordinates, end: Coordinates, n: Int): Array<Array<Double>> {
        transforms.fromCoordinatesTo3dPoint(start, point3d)
        val x1 = point3d[Transforms.X_INDEX]
        val y1 = point3d[Transforms.Y_INDEX]
        val z1 = point3d[Transforms.Z_INDEX]

        transforms.fromCoordinatesTo3dPoint(end, point3d)
        val x2 = point3d[Transforms.X_INDEX]
        val y2 = point3d[Transforms.Y_INDEX]
        val z2 = point3d[Transforms.Z_INDEX]

        return buildCourse(x1, y1, z1, x2, y2, z2, n)
    }

    private fun buildCourse(x1: Double, y1: Double, z1: Double, x2: Double, y2: Double, z2: Double, n: Int): Array<Array<Double>> {
        val delta = computeDelta(x1, y1, z1, x2, y2, z2, n)
        val mercatorPoints = Array<Array<Double>>(n + 1, { arrayOf(0.0, 0.0) })
        for (i in 0..n) {
            point3d[Transforms.X_INDEX] = x1 + i*delta[Transforms.X_INDEX]
            point3d[Transforms.Y_INDEX] = y1 + i*delta[Transforms.Y_INDEX]
            point3d[Transforms.Z_INDEX] = z1 + i*delta[Transforms.Z_INDEX]
            transforms.from3dPointToCoordinates(point3d, point2d)
            transforms.fromCoordinatesToCylindricalPoint(point2d, mercatorPoints[i])
        }

        return mercatorPoints
    }

    // https://brilliant.org/wiki/3d-coordinate-geometry-equation-of-a-line/
    private fun computeDelta(x1: Double, y1: Double, z1: Double, x2: Double, y2: Double, z2: Double, n: Int): Array<Double> {
        val dx = (x2 - x1)
        val dy = (y2 - y1)
        val dz = (z2 - z1)

        val diffs = arrayOf(dx, dy, dz)
        val absDiffs = diffs.map { Math.abs(it) }
        val maxDiff = absDiffs.max()
        val index = absDiffs.indexOfFirst { it == maxDiff }

        val delta = arrayOf(0.0, 0.0, 0.0)

        when (index) {
            Transforms.X_INDEX -> {
                delta[Transforms.X_INDEX] = diffs[Transforms.X_INDEX]/n
                delta[Transforms.Y_INDEX] = (diffs[Transforms.Y_INDEX]/diffs[Transforms.X_INDEX])*delta[Transforms.X_INDEX]
                delta[Transforms.Z_INDEX] = (diffs[Transforms.Z_INDEX]/diffs[Transforms.X_INDEX])*delta[Transforms.X_INDEX]
            }

            Transforms.Y_INDEX -> {
                delta[Transforms.Y_INDEX] = diffs[Transforms.Y_INDEX]/n
                delta[Transforms.X_INDEX] = (diffs[Transforms.X_INDEX]/diffs[Transforms.Y_INDEX])*delta[Transforms.Y_INDEX]
                delta[Transforms.Z_INDEX] = (diffs[Transforms.Z_INDEX]/diffs[Transforms.Y_INDEX])*delta[Transforms.Y_INDEX]
            }

            Transforms.Z_INDEX -> {
                delta[Transforms.Z_INDEX] = diffs[Transforms.Z_INDEX]/n
                delta[Transforms.X_INDEX] = (diffs[Transforms.X_INDEX]/diffs[Transforms.Z_INDEX])*delta[Transforms.Z_INDEX]
                delta[Transforms.Y_INDEX] = (diffs[Transforms.Y_INDEX]/diffs[Transforms.Z_INDEX])*delta[Transforms.Z_INDEX]
            }

            else -> throw IllegalStateException("Invalid maximum difference index: $index")
        }

        return delta
    }
}