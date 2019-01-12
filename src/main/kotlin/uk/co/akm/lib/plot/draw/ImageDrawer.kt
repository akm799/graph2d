package uk.co.akm.lib.plot.draw

import uk.co.akm.lib.plot.model.AxesDim
import uk.co.akm.lib.plot.model.ColouredItem
import uk.co.akm.lib.plot.model.Path
import uk.co.akm.lib.plot.model.TextItem
import java.awt.Graphics

/**
 * Created by Thanos Mavroidis on 11/01/2019.
 */
interface ImageDrawer {

    fun drawBackground(g: Graphics)

    fun drawAxes(axes: ColouredItem<AxesDim>, g: Graphics)

    fun drawPath(path: ColouredItem<Path>, g: Graphics)

    fun drawText(text: ColouredItem<TextItem>, g: Graphics)
}