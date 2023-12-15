package commons

import java.io.File
import java.nio.file.Paths

fun inputsFileToLineSequence(day: Int) = File("${Paths.get("").toAbsolutePath()}/src/main/kotlin/day$day/input.txt").bufferedReader().lineSequence()