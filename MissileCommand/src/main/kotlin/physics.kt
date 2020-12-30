/**
 * Represents coordinates on the game arena.
 *
 * @property x  the horizontal coordinate
 * @property y  the vertical coordinate
 */
data class Location(val x: Double, val y: Double)

/**
 * Extension function that converts a [Location] to a [Vector2D]
 */
fun Location.toVector() = Vector2D(this.x, this.y)

/**
 * Extension function that converts a [Vector2D] to a [Location]
 */
fun Vector2D.toLocation() = Location(this.x, this.y)

/**
 * Represents coordinates variations in the arena.
 *
 * @property dx the horizontal coordinate variation
 * @property dy the vertical coordinate variation
 */
data class Velocity(val dx: Double, val dy: Double)

/**
 * Extension function that converts a [Velocity] to a [Vector2D]
 */
fun Velocity.toVector() = Vector2D(dx, dy)

/**
 * Extension function that converts a [Vector2D] to a [Velocity]
 */
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
