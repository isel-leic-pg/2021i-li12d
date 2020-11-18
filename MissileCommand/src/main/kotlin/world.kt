
const val WORLD_WIDTH = 800
const val WORLD_HEIGHT = 600

/**
 * Representation od the game world
 */
data class World(
        val missile: Missile,
        val explosion: Explosion? = null
)

/**
 * Computes the new world based on the given one.
 *
 * @param   world   The current [World] instance
 * @return  The new [World] instance
 */
fun doStep(world: World): World {

    val newExplosion: Explosion? = when {
        world.explosion == null -> null
        world.explosion.rate == GROWTH_RATE -> expandUntil(world.explosion, MAX_RADIUS)
        else -> contractUntilZero(world.explosion)
    }

    val movedMissile = Missile(
            world.missile.start,
            move(world.missile.current, world.missile.velocity),
            world.missile.velocity
    )

    return World(
            movedMissile,
            when {
                newExplosion == null -> null
                newExplosion != world.explosion -> newExplosion
                else -> Explosion(newExplosion.center, newExplosion.radius, SHRINK_RATE, newExplosion.color)
            }
    )
}