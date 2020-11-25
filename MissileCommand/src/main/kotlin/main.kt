import pt.isel.canvas.*

const val MARGIN = 50

fun main() {

    onStart {
        val canvas = Canvas(WORLD_WIDTH, WORLD_HEIGHT, BLACK)
        var world = World(createMissile(WORLD_WIDTH, WORLD_HEIGHT, MARGIN))

        canvas.onMouseDown {
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
