package uk.co.akm.lib.plot.course

import org.junit.Test
import uk.co.akm.lib.plot.course.impl.CourseBuilderImpl
import uk.co.akm.lib.plot.model.*
import uk.co.akm.lib.plot.testGraphPlot
import java.awt.Color

/**
 * Created by Thanos Mavroidis on 28/01/2019.
 */
class CourseTest {
    private val underTest: CourseBuilder = CourseBuilderImpl(1.0)

    @Test
    fun shouldPlotCourse() {
        plotCourse(Coordinates(2.12*Math.PI/8, -Math.PI/8), Coordinates(2.12*Math.PI/8, Math.PI/8), 100)
    }

    private fun plotCourse(start: Coordinates, end: Coordinates, n: Int) {
        val width = 480
        val height = 720
        val latMax = Math.atan((height/width)*Math.PI)

        if (Math.abs(start.lat) >= latMax || Math.abs(end.lat) >= latMax) {
            throw IllegalArgumentException("Input latitude exceeds the maximum allowed value.")
        }

        val axesDim = AxesDim(0.0, 0.0)
        val dimensions = PlotDim(width, height, -Math.PI, Math.PI, -latMax, latMax)
        val plot = Graph(ColouredItem(dimensions, Color.WHITE), ColouredItem(axesDim, Color.BLACK))

        val points = underTest.buildCourse(start, end, n)
        plot.addPlot(ColouredItem(Path(points), Color.RED))
        testGraphPlot(plot, "red-course")
    }
}