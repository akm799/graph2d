package uk.co.akm.lib.plot.course

import org.junit.Test
import uk.co.akm.lib.plot.course.impl.CourseBuilderImpl
import uk.co.akm.lib.plot.course.impl.TransformsImpl
import uk.co.akm.lib.plot.model.*
import uk.co.akm.lib.plot.testGraphPlot
import java.awt.Color

/**
 * Created by Thanos Mavroidis on 28/01/2019.
 */
class CourseTest {
    private val r = 1.0
    private val transforms = TransformsImpl(r)
    private val underTest: CourseBuilder = CourseBuilderImpl(r)

    private val latSpacingDeg = 10.0
    private val latSpacingWidthDeg = 4.0
    private val lonSpacingDeg = 10.0
    private val lonSpacingHeightDeg = 1.0

    private val pixelWidth = 480
    private val pixelHeight = 720

    @Test
    fun shouldPlotCourse() {
        plotCourse(49.0, -23.5, 49.0, 23.5, 100)
    }

    private fun plotCourse(
            startLatDeg: Double,
            startLonDeg: Double,
            endLatDeg: Double,
            endLonDeg: Double,
            n: Int) {

        val start = Coordinates(degToRad(startLatDeg), degToRad(startLonDeg))
        val end = Coordinates(degToRad(endLatDeg), degToRad(endLonDeg))
        plotCourse(start, end, n)
    }

    private fun plotCourse(start: Coordinates, end: Coordinates, n: Int) {
        val latMax = Math.atan((pixelHeight / pixelWidth)*Math.PI)

        if (Math.abs(start.lat) >= latMax || Math.abs(end.lat) >= latMax) {
            throw IllegalArgumentException("Input latitude exceeds the maximum allowed value.")
        }

        val lonSpacing = degToRad(lonSpacingDeg)
        val lonSpacingHeight = degToRad(lonSpacingHeightDeg)
        val axesDim = AxesDim(0.0, 0.0, lonSpacing, 0.0, lonSpacingHeight, 0.0)

        val dimensions = PlotDim(pixelWidth, pixelHeight, -Math.PI, Math.PI, -latMax, latMax)
        val plot = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))

        val latMarkers = buildLatitudeMarkers(latMax, latSpacingDeg, latSpacingWidthDeg)
        plot.addPlot(ColouredItem(latMarkers, Color.BLACK))

        val points = underTest.buildCourse(start, end, n)
        plot.addPlot(ColouredItem(Path(points), Color.RED))
        testGraphPlot(plot, "red-course")
    }

    private fun buildLatitudeMarkers(latMax: Double, spacingDeg: Double, widthDeg: Double): Path {
        val yValues = calculateLatitudeMarkers(latMax, spacingDeg)
        val halfWidth = degToRad(widthDeg)/2

        val values = ArrayList<Array<Double>>()
        yValues.forEach {
            values.add(arrayOf(-halfWidth, it))
            values.add(arrayOf(halfWidth, it))

            values.add(arrayOf(-halfWidth, -it))
            values.add(arrayOf(halfWidth, -it))
        }

        return Path(values.toTypedArray(), false)
    }

    private fun calculateLatitudeMarkers(latMax: Double, spacingDeg: Double): Collection<Double> {
        val mercator = arrayOf(0.0, 0.0)
        val coordinates = arrayOf(0.0, 0.0)

        var lat = 0.0
        val result = ArrayList<Double>()
        val latSpacing = degToRad(spacingDeg)
        while (lat < latMax) {
            coordinates[Coordinates.LAT_INDEX] = lat
            transforms.fromCoordinatesToMercatorPoint(coordinates, mercator)
            result.add(mercator[Transforms.Y_INDEX])
            lat += latSpacing
        }

        return result
    }

    private fun degToRad(deg: Double) = Math.PI*deg/180
}