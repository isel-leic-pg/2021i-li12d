import pt.isel.canvas.*

data class Location(val x: Int, val y: Int)
data class Explosion(val center: Location, val radius: Int)

fun drawExplosion(canvas: Canvas, explosion: Explosion) {
    canvas.drawCircle(explosion.center.x, explosion.center.y, explosion.radius, RED)
}

fun expand(explosion: Explosion, delta: Int) = Explosion(explosion.center, explosion.radius + delta)


fun main() {

    onStart {
        val canvas = Canvas(800, 600)

        val explosion = Explosion(Location(canvas.width / 2, canvas.height / 2), 20)
        drawExplosion(canvas, explosion)

        val aLargerExplosion = expand(explosion, 20)
        drawExplosion(canvas, aLargerExplosion)
    }

    onFinish {
        println("Bye bye!")
    }
}