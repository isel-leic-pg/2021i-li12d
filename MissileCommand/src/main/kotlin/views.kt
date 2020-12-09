import pt.isel.canvas.Canvas
import pt.isel.canvas.GREEN
import pt.isel.canvas.WHITE
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * Draws the world.
 * @param canvas    the canvas where the world is to be drawn
 * @param world     the world
 */
fun drawWorld(canvas: Canvas, world: World) {
    canvas.erase()

    world.explosions.forEach { drawExplosion(canvas, it) }
    world.missiles.forEach { drawMissile(canvas, it) }
    drawGround(canvas, world)
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

    val magnitude = sqrt(missile.velocity.dx.pow(2) + missile.velocity.dy.pow(2))
    val missileSize = 12
    canvas.drawLine(
        (missile.current.x - missileSize * (missile.velocity.dx / magnitude)).roundToInt(),
        (missile.current.y - missileSize * (missile.velocity.dy / magnitude)).roundToInt(),
        missile.current.x.roundToInt(),
        missile.current.y.roundToInt(),
        WHITE,
        3
    )

    val trailSize = 120
    canvas.drawLine(
        (missile.current.x - trailSize * (missile.velocity.dx / magnitude)).roundToInt(),
        (missile.current.y - trailSize * (missile.velocity.dy / magnitude)).roundToInt(),
        (missile.current.x - missileSize * (missile.velocity.dx / magnitude)).roundToInt(),
        (missile.current.y - missileSize * (missile.velocity.dy / magnitude)).roundToInt(),
        missile.color,
        1
    )
}


fun drawGround(canvas: Canvas, world: World) {
    canvas.drawLine(
        xFrom = 0,
        yFrom = world.height - world.groundHeight,
        xTo = world.width,
        yTo = world.height - world.groundHeight,
        GREEN,
        4
    )
}











