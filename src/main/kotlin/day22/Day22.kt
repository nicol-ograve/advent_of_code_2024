package day22

import utils.getDataScanner
import java.util.Scanner

fun main() {
    val isDemo = false
    val scanner = getDataScanner(22, if (isDemo) arrayOf("demo") else emptyArray())

    val numbers = mutableListOf<Long>()
    while (scanner.hasNextLine()) {
        numbers.add(
            scanner.nextLong()
        )
    }



    val result = numbers.sumOf {
        var result = it
        for (i in 0 until 2000) {
            result = transform(result)
        }
        result
    }


    println(result)
}


fun transform(value: Long): Long {
    var result = value

    var operator = result * 64
    result = postOp(result, operator)

    operator = result / 32
    result = postOp(result, operator)

    operator = result * 2048
    result = postOp(result, operator)

    return result


}

fun postOp(value: Long, operator: Long): Long {
    val mixed = mix(value, operator)
    return prune(mixed)
}

fun mix(l: Long, r: Long): Long {
    return l xor r
}

fun prune(v: Long): Long {
    return v % 16777216
}