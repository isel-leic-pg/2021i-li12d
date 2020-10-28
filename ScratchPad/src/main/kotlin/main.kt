import pt.isel.canvas.*

data class Location(val x: Int, val y: Int)
data class Explosion(val center: Location, val radius: Double, val rate: Double)

fun fromExplosionWithNewRadius(explosion: Explosion, newRadius: Double) =
        Explosion(center = explosion.center, newRadius, rate = explosion.rate)

fun expandUntil(explosion: Explosion, maxRadius: Double): Explosion =
        if (explosion.radius >= maxRadius) explosion
        else fromExplosionWithNewRadius(explosion, newRadius = explosion.radius * explosion.rate)

fun contractUntilZero(explosion: Explosion) =
        if (explosion.radius <= 0) explosion
        else fromExplosionWithNewRadius(explosion, newRadius = explosion.radius * explosion.rate)


fun drawExplosion(canvas: Canvas, explosion: Explosion) {
    canvas.drawCircle(explosion.center.x, explosion.center.y, explosion.radius.toInt(), RED)
}

fun main() {

    onStart {
        val canvas = Canvas(width = 800, height = 600)

        var explosion = Explosion(Location(canvas.width / 2, canvas.height / 2), radius = 5.0, rate = 1.03)
        drawExplosion(canvas, explosion)

        canvas.onTimeProgress(period = 25) {
            val newExplosion = TODO()
            explosion = if (newExplosion != explosion) newExplosion else TODO()

            canvas.erase()
            drawExplosion(canvas, explosion)
        }
    }

    onFinish {
        println("Bye bye!")
    }
}