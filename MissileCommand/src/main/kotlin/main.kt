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
        var world = World()
        val canvas = Canvas(world.width, world.height, BLACK)

        val groundDmz = world.height - (world.groundHeight + MAX_RADIUS)
        canvas.onMouseDown {
            if (it.y < groundDmz) {
                val firedMissile = createDefenderMissile(
                    origin = Location(world.width / 2.0, (world.height - world.groundHeight).toDouble()),
                    target = Location(it.x.toDouble(), it.y.toDouble()),
                    DEFENDER_MISSILE_VELOCITY_MAGNITUDE
                )
                world = world.build(defenderMissiles =  world.defenderMissiles + firedMissile)
            }
        }

        canvas.onTimeProgress(1500) {
            world = addMissileToWorld(world)
        }

        canvas.onTimeProgress(period = 25) {
            world = computeNextWorld(world)
            drawWorld(canvas, world)
        }
    }

    onFinish {
        println("Bye bye!")
    }
}

