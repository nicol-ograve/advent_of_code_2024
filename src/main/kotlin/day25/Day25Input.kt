package day25

import java.util.Scanner

class Day25Input(scanner: Scanner) {
    val keys: List<Array<Int>>
    val locks: List<Array<Int>>


    init {
        val ks = mutableListOf<Array<Int>>()
        val lks = mutableListOf<Array<Int>>()

        while (scanner.hasNextLine()) {
            val isKey = scanner.nextLine()[0] == '.'

            val lines = mutableListOf<String>()
            for (i in 0 until 5) {
                lines.add(scanner.nextLine())
            }
            scanner.nextLine()

            val newScheme = parseScheme(if (isKey) lines.reversed() else lines)

            val list = if (isKey) ks else lks
            list.add(newScheme)

            if(scanner.hasNextLine()){
                // Empty row between schemes
                scanner.nextLine()
            }

        }

        keys = ks
        locks = lks

    }

    private fun parseScheme(lines: List<String>): Array<Int> {
        val result = arrayOf(0, 0, 0, 0, 0)

        lines.forEach { line ->
            line.forEachIndexed { index, c ->
                if (c == '#') {
                    result[index]++
                }
            }
        }

        return result
    }
}