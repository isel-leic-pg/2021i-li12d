import java.lang.IllegalArgumentException

fun List<Int>.head(): Int? = if (this.isEmpty()) null else this[0]

fun List<Int>.tail(): List<Int> = if (this.isEmpty())
    emptyList()
else this - this[0]

fun min(v1: Int, v2: Int?): Int = when {
    v2 == null -> v1
    v1 < v2 -> v1
    else -> v2
}

fun recursiveMin(list: List<Int>): Int? {
    val head = list.head()
    val tail = list.tail()
    return if(head == null) null
    else min(head, recursiveMin(tail))
}


fun someFun() {
    val original: List<Int> = buildList(5, 10, 2).shuffled()
    println("original -> $original")
    val modified = original + 30
    println("modified -> $modified")
    println("original -> $original")

    val mutableOriginal: MutableList<Int> = original.toMutableList()
    println("mutableOriginal (first) -> $mutableOriginal")
    mutableOriginal.add(100)
    println("mutableOriginal (last) -> $mutableOriginal")
}

fun main() {

    val list = listOf(10, 20 , 30)
    println(list)

    val array = arrayOf(10, 20, 30)
    val anotherArray = Array<Int>(1000) { idx -> idx }
    array[0]
    array[1]
    array[2]
    array[999]
    println(array)
}
