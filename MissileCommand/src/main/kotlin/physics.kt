/**
 * Represents coordinates on the game arena.
 *
 * @property x  the horizontal coordinate
 * @property y  the vertical coordinate
 */
data class Location(val x: Double, val y: Double)

/**
 * Represents coordinates variation in the arena.
 *
 */
data class Velocity(val dx: Double, val dy: Double)