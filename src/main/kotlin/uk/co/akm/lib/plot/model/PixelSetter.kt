package uk.co.akm.lib.plot.model

import java.awt.Color

/**
 * Created by Thanos Mavroidis on 05/01/2019.
 */
interface PixelSetter {

    /**
     * Sets the specified pixel to have the specified colour. Please note that pixel (x=0, y=0) is the bottom left-hand
     * side pixel, in line with cartesian coordinate system and not the screen one.
     *
     * @param x the pixel x-coordinate (starting at zero from the left-hand side)
     * @param y the pixel y-coordinate (starting at zero from the bottom, not the top)
     * @param colour the pixel colour to set
     */
    fun setPixel(x: Int, y: Int, colour: Color)
}