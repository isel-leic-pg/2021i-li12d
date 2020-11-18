import pt.isel.canvas.Canvas

fun drawWorld(canvas: Canvas, world: World) {
    canvas.erase()
    if (world.explosion != null)
        drawExplosion(canvas, world.explosion)
    drawMissile(canvas, world.missile)
}

private fun drawExplosion(canvas: Canvas, explosion: Explosion) {
    canvas.drawCircle(
        explosion.center.x.toInt(),
        explosion.center.y.toInt(),
        explosion.radius.toInt(),
        explosion.color
    )
}

private fun drawMissile(canvas: Canvas, missile: Missile) {
    canvas.drawLine(
        missile.start.x.toInt(),
        missile.start.y.toInt(),
        missile.current.x.toInt(),
        missile.current.y.toInt(),
        missile.color,
        3
    )
}