import pt.isel.canvas.WHITE

const val MIN_RADIUS = 5.0
const val MAX_RADIUS = 50.0

const val GROWTH_RATE = 1.03
const val SHRINK_RATE = 0.97

/**
 * Represents explosions at a given time instant.
 *
 * TODO: Explosion color sequence is: branco, amarelo, magenta, vermelho, cyan, verde, azul escuro, preto
 *
 * @property center     the explosion's center
 * @property radius     the eplosion's current radius
 * @property rate       the explosion's changing rate (i.e. >= 1.0, it is growing)
 * @property color      the explosion's color
 */
data class Explosion(
        val center: Location,
        val radius: Double = MIN_RADIUS,
        val rate: Double = GROWTH_RATE,
        val color: Int = WHITE
)

/**
 * Creates a new explosion from the given one but with a new radius.
 *
 * @param explosion     the original explosion
 * @param newRadius     the new radius
 * @return the new explosion
 */
fun fromExplosionWithNewRadius(explosion: Explosion, newRadius: Double) =
        Explosion(center = explosion.center, newRadius, rate = explosion.rate, explosion.color)

/**
 * Convenience type alias for predicates.
 */
private typealias Predicate = (Explosion) -> Boolean

/**
 * Makes the explosion vary according to its explosion rate if the given predicate evaluates to true.
 *
 * @param explosion the explosion to be evolved
 * @param predicate the predicate that is evaluated and, if true, mandates the explosion variation
 * @return the new explosion if the predicate evaluates to true.
 */
private fun maybeApplyExplosionRate(explosion: Explosion, predicate: Predicate) =
    if(predicate(explosion))
        fromExplosionWithNewRadius(explosion, newRadius = explosion.radius * explosion.rate)
    else
        explosion

/**
 * Conditionally expands the explosion if it hasn't reached the maximum radius
 *
 * @param explosion the explosion to be tentatively expanded
 * @param maxRadius the maximum radius
 * @return the new explosion if the maximum radius hasn't yet been reached.
 */
private fun expandUntil(explosion: Explosion, maxRadius: Double): Explosion {
    val condition: Predicate = { explosion: Explosion -> explosion.radius < maxRadius }
    return maybeApplyExplosionRate(explosion, condition)
}

/**
 * Conditionally contracts the explosion if it hasn't reached the maximum radius
 *
 * @param explosion the explosion to be tentatively contracts
 * @return the new explosion if the minimum radius has not yet been reached.
 */
private fun contractUntilZero(explosion: Explosion) = maybeApplyExplosionRate(explosion) { it.radius > 0 }

/**
 * Reverts the given explosion's rate, that is, it is expanding and we make it contract.
 *
 * @param explosion the explosion
 * @return the new reverted explosion
 */
private fun revertExplosionRate(explosion: Explosion) =
        Explosion(explosion.center, explosion.radius, SHRINK_RATE, explosion.color)

/**
 * Makes the explosion evolve, if it exists.
 *
 * @param explosion the explosion, if it exists
 * @return the new evolved explosion, or null
 */
fun evolveExplosion(explosion: Explosion?): Explosion? {

    val newExplosion = when {
        explosion == null -> null
        explosion.rate == GROWTH_RATE -> expandUntil(explosion, MAX_RADIUS)
        else -> contractUntilZero(explosion)
    }

    return if (newExplosion == null || newExplosion != explosion) newExplosion
    else revertExplosionRate(newExplosion)
}

