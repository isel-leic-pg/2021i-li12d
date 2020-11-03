import pt.isel.canvas.*

data class Location(val x: Double, val y: Double)
data class Explosion(val center: Location, val radius: Double, val rate: Double)

data class Missile(val start: Location, val current: Location, val velocity: Velocity)
data class Velocity(val dx: Double, val dy: Double)

fun fromExplosionWithNewRadius(explosion: Explosion, newRadius: Double) =
        Explosion(center = explosion.center, newRadius, rate = explosion.rate)

fun expandUntil(explosion: Explosion, maxRadius: Double): Explosion =
        if (explosion.radius >= maxRadius) explosion
        else fromExplosionWithNewRadius(explosion, newRadius = explosion.radius * explosion.rate)

fun contractUntilZero(explosion: Explosion) =
        if (explosion.radius <= 0) explosion
        else fromExplosionWithNewRadius(explosion, newRadius = explosion.radius * explosion.rate)

fun drawExplosion(canvas: Canvas, explosion: Explosion) {
    canvas.drawCircle(
            explosion.center.x.toInt(),
            explosion.center.y.toInt(),
            explosion.radius.toInt(),
            RED
    )
}

fun drawMissile(canvas: Canvas, missile: Missile) {
    canvas.drawLine(
            missile.start.x.toInt(),
            missile.start.y.toInt(),
            missile.current.x.toInt(),
            missile.current.y.toInt(),
            RED,
            3
    )
}

fun main() {

    onStart {
        val canvas = Canvas(width = 800, height = 600)
        var explosion = Explosion(Location(x = 0.0, y = 0.0), radius = 0.0, rate = 0.0)

        canvas.onMouseDown {
            explosion = Explosion(center = Location(it.x.toDouble(), it.y.toDouble()), radius = 5.0, rate = 1.06)
        }

        val missile = Missile(
                start = Location(100.0, 0.0),
                current = Location(canvas.width / 2.0, canvas.height / 2.0),
                Velocity(0.0, 0.0)
        )

        canvas.onTimeProgress(period = 25) {

            val newExplosion =
                    if (explosion.rate > 1.0) expandUntil(explosion, maxRadius = 50.0)
                    else contractUntilZero(explosion)

            explosion =
                    if (newExplosion == explosion) Explosion(newExplosion.center, newExplosion.radius, rate = 0.94)
                    else newExplosion

            canvas.erase()
            drawExplosion(canvas, explosion)
            drawMissile(canvas, missile)
        }
    }

    onFinish {
        println("Bye bye!")
    }
}