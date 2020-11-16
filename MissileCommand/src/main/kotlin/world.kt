/**
 * Representation od the game world
 */
data class World(val missile: Missile,
                 val explosion: Explosion)

/**
 * Computes the new world based on the given one.
 *
 * @param   world   The current [World] instance
 * @return  The new [World] instance
 */
fun doStep(world: World): World {
    return World(
            world.missile,
            Explosion(
                    world.explosion.center,
                    world.explosion.radius * world.explosion.rate,
                    world.explosion.rate,
                    world.explosion.color
            )
    )
}