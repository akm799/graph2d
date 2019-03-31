package uk.co.akm.lib.plot.course

import org.junit.Test

/**
 * Created by Thanos Mavroidis on 31/03/2019.
 */
class CylindricalCourseTest : CourseTest(ProjectionType.CYLINDRICAL) {

    @Test
    fun shouldPlotCourse() {
        plotCourse(80.0, 50.0, -77.5, 50.0, 77.5, 100)
    }
}