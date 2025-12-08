package year2024.day17

enum class ThreeBitRegister {
    A, B, C
}

class ThreeBitComputer(val registerValues: HashMap<ThreeBitRegister, Long>, val program: List<Long>) {
    val registers = registerValues
    var instructionPointer = 0L

    private val instructions: Array<Pair<ThreeBitInstruction, Long>>

    var onOutput: ((Long) -> Unit)? = { print("$it,") }

    init {
        val instructionsList = mutableListOf<Pair<ThreeBitInstruction, Long>>()
        for (i in program.indices step 2) {
            val pair = Pair(
                ThreeBitInstruction.getInstruction(program[i].toInt(), this),
                program[i + 1]
            )
            instructionsList.add(pair)
        }
        instructions = instructionsList.toTypedArray()
    }

    fun executeProgram(): Long {
        //for (i in 0..10000000) {
        var i = 4398046511104
        while (true) {
            i++

            var outputError = false

            instructionPointer = 0
            var nextOutputPointer = 0

            registers[ThreeBitRegister.A] = i
            registers[ThreeBitRegister.B] = registerValues[ThreeBitRegister.B]!!
            registers[ThreeBitRegister.C] = registerValues[ThreeBitRegister.C]!!

            onOutput = {

                if (nextOutputPointer >= program.size || program[nextOutputPointer] != it) {
                    outputError = true
                } else{
                    if(nextOutputPointer == 10){
                        println(i)
                    }
                    if(nextOutputPointer == 16){
                        println("AAAAAAA")
                        println(i)
                    }
                }

                nextOutputPointer++
            }

            while (instructionPointer < instructions.size && !outputError) {
                val instruction = instructions[instructionPointer.toInt()]
                instruction.first.execute(instruction.second)
            }
            if (!outputError && nextOutputPointer == program.size) {
                return i
            }


        }

        return -1
    }
}