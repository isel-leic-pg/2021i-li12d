import pt.isel.canvas.BLACK
import pt.isel.canvas.Canvas
import pt.isel.canvas.onFinish
import pt.isel.canvas.onStart


/**
 * Implementation of Atari's Missile Command
 * Inspiration source: https://games.aarp.org/games/atari-missile-command
 */
fun main() {

    onStart {
        var game = Game(World(), listOf(World()), Mode.PLAYING)
        val canvas = Canvas(game.world.width, game.world.height, BLACK)

        val groundDmz = game.world.height - (game.world.groundHeight + MAX_RADIUS)
        canvas.onMouseDown {
            if (game.mode == Mode.PLAYING && it.y < groundDmz) {
                val firedMissile = createDefenderMissile(
                    origin = Location(game.world.width / 2.0, (game.world.height - game.world.groundHeight).toDouble()),
                    target = Location(it.x.toDouble(), it.y.toDouble()),
                    DEFENDER_MISSILE_VELOCITY_MAGNITUDE
                )

                val newWorld = game.world.build(defenderMissiles =  game.world.defenderMissiles + firedMissile)
                game = addWorldSnapshotToGame(game, newWorld)
            }
        }

        canvas.onTimeProgress(1500) {
            if (game.mode == Mode.PLAYING) {
                val newWorld = addMissileToWorld(game.world)
                game = addWorldSnapshotToGame(game, newWorld)
            }
        }

        canvas.onTimeProgress(period = 25) { elapsedTime ->
            game = when(game.mode) {
                Mode.PLAYING ->
                    if (elapsedTime < 10_000) addWorldSnapshotToGame(game, computeNextWorld(game.world))
                    else game.build(mode = Mode.STOPPED)
                Mode.REPLAYING -> computeReplaySnapshot(game)
                else -> game
            }
            drawGame(canvas, game)
        }

        canvas.onKeyPressed {
            if (game.mode == Mode.STOPPED) {
                game = when (it.char) {
                    'f' -> game.build(
                        world = game.history.first(),
                        mode = Mode.REPLAYING,
                        replayInfo = Replay(ReplayMode.FORWARD, 0)
                    )
                    'b' -> game.build(
                        world = game.history.last(),
                        mode = Mode.REPLAYING,
                        replayInfo = Replay(ReplayMode.BACKWARD, game.history.size - 1)
                    )
                    else -> game
                }
            }
        }
    }

    onFinish {
        println("Bye bye!")
    }
}

