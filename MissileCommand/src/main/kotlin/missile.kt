/**
 * Represents enemy missiles.
 *
 * @property start      The location where the missile entered the arena
 * @property current    The current location of the missile
 * @property velocity   The missile's velocity
 * @property color      The missile's color
 */
data class Missile(
        val start: Location,
        val current: Location,
        val velocity: Velocity,
        val color: Int
)