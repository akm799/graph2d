package uk.co.akm.lib.plot.course

/**
 * Created by Thanos Mavroidis on 28/01/2019.
 */
interface CourseBuilder {

    fun buildCourse(start: Coordinates, end: Coordinates, n: Int): Array<Array<Double>>
}