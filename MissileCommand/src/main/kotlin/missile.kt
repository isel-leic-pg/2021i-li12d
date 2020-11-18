import pt.isel.canvas.RED

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
