import pt.isel.canvas.WHITE

const val MIN_RADIUS = 6.0
const val MAX_RADIUS = 36.0

const val GROWTH_RATE = 1.06
const val SHRINK_RATE = 0.94

// TODO: Explosion color sequence should be branco, amarelo, magenta, vermelho, cyan, verde, azul escuro, preto

/**
 * Represents explosions at a given time instant.
 *
 * @property center     the explosion's center
 * @property radius     the explosion's current radius
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
    val condition: Predicate = { thExplosion: Explosion -> thExplosion.radius < maxRadius }
    return maybeApplyExplosionRate(explosion, condition)
}

/**
 * Conditionally contracts the explosion if it hasn't reached the minimum radius yet.
 *
 * @param explosion the explosion to be tentatively contracted
 * @param minRadius the minimum radius
 * @return the new explosion if the minimum radius has not yet been reached.
 */
private fun contractUntil(explosion: Explosion, minRadius: Double) =
        maybeApplyExplosionRate(explosion) { it.radius > minRadius }

/**
 * Reverts the given explosion's rate, that is, it is expanding and we make it contract.
 *
 * @param explosion the explosion
 * @return the new reverted explosion
 */
private fun revertExplosionRate(explosion: Explosion) =
        Explosion(explosion.center, explosion.radius, SHRINK_RATE, explosion.color)

/**
 * Verifies if the explosion is growing or not.
 *
 * @return true if the explosion is expanding, false otherwise
 */
fun isExpanding(explosion: Explosion) = explosion.rate == GROWTH_RATE

/**
 * Makes the explosion evolve, if it exists. Explosions evolve by expanding until they reach the maximum radius. Once
 * that radius is reached, they start to contract until they reach their minimum radius. After that, they disappear.
 *
 * @param explosion the explosion, if it exists
 * @return the new evolved explosion, or null if it disappeared
 */
fun evolveExplosion(explosion: Explosion?): Explosion? {

    // Using imperative style flow control for demo purposes
    if (explosion == null)
        return null

    val newExplosion =
        if (isExpanding(explosion)) expandUntil(explosion, MAX_RADIUS)
        else contractUntil(explosion, MIN_RADIUS)

    return when {
        newExplosion.radius <= MIN_RADIUS -> null
        newExplosion != explosion -> newExplosion
        else -> revertExplosionRate(newExplosion)
    }
}

