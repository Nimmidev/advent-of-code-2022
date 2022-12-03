package aoc

import kotlin.math.*

import readInput

fun day3Task1(){
    var priority = 0

    for(line in readInput("day3-input")!!){
        var itemsFound: Long = 0;

        for((i, char) in line.withIndex()){
            var sequenceNumber = char.code.let { if(it > 96) it - 96 else it - 38 }
            val mask = 1L shl sequenceNumber

            if(i < line.length / 2) itemsFound = itemsFound or mask
            else if(itemsFound and mask != 0L) { priority += sequenceNumber; break }
        }
    }

    println("Priority: $priority")
    assert(priority == 7428)
}

fun day3Task2(){
    var priority = 0
    var itemsFound = mutableListOf(0L, 0L, 0L)

    for((i, line) in readInput("day3-input")!!.withIndex()){
        for(char in line.withIndex()){
            var sequenceNumber = char.value.code.let { if(it > 96) it - 96 else it - 38 }
            val mask = 1L shl sequenceNumber
            itemsFound[i%3] = itemsFound[i%3] or mask
        }

        if(i % 3 == 2) {
            priority += log2(itemsFound.reduce { v1, v2 -> v1 and v2 }.toDouble()).toInt()
            itemsFound = mutableListOf(0L, 0L, 0L)
        }
    }

    println("Priority: $priority")
    assert(priority == 2650)
}
