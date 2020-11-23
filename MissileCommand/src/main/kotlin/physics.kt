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


fun distance(l1: Location, l2: Location) = sqrt((l1.x - l2.x).pow(2) + (l1.y - l2.y).pow(2))

// TODO: we need to generalize Location and Velocity by creating the Vector2D type

// TODO: we need to generalize this
fun computeVelocity(start: Location, end: Location): Velocity {
    val magnitude = sqrt(start.x.pow(2) + start.y.pow(2))
    return Velocity((end.x - start.x) / magnitude, (end.y - start.y) / magnitude)
}


fun move(start: Location, velocity: Velocity) =
        Location(start.x + velocity.dx, start.y + velocity.dy)
