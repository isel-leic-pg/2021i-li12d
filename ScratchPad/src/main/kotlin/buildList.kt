
fun buildList1(from: Int, to: Int, step: Int = 1): List<Int> {

    var list = listOf<Int>()
    var current = from

    while (current <= to) {
        list = list + current
        current += step
    }

    return list
}

fun buildList(start: Int, end: Int, delta: Int = 1) = (start .. end step delta).toList()


fun buildList2(from: Int, to: Int, step: Int = 1): List<Int> {

    val list = mutableListOf<Int>()
    var current = from

    while (current <= to) {
        list.add(current)
        current += step
    }

    return list
}