
fun min(v1: Int, v2: Int): Int = if (v1 < v2) v1 else v2

fun List<Int>.indexOfMin(): Int? {

    if (isEmpty())
        return null

    var indexOfMin = 0
    var min = this[indexOfMin]

    var index = 1
    while (index < this.size) {
        if (this[index] < min) {
            min = this[index]
            indexOfMin = index
        }
        index += 1
    }

    return indexOfMin
}


fun List<Int>.min(): Int? {
    val index = this.indexOfMin()
    return if(index != null) this[index]
    else null
}

fun List<Int>.sort(): List<Int> {
    var original = this
    var result = emptyList<Int>()

    while(result.size != this.size) {
        val min = original.min()
        if (min != null) {
            original = original - min
            result = result + min
        }
    }

    return result
}