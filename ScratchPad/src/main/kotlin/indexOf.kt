
fun indexOf(list: List<Int>, value: Int): Int? {
    var index = 0
    while (index < list.size) {
        if (value == list[index])
            return index
        index += 1
    }
    return null
}
