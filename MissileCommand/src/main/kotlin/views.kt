import pt.isel.canvas.Canvas

data class ExplosionView(val data: Explosion, val color: Int)

fun drawExplosion(canvas: Canvas, explosion: ExplosionView) {
    canvas.drawCircle(
        explosion.data.center.x.toInt(),
        explosion.data.center.y.toInt(),
        explosion.data.radius.toInt(),
        explosion.color
    )
}

fun drawMissile(canvas: Canvas, missile: Missile, color: Int) {
    canvas.drawLine(
        missile.start.x.toInt(),
        missile.start.y.toInt(),
        missile.current.x.toInt(),
        missile.current.y.toInt(),
        color,
        3
    )
}