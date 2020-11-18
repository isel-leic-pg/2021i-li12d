import pt.isel.canvas.RED

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
        val color: Int = RED
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


fun expandUntil(explosion: Explosion, maxRadius: Double) =
    if (explosion.radius >= maxRadius) explosion
    else fromExplosionWithNewRadius(explosion, newRadius = explosion.radius * explosion.rate)

fun contractUntilZero(explosion: Explosion) =
    if (explosion.radius <= 0) explosion
    else fromExplosionWithNewRadius(explosion, newRadius = explosion.radius * explosion.rate)
