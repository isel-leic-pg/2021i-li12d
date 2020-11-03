import pt.isel.canvas.*

fun main() {

    onStart {
        val canvas = Canvas(width = 800, height = 600, BLACK)
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