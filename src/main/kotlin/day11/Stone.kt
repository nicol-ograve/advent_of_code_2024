package day11

class Stone(initialValue: Long, val onSplit: () -> Unit) {
    var value: Long?
    val children = mutableListOf<Stone>()

    init {
        value = initialValue
    }

    fun blink() {
        val stringValue = value.toString()
        if(value != null) {
            if (value == 0L) {
                value = 1
            } else if (stringValue.length % 2 == 0) {
                if(stringValue.contains("-")){
                    println("???")
                }
                onSplit()
                children.add(
                    Stone(stringValue.substring(0, stringValue.length / 2).toLong(), onSplit)
                )
                children.add(
                    Stone(stringValue.substring(stringValue.length / 2).toLong(), onSplit)
                )
                value = null

            } else {
                value = value!! * 2024
            }
        } else {
            children.forEach { it.blink() }
        }
    }
}
