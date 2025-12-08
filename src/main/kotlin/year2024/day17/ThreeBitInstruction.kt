package year2024.day17

import java.lang.RuntimeException
import kotlin.math.pow

sealed class ThreeBitInstruction(private val computer: ThreeBitComputer) {
    abstract fun execute(param: Long)

    fun getComboOperandValue(operandValue: Long): Long {
        return when (operandValue) {
            in 0..3 -> operandValue
            4L -> computer.registers[ThreeBitRegister.A]!!
            5L -> computer.registers[ThreeBitRegister.B]!!
            6L -> computer.registers[ThreeBitRegister.C]!!
            else -> throw RuntimeException("Invalid combo operand value")
        }
    }

    companion object {
        fun getInstruction(opCode: Int, computer: ThreeBitComputer): ThreeBitInstruction {
            return when (opCode) {
                0 -> RegisterDvInstruction(computer, ThreeBitRegister.A)
                1 -> BxlInstruction(computer)
                2 -> BstInstruction(computer)
                3 -> JnzInstruction(computer)
                4 -> BxcInstruction(computer)
                5 -> OutInstruction(computer)
                6 -> RegisterDvInstruction(computer, ThreeBitRegister.B)
                7 -> RegisterDvInstruction(computer, ThreeBitRegister.C)
                else -> throw RuntimeException("Invalid op code")
            }

        }

    }
}

private class RegisterDvInstruction(private val computer: ThreeBitComputer, private val register: ThreeBitRegister) :
    ThreeBitInstruction(computer) {
    override fun execute(param: Long) {
        val numerator = computer.registers[ThreeBitRegister.A]!!
        val comboValue = getComboOperandValue(param)

        val result: Long = (numerator / 2.0.pow(comboValue.toInt())).toLong()
        computer.registers[register] = result

        computer.instructionPointer += 1
    }

}

private class BxlInstruction(private val computer: ThreeBitComputer) : ThreeBitInstruction(computer) {
    override fun execute(param: Long) {
        val bValue = computer.registers[ThreeBitRegister.B]!!

        computer.registers[ThreeBitRegister.B] = bValue xor param

        computer.instructionPointer += 1
    }

}

private class BstInstruction(private val computer: ThreeBitComputer) : ThreeBitInstruction(computer) {
    override fun execute(param: Long) {
        val comboValue = getComboOperandValue(param)
        computer.registers[ThreeBitRegister.B] = comboValue % 8

        computer.instructionPointer += 1
    }

}

private class JnzInstruction(private val computer: ThreeBitComputer) : ThreeBitInstruction(computer) {
    override fun execute(param: Long) {
        val aValue = computer.registers[ThreeBitRegister.A]!!

        if (aValue == 0L) {
            computer.instructionPointer += 1
        } else {
            computer.instructionPointer = param / 2
        }
    }

}

private class BxcInstruction(private val computer: ThreeBitComputer) : ThreeBitInstruction(computer) {
    override fun execute(param: Long) {
        val bValue = computer.registers[ThreeBitRegister.B]!!
        val cValue = computer.registers[ThreeBitRegister.C]!!

        computer.registers[ThreeBitRegister.B] = bValue xor cValue

        computer.instructionPointer += 1
    }

}

private class OutInstruction(private val computer: ThreeBitComputer) : ThreeBitInstruction(computer) {
    override fun execute(param: Long) {
        val comboValue = getComboOperandValue(param) % 8
        computer.onOutput?.let { it(comboValue) }


        computer.instructionPointer += 1
    }

}

