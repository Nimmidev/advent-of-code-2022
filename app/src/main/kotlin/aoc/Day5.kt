package aoc

import kotlin.collections.ArrayDeque
import kotlin.jvm.internal.iterator
import readInput

val MOVE_REGEX = Regex("move ([0-9]+) from ([0-9]+) to ([0-9]+)")

fun parseAndGetTopCrates(lines: List<String>, calcStackInsertIndex: (index: Int) -> Int) : String {
    val stacks = mutableListOf<ArrayDeque<Char>>()
    var shouldParseCrates = true

    for(line in lines){
        if(shouldParseCrates){
            if(line.isEmpty()){
                shouldParseCrates = false
            } else {
                for((i, crate) in Regex(".{3}\\s?").findAll(line).withIndex()){
                    if(stacks.size < i + 1) stacks.add(ArrayDeque())
                    if(crate.value[0] == '[') stacks[i].addLast(crate.value[1])
                }
            }
        } else {
            val (count, from, to) = MOVE_REGEX.find(line)!!.groupValues.let { it.subList(1, it.size).map { it.toInt() } }
            for(i in 0 until count) stacks[from-1].removeFirst().also { stacks[to-1].add(calcStackInsertIndex(i), it) }
        }
    }

    return stacks.mapNotNull { it.firstOrNull() }.joinToString("")
}

fun day5Task1(){
    val topCrates = parseAndGetTopCrates(readInput("day5-input")!!){ 0 }
    println("Result: $topCrates")
    assert(topCrates == "TWSGQHNHL")
}

fun day5Task2(){
    val topCrates = parseAndGetTopCrates(readInput("day5-input")!!){ it }
    println("Result: $topCrates")
    assert(topCrates == "JNRSCDWPP")
}
