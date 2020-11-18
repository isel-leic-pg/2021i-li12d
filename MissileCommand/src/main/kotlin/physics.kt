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
 * Represents coordinates variation in the arena.
 *
 */
data class Velocity(val dx: Double, val dy: Double)

fun computeVelocity(start: Location, end: Location): Velocity {
    val modulus = sqrt(start.x.pow(2) + start.y.pow(2))
    return Velocity((end.x - start.x) / modulus, (end.y - start.y) / modulus)
}

fun move(start: Location, velocity: Velocity) =
        Location(start.x + velocity.dx, start.y + velocity.dy)
