import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Represents coordinates on the game arena.
 *
 * @property x  the horizontal coordinate
 * @property y  the vertical coordinate
 */
data class Location(val x: Double, val y: Double)

/**
 * Represents coordinates variations in the arena.
 *
 * @property dx the horizontal coordinate variation
 * @property dy the vertical coordinate variation
 */
data class Velocity(val dx: Double, val dy: Double)

/**
 * Computes the distance between two locations.
 *
 * @param l1    The first location
 * @param l2    The second location
 * @return the distance (a Double) between the two locations
 */
fun distance(l1: Location, l2: Location) =
        sqrt((l1.x - l2.x).pow(2) + (l1.y - l2.y).pow(2))

/**
 * Adds to [start] the displacement expressed by [velocity].
 *
 * @param start     the starting location
 * @param velocity  the displacement to be added (implicitly a velocity, in our model)
 * @return the new location
 */
fun add(start: Location, velocity: Velocity) =
        Location(start.x + velocity.dx, start.y + velocity.dy)
