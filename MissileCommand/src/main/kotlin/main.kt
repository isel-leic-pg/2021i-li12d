import pt.isel.canvas.*

fun main() {

    onStart {
        val canvas = Canvas(width = 800, height = 600, BLACK)
        var explosionView: ExplosionView = ExplosionView(
                Explosion(Location(x = 0.0, y = 0.0), radius = 0.0, rate = 0.0),
                RED
        )

        canvas.onMouseDown {
            explosionView = ExplosionView(
                    Explosion(center = Location(it.x.toDouble(), it.y.toDouble()), radius = 5.0, rate = 1.06),
                    RED
            )
        }

        val missile = Missile(
            start = Location(100.0, 0.0),
            current = Location(canvas.width / 2.0, canvas.height / 2.0),
            Velocity(0.0, 0.0)
        )

        canvas.onTimeProgress(period = 25) {

            val maybeNewExplosion: Explosion =
                if (explosionView.data.rate > 1.0) expandUntil(explosionView.data, maxRadius = 50.0)
                else contractUntilZero(explosionView.data)

            val newExplosion =
                if (maybeNewExplosion == explosionView.data)
                    Explosion(maybeNewExplosion.center, maybeNewExplosion.radius, rate = 0.94)
                else maybeNewExplosion

            explosionView = ExplosionView(newExplosion, explosionView.color)

            canvas.erase()
            drawExplosion(canvas, explosionView)
            drawMissile(canvas, missile, RED)
        }
    }

    onFinish {
        println("Bye bye!")
    }
}