
const val WORLD_WIDTH = 800
const val WORLD_HEIGHT = 600

/**
 * Representation of the game world.
 */
data class World(
        val missile: Missile? = null,
        val explosion: Explosion? = null
)

/**
 * Adds the explosion to the world.
 *
 * @param world     the world instance
 * @param location  the location of the new explosion
 * @return the new world instance
 */
fun addExplosionToWorld(world: World, location: Location) = World(world.missile, Explosion(location))

fun detectCollision(world: World, missile: Missile) =
    if (world.explosion != null)
        distance(world.explosion.center, missile.current) < world.explosion.radius
    else false

/**
 * Computes the new world based on the given one.
 *
 * @param world The current [World] instance
 * @return The new [World] instance
 */
fun computeNextWorld(world: World): World {

    val newExplosion: Explosion? = evolveExplosion(world.explosion)

    val newMissile = if (world.missile != null && !detectCollision(world, world.missile))
            moveMissile(world.missile)
        else null

    return World(newMissile, newExplosion)
}