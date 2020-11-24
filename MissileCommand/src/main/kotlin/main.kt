import pt.isel.canvas.*

const val MARGIN = 50

fun main() {

    onStart {
        val canvas = Canvas(WORLD_WIDTH, WORLD_HEIGHT, BLACK)
        var world = World(createMissile(WORLD_WIDTH, WORLD_HEIGHT, MARGIN))

        canvas.onMouseDown {
            // TODO: (6) Refactor to extract function addExplosionToWorld
            world = World(
                    world.missile,
                    Explosion(Location(it.x.toDouble(), it.y.toDouble()))
            )
        }

        canvas.onTimeProgress(period = 25) {
            // Apply time passing to the world
            world = computeNextWorld(world)
            drawWorld(canvas, world)
        }
    }

    onFinish {
        println("Bye bye!")
    }
}