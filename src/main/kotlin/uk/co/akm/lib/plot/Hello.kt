package uk.co.akm.lib.plot

import uk.co.akm.lib.plot.functions.X_INDEX
import uk.co.akm.lib.plot.functions.Y_INDEX
import uk.co.akm.lib.plot.functions.generateEmptyPointsArray


fun main(args: Array<String>) {
    println("Hello, World")

    val points = generateEmptyPointsArray(5)
    points[2][X_INDEX] = 1.0
    points[2][Y_INDEX] = 2.0
    points.forEach {
        it.forEach { print(it); print(" ") }
        println()
    }
}

