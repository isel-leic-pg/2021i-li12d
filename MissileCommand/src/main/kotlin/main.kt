import pt.isel.canvas.*


/**
 * Implementation of Atari's Missile Command
 * Inspiration source: https://games.aarp.org/games/atari-missile-command
 */
fun main() {

    onStart {
        var world = createWorld()
        val canvas = Canvas(world.width, world.height, BLACK)

        canvas.onMouseDown {
            // TODO: (3) Instead of instantly adding an explosion to the world, we should instead add a defender missile
            world = addExplosionToWorld(world, Location(it.x.toDouble(), it.y.toDouble()))
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
