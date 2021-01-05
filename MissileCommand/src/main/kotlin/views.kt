import pt.isel.canvas.Canvas
import pt.isel.canvas.GREEN
import pt.isel.canvas.WHITE
import kotlin.math.roundToInt

const val MISSILE_LENGTH = 12.0
const val MISSILE_TRAIL_LENGTH = 120.0


fun drawGame(canvas: Canvas, game: Game) {
    canvas.erase()
    drawWorld(canvas, game.world)

    val modeText = when {
        game.mode == Mode.PLAYING -> "REC"
        game.replayInfo?.mode == ReplayMode.FORWARD -> "\u25B6"
        game.replayInfo?.mode == ReplayMode.BACKWARD -> "\u25C0"
        else -> ""
    }

    canvas.drawText(
        game.world.width - 100,
        game.world.height - game.world.groundHeight / 3,
        modeText,
        GREEN,
        24
    )
}

/**
 * Draws the world.
 * @param canvas    the canvas where the world is to be drawn
 * @param world     the world
 */
fun drawWorld(canvas: Canvas, world: World) {

    drawGround(canvas, world)
    world.explosions.forEach { drawExplosion(canvas, it) }
    world.missiles.forEach { drawMissile(canvas, it) }
    world.defenderMissiles.forEach { drawMissile(canvas, it) }
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
 * @param canvas    the canvas where the missile is to be drawn
 * @param missile   the missile
 */
private fun drawMissile(canvas: Canvas, missile: Missile) {

    val missileVectorNorm = missile.velocity.toVector().norm()

    val missileVectorView = missileVectorNorm * MISSILE_LENGTH
    val missileStart = Location(missile.current.x, missile.current.y)
    val missileEnd = (missileStart.toVector() - missileVectorView).toLocation()

    canvas.drawLine(
        missileStart.x.roundToInt(),
        missileStart.y.roundToInt(),
        missileEnd.x.roundToInt(),
        missileEnd.y.roundToInt(),
        WHITE,
        3
    )

    val missileTrailVector = missileVectorNorm * MISSILE_TRAIL_LENGTH
    val projectedTrailEnd = missileEnd.toVector() - missileTrailVector

    val distanceToOrigin = (missileEnd.toVector() - missile.origin.toVector()).magnitude()
    val distanceToTrailEnd = (missileEnd.toVector() - projectedTrailEnd).magnitude()

    val trailEnd =
        if (distanceToTrailEnd < distanceToOrigin) projectedTrailEnd.toLocation()
        else missile.origin

    canvas.drawLine(
        missileEnd.x.roundToInt(),
        missileEnd.y.roundToInt(),
        trailEnd.x.roundToInt(),
        trailEnd.y.roundToInt(),
        missile.color,
        1
    )
}

/**
 * Draws the ground.
 * @param canvas    the canvas where the ground is to be drawn
 * @param world     the world instance
 */
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











