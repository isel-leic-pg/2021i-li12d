import pt.isel.canvas.*

const val MARGIN = 50

fun main() {

    /*
    val values: List<Int> = listOf(5, 3, 2, 8, 3)

    println("values = $values")
    println("evenValues = ${values.filter { it % 2 == 0 }}")
    println("oddValues = ${values.filter { it % 2 != 0 }}")
    println("positiveValues = ${values.filter { it >= 0 }}")
    println("doubledValues = ${values.map { it * 2 }}")

    val evenValues: List<Int> = values.filter { it % 2 == 0 }
    val doubledEvenValues: List<Int> = evenValues.map { it * 2 }

    println("doubledEvenValues = $doubledEvenValues")
    println("doubledOddValues = ${values.filter { it % 2 != 0 }.map { it * 2 }}")
    println("doubledDoubledValues = ${values.map { it * 2 }.map { it * 2 }}")
     */

    onStart {
        val canvas = Canvas(WORLD_WIDTH, WORLD_HEIGHT, BLACK)
        val missiles: List<Missile> = listOf(
                createMissile(WORLD_WIDTH, WORLD_HEIGHT, MARGIN),
                createMissile(WORLD_WIDTH, WORLD_HEIGHT, MARGIN),
                createMissile(WORLD_WIDTH, WORLD_HEIGHT, MARGIN)
        )
        var world = World(missiles)

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
