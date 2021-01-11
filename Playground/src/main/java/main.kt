

fun myPrint(list: List<Int>) {
    var index = 0
    while (index < list.size) {
        println(list[index])
        index += 1
    }
}

fun myIndexOf(list: List<Int>, value: Int): Int? {
    var index = 0
    while (index < list.size) {
        if (value == list[index])
            return index
        index += 1
    }
    return null
}

fun myIndexOf2(list: List<Int>, value: Int): Int? {
    for (index in list.indices) { // ou for (index in 0 until list.size) { ... }
        if (value == list[index])
            return index
    }
    return null
}

fun myIndexOf3(list: List<Int>, value: Int): Int? {
    var index = 0
    for (elem in list) {
        if (value == elem)
            return index
        index += 1
    }
    return null
}

fun myIndexOf4(list: List<Int>, value: Int): Int? {
    list.forEach {
        if (value == it)
            return it
    }
    return null
}

fun anyEven(list: List<Int>) = any(list) { it % 2 == 0 }

fun anyMultipleOfFive(list: List<Int>) = any(list) { it % 5 == 0 }

fun anyHigherThanHundred(list: List<Int>) = any(list) { it > 100 }

fun any(list: List<Int>, predicate: (Int) -> Boolean): Boolean {
    var index = 0
    while (index < list.size) {
        if (predicate(list[index]))
            return true
        index += 1
    }
    return false
}

fun main() {
    val list = listOf(12, 24, 6, 13, 1, 49, 52)
    println(list)
    //println(myIndexOf(list, 49))
    //println(myIndexOf2(list, 50))
    println(myIndexOf3(list, 49))
}

