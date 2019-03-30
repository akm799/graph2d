package uk.co.akm.lib.plot.course

import org.junit.Test
import uk.co.akm.lib.plot.course.impl.CourseBuilderImpl
import uk.co.akm.lib.plot.course.impl.TransformsImpl
import uk.co.akm.lib.plot.model.*
import uk.co.akm.lib.plot.testGraphPlot
import java.awt.Color
import java.text.DecimalFormat

/**
 * Created by Thanos Mavroidis on 28/01/2019.
 */
class CourseTest {
    private val r = 1.0
    private val transforms = TransformsImpl(r)
    private val underTest: CourseBuilder = CourseBuilderImpl(r)

    private val latSpacingDeg = 10.0
    private val latSpacingWidthDeg = 4.0
    private val lonSpacingDeg = 20.0
    private val lonSpacingHeightDeg = 4.0

    private val pixelWidth = 480
    private val pixelHeight = 720

    private val axisMarkerTextSize = 8
    private val degFormat = DecimalFormat("0")

    @Test
    fun shouldPlotCourse() {
        plotCourse(80.0, 50.0, -77.5, 50.0, 77.5, 100)
    }

    private fun plotCourse(
            latMaxDeg: Double,
            startLatDeg: Double,
            startLonDeg: Double,
            endLatDeg: Double,
            endLonDeg: Double,
            n: Int) {

        if (Math.abs(startLatDeg) >= latMaxDeg || Math.abs(endLatDeg) >= latMaxDeg) {
            throw IllegalArgumentException("Input (start or end) latitude exceeds the maximum allowed value.")
        }

        val start = Coordinates(degToRad(startLatDeg), degToRad(startLonDeg))
        val end = Coordinates(degToRad(endLatDeg), degToRad(endLonDeg))
        plotCourse(latMaxDeg, start, end, n)
    }

    private fun plotCourse(latMaxDeg: Double, start: Coordinates, end: Coordinates, n: Int) {
        val lonSpacing = degToRad(lonSpacingDeg)
        val lonSpacingHeight = degToRad(lonSpacingHeightDeg)
        val axesDim = AxesDim(0.0, 0.0, lonSpacing, 0.0, lonSpacingHeight, 0.0)

        val latMax = degToRad(latMaxDeg)
        val yMax = transforms.fromLatToCylindricalY(latMax)
        val dimensions = PlotDim(pixelWidth, pixelHeight, -Math.PI, Math.PI, -yMax, yMax)
        val plot = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))

        addLongitudeMarkers(plot)
        addLatitudeMarkers(latMaxDeg, latSpacingDeg, latSpacingWidthDeg, plot)

        val points = underTest.buildCourse(start, end, n)
        plot.addPlot(ColouredItem(Path(points), Color.RED))
        testGraphPlot(plot, "red-course")
    }

    private fun addLatitudeMarkers(latMaxDeg: Double, spacingDeg: Double, widthDeg: Double, plot: Graph) {
        val yValues = calculateLatitudeMarkers(latMaxDeg, spacingDeg)
        val halfWidth = degToRad(widthDeg)/2
        val yLabelOffset = transforms.fromLatToCylindricalY(spacingDeg)/10

        val values = ArrayList<Array<Double>>()
        yValues.forEach {
            val y = it.first
            val label = it.second

            values.add(arrayOf(-halfWidth, y))
            values.add(arrayOf(halfWidth, y))
            if (y > 0.0) {
                plot.addText(ColouredItem(TextItem(label, 1.5 * halfWidth, y - yLabelOffset, axisMarkerTextSize), Color.BLACK))
            }

            values.add(arrayOf(-halfWidth, -y))
            values.add(arrayOf(halfWidth, -y))
            if (-y < 0.0) {
                plot.addText(ColouredItem(TextItem("-$label", -9 * halfWidth, -y - yLabelOffset, axisMarkerTextSize), Color.BLACK))
            }
        }

        val latMarkersPath =  Path(values.toTypedArray(), false)
        plot.addPlot(ColouredItem(latMarkersPath, Color.BLACK))
    }

    private fun calculateLatitudeMarkers(latMaxDeg: Double, spacingDeg: Double): Collection<Pair<Double, String>> {
        val result = ArrayList<Pair<Double, String>>()

        var latDeg = 0.0
        while (latDeg < latMaxDeg) {
            val y = transforms.fromLatToCylindricalY(degToRad(latDeg))
            val latLabel = formatDeg(latDeg)
            result.add(Pair(y, latLabel))
            latDeg += spacingDeg
        }

        return result
    }

    private fun addLongitudeMarkers(plot: Graph) {
        val yOffset = 2*transforms.fromLatToCylindricalY(degToRad(lonSpacingHeightDeg))

        var lonDeg = 0.0
        while (lonDeg < 180.0) {
            val x = transforms.fromLonToCylindricalX(degToRad(lonDeg))
            if (x != 0.0) {
                val lonLabel = formatDeg(lonDeg)
                plot.addText(ColouredItem(TextItem(lonLabel, x - yOffset/2, -1.2 * yOffset, axisMarkerTextSize), Color.BLACK))
                plot.addText(ColouredItem(TextItem("-$lonLabel", -x - yOffset, yOffset, axisMarkerTextSize), Color.BLACK))
            }

            lonDeg += lonSpacingDeg
        }
    }

    private fun degToRad(deg: Double) = Math.PI*deg/180

    private fun formatDeg(v: Double) = (degFormat.format(v) + "\u00b0")
}