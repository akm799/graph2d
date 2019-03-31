package uk.co.akm.lib.plot.course.impl

import uk.co.akm.lib.plot.course.Transforms

/**
 * Created by Thanos Mavroidis on 31/03/2019.
 */


fun cylindricalInstance(r: Double): Transforms = CylindricalTransforms(r)

fun mercatorInstance(r: Double): Transforms = MercatorTransforms(r)