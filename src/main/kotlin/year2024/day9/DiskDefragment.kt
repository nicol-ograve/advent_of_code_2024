package year2024.day9

class DiskDefragment(
    private val disk: Array<Int?>,
    private val fileBlocks: MutableList<Block>,
    private val emptyBlocks: MutableList<Block>
) {


    fun solvePart2(): Long {

        val filesToMove = fileBlocks.reversed()

        for (file in filesToMove) {
            var emptyIndex = 0
            var placed = false

            while (emptyIndex < emptyBlocks.size && !placed) {
                val emptyBlock = emptyBlocks[emptyIndex]
                if (emptyBlock.size >= file.size && emptyBlock.startPosition < file.startPosition) {
                    val fileId = disk[file.startPosition]

                    for (i in 0 until file.size) {
                        disk[file.startPosition + i] = null
                        disk[emptyBlock.startPosition + i] = fileId
                    }

                    emptyBlocks.add(Block(file.startPosition, file.size))

                    placed = true

                    emptyBlock.size = emptyBlock.size - file.size
                    emptyBlock.startPosition = emptyBlock.startPosition + file.size

                }


                emptyIndex++
            }
        }

        return calculateChecksum()
    }

    fun solvePart1(): Long {

        var up = 0
        var down = disk.size - 1

        while (up < down) {
            while (up < down && disk[up] != null) {
                up++
            }
            while (up < down && disk[down] == null) {
                down--
            }
            disk[up] = disk[down]
            disk[down] = null
        }



        return calculateChecksum()
    }

    private fun calculateChecksum(): Long {

        var result = 0L

        var i = 0

        for (i in disk.indices)
            if (disk[i] != null) {
                result += disk[i]!! * i

            }

        return result
    }


}

data class Block(var startPosition: Int, var size: Int)
