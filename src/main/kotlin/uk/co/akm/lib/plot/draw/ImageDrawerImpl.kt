package uk.co.akm.lib.plot.draw

import uk.co.akm.lib.plot.functions.X_INDEX
import uk.co.akm.lib.plot.functions.Y_INDEX
import uk.co.akm.lib.plot.functions.buildPathsForAxes
import uk.co.akm.lib.plot.model.AxesDim
import uk.co.akm.lib.plot.model.ColouredItem
import uk.co.akm.lib.plot.model.Path
import uk.co.akm.lib.plot.model.PlotDim
import java.awt.Color
import java.awt.Graphics

/**
 * Created by Thanos Mavroidis on 11/01/2019.
 */
class ImageDrawerImpl(private val background: ColouredItem<PlotDim>) : ImageDrawer {
    private val plot = background.data
    private val xw = (plot.xMax - plot.xMin)/plot.width
    private val yw = (plot.yMax - plot.yMin)/plot.height

    private val noValue = -1
    private val buffer1 = arrayOf(noValue, noValue)
    private val buffer2 = arrayOf(noValue, noValue)

    override fun drawBackground(g: Graphics) {
        setColourIfRequires(background.colour, g)
        g.fillRect(0, 0, plot.width, plot.height)
    }

    override fun drawAxes(axes: ColouredItem<AxesDim>, g: Graphics) {
        setColourIfRequires(axes.colour, g)
        buildPathsForAxes(plot, axes).forEach { drawPath(it, g) }
    }

    override fun drawPath(path: ColouredItem<Path>, g: Graphics) {
        setColourIfRequires(path.colour, g)

        if (path.data.isContinuous) {
            drawContinuousPath(path.data, g)
        } else {
            drawNonContinuousPath(path.data, g)
        }
    }

    private fun drawContinuousPath(path: Path, g: Graphics) {
        for (i in 0 until path.points.size - 1) {
            drawLine(path.points[i], path.points[i + 1], g)
        }
    }

    private fun drawNonContinuousPath(path: Path, g: Graphics) {
        for (i in 0 until path.points.size - 1 step 2) {
            drawLine(path.points[i], path.points[i + 1], g)
        }
    }
    private fun drawLine(start: Array<Double>, end: Array<Double>, g: Graphics) {
        val startInRange = toScreenCoordinates(start, buffer1)
        val endInRange = toScreenCoordinates(end, buffer2)

        if (startInRange && endInRange) {
            g.drawLine(buffer1[X_INDEX], buffer1[Y_INDEX], buffer2[X_INDEX], buffer2[Y_INDEX])
        }
    }

    private fun setColourIfRequires(colour: Color, g: Graphics) {
        if (g.color != colour) {
            g.color = colour
        }
    }

    private fun toScreenCoordinates(coordinates: Array<Double>, screenCoordinates: Array<Int>): Boolean {
        val x = Math.round((coordinates[X_INDEX] - plot.xMin)/xw).toInt()
        val y = Math.round((coordinates[Y_INDEX] - plot.yMin)/yw).toInt()

        if (x < 0 || x > plot.width || y < 0 && y >= plot.height) {
            screenCoordinates[X_INDEX] = noValue
            screenCoordinates[Y_INDEX] = noValue

            return false
        }

        screenCoordinates[X_INDEX] = x
        screenCoordinates[Y_INDEX] = plot.height - y

        return true
    }
}