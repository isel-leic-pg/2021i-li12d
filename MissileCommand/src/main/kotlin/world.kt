
private const val WORLD_WIDTH = 800
private const val WORLD_HEIGHT = 600
private const val MARGIN = 50
private const val GROUND_HEIGHT = 50

/**
 * Representation of the game world.
 */
data class World(
        val width: Int = WORLD_WIDTH,
        val height: Int = WORLD_HEIGHT,
        val groundHeight: Int = GROUND_HEIGHT,
        val missiles: List<Missile> = listOf(),
        val explosions: List<Explosion> = listOf(),
)

/**
 * Creates a new [World] instance.
 *
 * @return the new instance
 */
fun createWorld(): World {
    val missilesList: List<Missile> = listOf(
            createMissile(WORLD_WIDTH, WORLD_HEIGHT, MARGIN, FOE_MISSILE_VELOCITY_MAGNITUDE),
            createMissile(WORLD_WIDTH, WORLD_HEIGHT, MARGIN, FOE_MISSILE_VELOCITY_MAGNITUDE),
            createMissile(WORLD_WIDTH, WORLD_HEIGHT, MARGIN, FOE_MISSILE_VELOCITY_MAGNITUDE)
    )
    return World(missiles = missilesList)
}

/**
 * Builds a new world instance from the given one.
 *
 * @param world the world instance
 *
 * @return the new world instance
 */
fun buildWorld(
    world: World,
    width: Int = world.width,
    height: Int = world.height,
    groundHeight: Int = world.groundHeight,
    missiles: List<Missile> = world.missiles,
    explosions: List<Explosion> = world.explosions) = World(width, height, groundHeight, missiles, explosions)


/**
 * Adds the explosion to the world.
 *
 * @param world     the world instance
 * @param location  the location of the new explosion
 * @return the new world instance
 */
fun addExplosionToWorld(world: World, location: Location) =
        buildWorld(world, explosions = world.explosions + Explosion(location))

/**
 * Computes the list of missiles that were destroyed by any of the explosions.
 *
 * @param missiles      the current list of missiles
 * @param explosions    the explosions to consider
 * @return the surviving missiles.
 */
fun computeNextMissiles(missiles: List<Missile>, explosions: List<Explosion>): List<Missile> {
    return missiles.filter { !detectCollision(explosions, it) }.map { moveMissile(it) }
}

/**
 * Verifies if any of the given explosions destroys the missile.
 *
 * @param explosions    the explosions to consider
 * @param missile       the missile
 * @return a boolean value to indicate if the [missile] was destroyed or not by any of the explosions in [explosions]
 */
fun detectCollision(explosions: List<Explosion>, missile: Missile): Boolean {
    return explosions.any { detectCollision(it, missile) }
}

/**
 * Verifies if the given explosion destroys the given missile.
 *
 * @param explosion the explosion to consider
 * @param missile   the missile
 * @return a boolean value to indicate if the [missile] was destroyed or not by [explosion]
 */
fun detectCollision(explosion: Explosion, missile: Missile) =
    isExpanding(explosion) && distance(explosion.center, missile.current) < explosion.radius

/**
 * Computes the new world based on the given one.
 *
 * @param world The current [World] instance
 * @return The new [World] instance
 */
fun computeNextWorld(world: World): World {
    val newExplosions: List<Explosion> = world.explosions.mapNotNull { evolveExplosion(it) }
    val newMissiles: List<Missile> = computeNextMissiles(world.missiles, world.explosions)
    return buildWorld(world, missiles = newMissiles, explosions = newExplosions)
}