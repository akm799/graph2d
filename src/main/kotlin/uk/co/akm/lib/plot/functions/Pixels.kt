package uk.co.akm.lib.plot.functions

import uk.co.akm.lib.plot.model.*
import java.awt.Color

/**
 * Created by Thanos Mavroidis on 05/01/2019.
 */

fun setBackGroundPixels(background: ColouredItem<PlotDim>, pixels: PixelSetter) {
    for (i in 0 until background.data.width) {
        for (j in 0 until background.data.height) {
            pixels.setPixel(i, background.data.height - j - 1, background.colour)
        }
    }
}

fun setAxesPixels(dims: PlotDim, axes: ColouredItem<AxesDim>, pixels: PixelSetter) {
    buildPathsForAxes(dims, axes).forEach {
        setPathPixels(it, dims, pixels)
    }
}

fun setPathPixels(path: ColouredItem<Path>, plot: PlotDim, pixels: PixelSetter) {
    if (path.data.isContinuous) {
        setContinuousPathPixels(path, plot, pixels)
    } else {
        setNonContinuousPathPixels(path, plot, pixels)
    }
}

private fun setContinuousPathPixels(path: ColouredItem<Path>, plot: PlotDim, pixels: PixelSetter) {
    for (i in 0 until path.data.points.size - 1) {
        drawLine(path.data.points[i], path.data.points[i + 1], path.colour, plot, pixels)
    }
}

private fun setNonContinuousPathPixels(path: ColouredItem<Path>, plot: PlotDim, pixels: PixelSetter) {
    for (i in 0 until path.data.points.size - 1 step 2) {
        drawLine(path.data.points[i], path.data.points[i + 1], path.colour, plot, pixels)
    }
}

private fun drawLine(start: Array<Double>, end: Array<Double>, colour: Color, plot: PlotDim, pixels: PixelSetter) {
    if (start[X_INDEX] == end[X_INDEX]) {
        drawVerticalLine(start, end, colour, plot, pixels)
    } else {
        drawNonVerticalLine(start, end, colour, plot, pixels)
    }
}

private fun drawVerticalLine(start: Array<Double>, end: Array<Double>, colour: Color, plot: PlotDim, pixels: PixelSetter) {
    val xw = (plot.xMax - plot.xMin)/plot.width
    val yw = (plot.yMax - plot.yMin)/plot.height

    val y1 = start[Y_INDEX]
    val y2 = end[Y_INDEX]

    val xp = Math.round((start[X_INDEX] - plot.xMin)/xw).toInt()
    val yp1 = Math.round((y1 - plot.yMin)/yw).toInt()
    val yp2 = Math.round((y2 - plot.yMin)/yw).toInt()
    for (yp in Math.min(yp1, yp2)..Math.max(yp1, yp2)) {
        setPixelSafely(xp, yp, colour, plot, pixels)
    }
}

//Divide the plot space into pixel like grids, find the line between the 2 points and mark the grids/pixels intercepted by the line.
private fun drawNonVerticalLine(start: Array<Double>, end: Array<Double>, colour: Color, plot: PlotDim, pixels: PixelSetter) {
    val xw = (plot.xMax - plot.xMin)/plot.width
    val yw = (plot.yMax - plot.yMin)/plot.height

    val x1 = start[X_INDEX]
    val y1 = start[Y_INDEX]
    val x2 = end[X_INDEX]
    val y2 = end[Y_INDEX]

    val a = (y2 - y1)/(x2 - x1)
    val b = y1 - a*x1

    val xMin = Math.min(x1, x2)
    val xMax = Math.max(x1, x2)
    val iMin = Math.round((xMin - plot.xMin)/xw) + 1
    val iMax = Math.round((xMax - plot.xMin)/xw)

    if (iMin != iMax) {
        for (xp in iMin.toInt()..iMax.toInt()) { // Set the pixels between the start and end points.
            val x = plot.xMin + xp*xw
            val y = a*x + b
            val yp = Math.round((y - plot.yMin)/yw).toInt()
            setPixelSafely(xp,     yp, colour, plot, pixels)
            setPixelSafely(xp - 1, yp, colour, plot, pixels)
        }
    }

    // Set the start point pixel
    val xp1 = Math.round((x1 - plot.xMin)/xw).toInt()
    val yp1 = Math.round((y1 - plot.yMin)/yw).toInt()
    setPixelSafely(xp1, yp1, colour, plot, pixels)

    // Set the end point pixel
    val xp2 = Math.round((x2 - plot.xMin)/xw).toInt()
    val yp2 = Math.round((y2 - plot.yMin)/yw).toInt()
    setPixelSafely(xp2, yp2, colour, plot, pixels)
}

private fun setPixelSafely(x: Int, y: Int, colour: Color, plot: PlotDim, pixels: PixelSetter) {
    if (isInsidePlot(plot, x, y)) {
        pixels.setPixel(x, y, colour)
    }
}