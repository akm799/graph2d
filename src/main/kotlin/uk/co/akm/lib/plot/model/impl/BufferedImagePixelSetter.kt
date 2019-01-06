package uk.co.akm.lib.plot.model.impl

import uk.co.akm.lib.plot.model.PixelSetter
import java.awt.Color
import java.awt.image.BufferedImage

/**
 * Created by Thanos Mavroidis on 05/01/2019.
 */
class BufferedImagePixelSetter(private val image: BufferedImage) : PixelSetter {
    private val heightMinusOne = image.height - 1

    override fun setPixel(x: Int, y: Int, colour: Color) = image.setRGB(x, heightMinusOne - y, colour.rgb)
}