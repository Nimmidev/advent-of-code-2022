package aoc

import readInput

fun day4Task1(){
    var overlaps = 0

    for(line in readInput("day4-input")!!){
        val (s1, s2) = line.split(",").map { it.split("-").map { it.toInt() } }
        if(s1[0] >= s2[0] && s1[1] <= s2[1] || s2[0] >= s1[0] && s2[1] <= s1[1]) overlaps++
    }

    println("Overlaps: $overlaps")
    assert(overlaps == 441)
}

fun day4Task2(){
    var overlaps = 0

    for(line in readInput("day4-input")!!){
        val (s1, s2) = line.split(",").map { it.split("-").map { it.toInt() } }
        if(s1[0] >= s2[0] && s1[0] <= s2[1] || s2[0] >= s1[0] && s2[0] <= s1[1]) overlaps++
    }

    println("Overlaps: $overlaps")
    assert(overlaps == 861)
}
