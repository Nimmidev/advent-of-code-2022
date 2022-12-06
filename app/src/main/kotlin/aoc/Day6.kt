package aoc

import readInput

fun findMarkerPosition(line: String, markerLength: Int) : Int {
    for(i in 0 until (line.length - markerLength)){
        if(line.subSequence(i, i + markerLength).toSet().size == markerLength) return i + markerLength
    }

    throw IllegalStateException()
}

fun day6Task1(){
    val result = findMarkerPosition(readInput("day6-input")!![0], 4)
    println("Result: $result")
    assert(result == 1623)
}

fun day6Task2(){
    val result = findMarkerPosition(readInput("day6-input")!![0], 14)
    println("Result: $result")
    assert(result == 3774)
}
