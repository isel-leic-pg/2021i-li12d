
fun min(v1: Int, v2: Int): Int = if (v1 < v2) v1 else v2

fun List<Int>.min(): Int? {
    if (this.isEmpty())
        return null

    var min = this[0]
    var index = 1

    while (index < this.size) {
        if (this[index] < min)
            min = this[index]
        index += 1
    }

    return min
}