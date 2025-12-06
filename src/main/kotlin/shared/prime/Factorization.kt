package shared.prime

fun factorize(value: Long) {
    var factorizedValue = value

    val factorsMap = hashMapOf<Long, Int>()

    for (factor in primeFactors) {
        val longFactor = factor.toLong()

        while (factorizedValue % longFactor == 0L) {
            if (!factorsMap.containsKey(longFactor)) {
                factorsMap[longFactor] = 1
            } else {
                factorsMap[longFactor] = factorsMap[longFactor]!! + 1
            }

            factorizedValue /= factor

        }

    }

    println(factorsMap)
}

fun getPrimeFactors(value: Long): Set<Long> {
    val factors = hashSetOf<Long>()

    var factorizedValue = value

    for (factor in primeFactors) {

        while (factorizedValue % factor == 0L) {
            factors.add(factor)
            factorizedValue /= factor
        }

    }

    return factors
}