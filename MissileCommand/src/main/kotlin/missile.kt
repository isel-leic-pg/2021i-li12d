import pt.isel.canvas.GREEN
import pt.isel.canvas.RED

const val FOE_MISSILE_VELOCITY_MAGNITUDE = 1.6
const val DEFENDER_MISSILE_VELOCITY_MAGNITUDE = 10.0

/**
 * Represents enemy missiles.
 *
 * @property origin     The location from where the missile was launched
 * @property target     The location to where the missile is headed
 * @property current    The missile's current location
 * @property velocity   The missile's velocity
 * @property color      The missile's color
 */
data class Missile(
        val origin: Location,
        val target: Location,
        val current: Location,
        val velocity: Velocity = Velocity(0.0, 0.0),
        val color: Int
)

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
    val entry = Vector2D((dmzMargin .. worldWidth - dmzMargin).random().toDouble(), 0.0)
    val target = Vector2D((dmzMargin .. worldWidth - dmzMargin).random().toDouble(), worldHeight.toDouble())
    val velocity = ((target - entry).norm() * magnitude).toVelocity()
    return Missile(entry.toLocation(), target.toLocation(), entry.toLocation(), velocity, RED)
}

/**
 * Cretaes a new defender missile with the given properties
 *
 * @param origin    the missile origin (i.e. to location from where it was fired)
 * @param target    the missile's target (i.e. to location to which it was fired)
 * @param magnitude The magnitude of the missile's velocity vector
 */
fun createDefenderMissile(origin: Location, target: Location, magnitude: Double) =
    Missile(origin, target, origin, ((target.toVector() - origin.toVector()).norm() * magnitude).toVelocity(), GREEN)

/**
 * Moves the given missile.
 *
 * @param missile the missile to be moved.
 * @return the new missile instance.
 */
fun moveMissile(missile: Missile) = Missile(
        missile.origin,
        missile.target,
        add(missile.current, missile.velocity),
        missile.velocity,
        missile.color
)

/**
 * Checks whether the given missile has reached its target.
 *
 * @param missile   the missile to be checked
 * @return a boolean value indicating whether the missile has reched its target or not
 */
fun hasReachedTarget(missile: Missile) =
    if (missile.velocity.dy > 0) missile.current.y >= missile.target.y
    else missile.current.y <= missile.target.y