import kotlin.math.pow
import kotlin.math.sqrt

/**
 *  Represents vectors in a plane (2D space).
 */
data class Vector2D(val x: Double, val y: Double)

operator fun Vector2D.plus(other: Vector2D) = Vector2D(this.x + other.x, this.y + other.y)

operator fun Vector2D.minus(other: Vector2D) = Vector2D(this.x - other.x, this.y - other.y)

fun Vector2D.magnitude() = sqrt(x.pow(2) + y.pow(2))

fun Vector2D.norm() = this / magnitude()

operator fun Vector2D.times(amplitude: Double) = Vector2D(x * amplitude, y * amplitude)

operator fun Vector2D.div(amplitude: Double) = Vector2D(x / amplitude, y / amplitude)

/**
 * Represents coordinates on the game arena.
 *
 * @property x  the horizontal coordinate
 * @property y  the vertical coordinate
 */
data class Location(val x: Double, val y: Double)

fun Location.toVector() = Vector2D(this.x, this.y)

fun Vector2D.toLocation() = Location(this.x, this.y)

/**
 * Represents coordinates variations in the arena.
 *
 * @property dx the horizontal coordinate variation
 * @property dy the vertical coordinate variation
 */
data class Velocity(val dx: Double, val dy: Double)

fun Velocity.toVector() = Vector2D(dx, dy)

fun Vector2D.toVelocity() = Velocity(x, y)

/**
 * Computes the distance between two locations.
 *
 * @param l1    The first location
 * @param l2    The second location
 * @return the distance (a Double) between the two locations
 */
fun distance(l1: Location, l2: Location) =
        (l1.toVector() - l2.toVector()).magnitude()

/**
 * Adds to [start] the displacement expressed by [velocity].
 *
 * @param start     the starting location
 * @param velocity  the displacement to be added (implicitly a velocity, in our model)
 * @return the new location
 */
fun add(start: Location, velocity: Velocity) =
        (start.toVector() + velocity.toVector()).toLocation()
