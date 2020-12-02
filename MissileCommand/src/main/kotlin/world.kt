
private const val WORLD_WIDTH = 800
private const val WORLD_HEIGHT = 600
private const val MARGIN = 50

/**
 * Representation of the game world.
 */
data class World(
        val width: Int = WORLD_WIDTH,
        val height: Int = WORLD_HEIGHT,
        val missiles: List<Missile> = listOf(),
        // TODO: (6) The world will support multiple explosions
        val explosion: Explosion? = null
)

fun initializeWorld(): World {
    val missilesList: List<Missile> = listOf(
            createMissile(WORLD_WIDTH, WORLD_HEIGHT, MARGIN, FOE_MISSILE_VELOCITY_MAGNITUDE),
            createMissile(WORLD_WIDTH, WORLD_HEIGHT, MARGIN, FOE_MISSILE_VELOCITY_MAGNITUDE),
            createMissile(WORLD_WIDTH, WORLD_HEIGHT, MARGIN, FOE_MISSILE_VELOCITY_MAGNITUDE)
    )
    return World(missiles = missilesList)
}

/**
 * Adds the explosion to the world.
 *
 * @param world     the world instance
 * @param location  the location of the new explosion
 * @return the new world instance
 */
fun addExplosionToWorld(world: World, location: Location) = World(world.width, world.height, world.missiles, Explosion(location))

fun computeNextMissiles(missiles: List<Missile>, explosion: Explosion?): List<Missile> {
    return missiles.filter { !detectCollision(explosion, it) }.map { moveMissile(it) }
}

// TODO: (7) Change implementation so that missiles are only destroyed by expanding explosions
// Add isExpanding(explosion): Boolean to explosion.kt
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

    return World(world.width, world.height, newMissiles, newExplosion)
}