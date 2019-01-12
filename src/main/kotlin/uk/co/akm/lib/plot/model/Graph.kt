package uk.co.akm.lib.plot.model

import uk.co.akm.lib.plot.functions.buildPathForFunction
import uk.co.akm.lib.plot.functions.plotGraph
import java.awt.Color
import java.awt.image.RenderedImage

/**
 * Definition of a graph to be plotted.
 *
 * Created by Thanos Mavroidis on 05/01/2019.
 */
class Graph(val background: ColouredItem<PlotDim>, val axes: ColouredItem<AxesDim>? = null) {
    private val plots = ArrayList<ColouredItem<Path>>()
    private val texts = ArrayList<ColouredItem<TextItem>>()

    fun addPlot(plot: ColouredItem<Path>) = plots.add(plot)

    fun addPlots(plots: Collection<ColouredItem<Path>>) = this.plots.addAll(plots)

    fun addFunctionPlot(f: (Double) -> Double, step: Double, colour: Color) {
        plots.add(buildPathForFunction(background.data, f, step, colour))
    }

    fun addText(text: ColouredItem<TextItem>) = texts.add(text)

    fun getPlots(): Iterator<ColouredItem<Path>> = plots.iterator()

    fun getTexts(): Iterator<ColouredItem<TextItem>> = texts.iterator()

    fun clear() {
        plots.clear()
        texts.clear()
    }

    /**
     * Returns the image of the plotted graph.
     */
    fun plot(): RenderedImage = plotGraph(this)
}