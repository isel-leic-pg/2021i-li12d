import pt.isel.canvas.Canvas

/**
 * Draws the world.
 * @param canvas    the canvas where the world is to be drawn
 * @param world     the world
 */
fun drawWorld(canvas: Canvas, world: World) {
    canvas.erase()
    if (world.explosion != null)
        drawExplosion(canvas, world.explosion)
    if (world.missile != null)
        drawMissile(canvas, world.missile)
}

/**
 * Draws an explosion.
 * @param canvas        the canvas where the explosion is to be drawn
 * @param explosion     the explosion
 */
private fun drawExplosion(canvas: Canvas, explosion: Explosion) {
    canvas.drawCircle(
        explosion.center.x.toInt(),
        explosion.center.y.toInt(),
        explosion.radius.toInt(),
        explosion.color
    )
}

/**
 * Draws a missile.
 * @param canvas    the canvas where the explosion is to be drawn
 * @param missile   the explosion
 */
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