import kotlin.math.max

const val MAX_RADIUS = 15

data class Circle(val x: Int, val y: Int, val radius: Int)

fun moveCircleRight(circle: Circle, delta: Int) = Circle(circle.x + delta, circle.y, circle.radius)

fun growCircle(circle: Circle, delta: Int) =
        if (circle.radius < MAX_RADIUS) Circle(circle.x, circle.y, max(circle.radius + delta, MAX_RADIUS))
        else circle

fun main() {
    var circle = Circle(100, 100, 10)
    println(circle)
    circle = moveCircleRight(circle, 10)
    println(circle)
    circle = growCircle(circle, 5)
    println(circle)
    circle = growCircle(circle, 5)
    println(circle)
}