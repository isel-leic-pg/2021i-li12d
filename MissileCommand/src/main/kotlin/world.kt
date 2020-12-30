
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
        val defenderMissiles: List<Missile> = listOf(),
        val explosions: List<Explosion> = listOf(),
)

/**
 * Adds an attacker missile to the given world.
 *
 * @param world The world instance
 * @return the new world instance
 */
fun addMissileToWorld(world: World) = world.build(
        missiles = world.missiles + createMissile(world.width, world.height, MARGIN, FOE_MISSILE_VELOCITY_MAGNITUDE)
    )

/**
 * Builds a new world instance from the given one.
 *
 * @param world the world instance
 * @return the new world instance
 */
fun World.build(
    width: Int = this.width,
    height: Int = this.height,
    groundHeight: Int = this.groundHeight,
    missiles: List<Missile> = this.missiles,
    defenderMissiles: List<Missile> = this.defenderMissiles,
    explosions: List<Explosion> = this.explosions
) = World(width, height, groundHeight, missiles, defenderMissiles, explosions)

/**
 * Computes the list of missiles that were destroyed by any of the explosions.
 *
 * @param missiles      the current list of attacking missiles
 * @param explosions    the explosions to consider
 * @return the surviving missiles.
 */
fun removeExplodedMissiles(missiles: List<Missile>, explosions: List<Explosion>): List<Missile> {
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

    val evolvedExplosions = world.explosions.mapNotNull { evolveExplosion(it) }
    val nonExplodedMissiles = removeExplodedMissiles(world.missiles, world.explosions)

    val movedDefenderMissiles = world.defenderMissiles.map { moveMissile(it) }
    val defenderMissilesToExplode = movedDefenderMissiles.filter { hasReachedTarget(it) }

    val evolvedDefenderMissiles = movedDefenderMissiles - defenderMissilesToExplode
    val explodedDefenderMissiles = defenderMissilesToExplode.map { Explosion(center = it.current) }

    val groundHitMissiles = nonExplodedMissiles.filter { it.current.y >= world.height - world.groundHeight }
    val groundExplosions = groundHitMissiles.map { Explosion(center = it.current) }

    return world.build(
        missiles = nonExplodedMissiles - groundHitMissiles,
        defenderMissiles = evolvedDefenderMissiles,
        explosions = evolvedExplosions + groundExplosions + explodedDefenderMissiles
    )
}
