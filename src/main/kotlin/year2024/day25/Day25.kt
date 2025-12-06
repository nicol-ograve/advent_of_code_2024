 package year2024.day25

import utils.getDataScanner

 fun main() {
    val isDemo = false
    val scanner = getDataScanner(25, if (isDemo) arrayOf("demo") else emptyArray())

    val input = Day25Input(scanner)


    var matches = 0

    for(lock in input.locks){
        for (key in input.keys){


            var ok = true
            for(i in 0 until 5){
                if(lock[i] + key[i] >=6){
                    ok = false
                }
            }
            if(ok){
                matches++
            }

        }
    }

    val result = matches;
    
    println(result)
}