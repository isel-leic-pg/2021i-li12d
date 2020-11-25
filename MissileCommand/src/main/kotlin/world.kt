
const val WORLD_WIDTH = 800
const val WORLD_HEIGHT = 600

/**
 * Representation of the game world.
 */
data class World(
        val missiles: List<Missile> = listOf(),
        val explosion: Explosion? = null
)

/**
 * Adds the explosion to the world.
 *
 * @param world     the world instance
 * @param location  the location of the new explosion
 * @return the new world instance
 */
fun addExplosionToWorld(world: World, location: Location) = World(world.missiles, Explosion(location))

fun computeNextMissiles(missiles: List<Missile>, explosion: Explosion?): List<Missile> {
    return missiles.filter { !detectCollision(explosion, it) }.map { moveMissile(it) }
}

fun detectCollision(explosion: Explosion?, missile: Missile) =
    if (explosion != null)
        distance(explosion.center, missile.current) < explosion.radius
    else false

/**
 * Computes the new world based on the given one.
 *
 * @param world The current [World] instance
 * @return The new [World] instance
 */
fun computeNextWorld(world: World): World {

    val newExplosion: Explosion? = evolveExplosion(world.explosion)
    val newMissiles: List<Missile> = computeNextMissiles(world.missiles, world.explosion)

    return World(newMissiles, newExplosion)
}