package day17

enum class ThreeBitRegister {
    A, B, C
}

class ThreeBitComputer(val registerValues: HashMap<ThreeBitRegister, Int>, val program: List<Int>) {
    val registers = registerValues
    var instructionPointer = 0

    private val instructions: Array<Pair<ThreeBitInstruction, Int>>

    var onOutput: ((Int) -> Unit)? = { print("$it,") }

    init {
        val instructionsList = mutableListOf<Pair<ThreeBitInstruction, Int>>()
        for (i in program.indices step 2) {
            val pair = Pair(
                ThreeBitInstruction.getInstruction(program[i], this),
                program[i + 1]
            )
            instructionsList.add(pair)
        }
        instructions = instructionsList.toTypedArray()
    }

    fun executeProgram(): Int {
        //for (i in 0..10000000) {
        var i = 0
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
                    if(nextOutputPointer == 6){
                        println(i)
                    }
                }

                nextOutputPointer++
            }

            while (instructionPointer < instructions.size && !outputError) {
                val instruction = instructions[instructionPointer]
                instruction.first.execute(instruction.second)
            }
            if (!outputError && nextOutputPointer == program.size) {
                return i
            }


        }

        return -1
    }
}