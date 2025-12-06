package year2024.day22

import utils.getDataScanner
import java.lang.StringBuilder

fun main() {
    val isDemo = false
    val scanner = getDataScanner(22, if (isDemo) arrayOf("demo") else emptyArray())

    val numbers = mutableListOf<Long>()
    while (scanner.hasNextLine()) {
        numbers.add(
            scanner.nextLong()
        )
    }

    val bestPricesMaps = hashMapOf<String, Array<Long>>()


    var index = -1
    numbers.forEach {
        index++
        var result = it
        var price = result % 10

        val last4 = arrayOf<Long?>(null, null, null, null)

        for (i in 0 until 2000) {

            val newResult = transform(result)
            val newPrice = newResult % 10

            val priceChange = newPrice - price


            price = newPrice
            result = newResult


            if (i < 3) {
                last4[i + 1] = priceChange
            } else {
                val keyBuilder = StringBuilder()
                for (k in 1..3) {
                    last4[k - 1] = last4[k]
                    keyBuilder.append(last4[k - 1].toString())
                    keyBuilder.append("_")
                }

                last4[3] = priceChange
                keyBuilder.append(priceChange.toString())

                val key = keyBuilder.toString()

                if (!bestPricesMaps.containsKey(key)) {
                    bestPricesMaps[key] = Array(numbers.size) { 0L }
                }

                // wrong: if (bestPricesMaps[key]!![index] < price) {
                if (bestPricesMaps[key]!![index] == 0L) {
                    bestPricesMaps[key]!![index] = price
                }

            }
        }
    }

    var bestKey = ""
    var bestSum = 0L

    bestPricesMaps.keys.forEach { key ->
        val totalSum = bestPricesMaps[key]!!.sum()

        if (totalSum > bestSum) {
            bestKey = key
            bestSum = totalSum
        }

    }


    println(bestKey)
    println(bestSum)
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