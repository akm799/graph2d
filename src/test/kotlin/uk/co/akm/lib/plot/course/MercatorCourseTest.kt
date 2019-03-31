package uk.co.akm.lib.plot.course

import org.junit.Test

/**
 * Created by Thanos Mavroidis on 31/03/2019.
 */
class MercatorCourseTest : CourseTest(ProjectionType.MERCATOR) {

    @Test
    fun shouldPlotCourse() {
        plotCourse(80.0, 50.0, -77.5, 50.0, 77.5, 100)
    }
}