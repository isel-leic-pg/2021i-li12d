import pt.isel.canvas.RED
import kotlin.math.pow
import kotlin.math.sqrt

const val FOE_MISSILE_VELOCITY_MAGNITUDE = 2.0

/**
 * Represents enemy missiles.
 *
 * @property start      The location where the missile entered the arena
 * @property current    The missile's current location
 * @property velocity   The missile's velocity
 * @property color      The missile's color
 */
data class Missile(
        val start: Location,
        val current: Location,
        val velocity: Velocity = Velocity(0.0, 0.0),
        val color: Int = RED
)

/**
 * Computes the normalized vector specified by the difference between [start] and [end].
 *
 * @param start the start point
 * @param end   the end point
 * @return the normalized vector expressed as a [Velocity]
 */
private fun computeNormalizedVelocity(start: Location, end: Location): Velocity {
    val vector = Location(end.x - start.x, end.y - start.y)
    val magnitude = sqrt(vector.x.pow(2) + vector.y.pow(2))
    return Velocity(vector.x / magnitude, vector.y / magnitude)
}

/**
 * Creates a new missile with the specified constraints
 *
 * @param worldHeight   The width of the world
 * @param worldHeight   The height of the world
 * @param dmzMargin     The width of the demilitarized zone (where no missiles will fall)
 * @param magnitude     The magnitude of the missile's velocity vector
 * @return the newly created missile
 */
fun createMissile(worldWidth: Int, worldHeight: Int, dmzMargin: Int, magnitude: Double): Missile {
    val entry = Location((dmzMargin .. worldWidth - dmzMargin).random().toDouble(), 0.0)
    val target = Location((dmzMargin .. worldWidth - dmzMargin).random().toDouble(), worldHeight.toDouble())
    val normalized = computeNormalizedVelocity(entry, target)
    return Missile(entry, entry, Velocity(normalized.dx * magnitude, normalized.dy * magnitude))
}

/**
 * Moves the given missile.
 *
 * @param missile the missile to be moved.
 * @return the new missile instance.
 */
fun moveMissile(missile: Missile) = Missile(
        missile.start,
        add(missile.current, missile.velocity),
        missile.velocity
)