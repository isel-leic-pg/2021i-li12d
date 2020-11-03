import pt.isel.canvas.Canvas
import pt.isel.canvas.RED

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