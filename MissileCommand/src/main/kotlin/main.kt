import pt.isel.canvas.*

const val MARGIN = 50

fun doubleIt(aValue: Int) = aValue * 2

fun main() {

    fun anotherDoubleIt(theValue: Int) = theValue * 2

    val yetAnotherDoubleIt: (Int) -> Int = { it: Int -> it * 2 }

    println(doubleIt(4))
    println(anotherDoubleIt(4))
    println(yetAnotherDoubleIt(4))



    onStart {
        val canvas = Canvas(WORLD_WIDTH, WORLD_HEIGHT, BLACK)

        var world = World(createMissile(WORLD_WIDTH, WORLD_HEIGHT, MARGIN))

        canvas.onMouseDown {
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