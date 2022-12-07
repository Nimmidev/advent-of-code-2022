package aoc

import readInput
import java.nio.file.Path

interface Entry { val name: String; val size: Long }
data class File(override val name: String, override val size: Long) : Entry
data class Directory(override val name: String, val entries: MutableList<Entry> = mutableListOf()) : Entry {
    override val size: Long by lazy { entries.sumOf { it.size } }
}

fun parseFilesystem(lines: List<String>) : Map<String, Directory> {
    val directoryMap = mutableMapOf<String, Directory>()
    var cwd = mutableListOf<String>()

    for(line in lines){
        val parts = line.split(" ")

        if(parts[0] == "$" && parts[1] == "cd"){
            if(parts[2] == "..") {
                cwd.removeLast()
            } else {
                cwd.add(parts[2])
                val dir = Directory(parts[2])
                directoryMap[cwd.joinToString("/")] = dir
                directoryMap[cwd.subList(0,cwd.size - 1).joinToString("/")]?.let { it.entries.add(dir) }
            }
        } else if(parts[0].matches(Regex("[0-9]+"))){
            directoryMap[cwd.joinToString("/")]!!.entries.add(File(parts[1], parts[0].toLong()))
        } 
    }

    return directoryMap
}

fun day7Task1(){
    val directoryMap = parseFilesystem(readInput("day7-input")!!)
    val result = directoryMap.values.filter { it.size <= 100000L }.sumOf { it.size }
    println("Result: $result")
    assert(result == 1390824L)
}

fun day7Task2(){
    val directoryMap = parseFilesystem(readInput("day7-input")!!)
    val size = directoryMap.values
        .map { Pair(it, 40000000 - directoryMap["/"]!!.size + it.size) }
        .filter { it.second >= 0 }
        .sortedBy { it.second }
        .first().first.size

    println("Result: $size")
    assert(size == 7490863L)
}
