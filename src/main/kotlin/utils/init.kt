package utils

import java.nio.file.Files
import java.nio.file.Paths


fun main(args: Array<String>) {
    createPackagesForDay(17)
}

fun createPackagesForDay(day: Int) {
    val path = Paths.get("src/main/kotlin/day$day")

    if (Files.notExists(path))
        Files.createDirectory(path)

    Files.createFile(Paths.get("$path/data.txt"))
    Files.createFile(Paths.get("$path/data_demo.txt"))

    val mainFilePath = Paths.get("$path/Day$day.kt");
    Files.createFile(mainFilePath)
    Files.writeString(mainFilePath, mainFileContent(day))

}


fun mainFileContent(day: Int): String {
    return """
     package day$day

    import utils.getDataScanner
            import java.util.Scanner

    fun main() {
        val isDemo = true
        val scanner = getDataScanner($day, if (isDemo) arrayOf("demo") else emptyArray())
        
        val result = "";  
        
        println(result)
    }
""".trimIndent()

}