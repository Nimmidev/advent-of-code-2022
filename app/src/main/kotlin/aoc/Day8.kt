package aoc

import readInput
import kotlin.collections.last
import kotlin.IllegalStateException

fun isHighest(lines: List<List<Int>>, x: Int, y: Int, facX: Int, facY: Int) : Pair<Boolean, Int> {
    for(i in 1..Integer.MAX_VALUE){
        try { 
            if(lines[y][x] <= lines[y + i * facY][x + i * facX]) return Pair(false, i) 
        } catch(e: Exception){ 
            return Pair(true, i - 1) 
        }
    }

    throw IllegalStateException()
}

fun day8Task1(){
    val lines = readInput("day8-input")!!.map { it.map(Character::getNumericValue) }
    var visibleTrees = lines.size * 2 + lines[0].size * 2 - 4

    for(y in 1 until lines.size - 1){
        for(x in 1 until lines[y].size - 1){
            if(isHighest(lines, x, y, -1, 0).first || 
                isHighest(lines, x, y, 1, 0).first || 
                isHighest(lines, x, y, 0,-1).first || 
                isHighest(lines, x, y, 0, 1).first
            ) { 
                visibleTrees++
                continue 
            }
        }
    }

    println("Result: $visibleTrees")
    assert(visibleTrees == 1854)
}

fun day8Task2(){
    val lines = readInput("day8-input")!!.map { it.map(Character::getNumericValue) }
    var highestScore = 0

    for(y in 0 until lines.size){
        for(x in 0 until lines[y].size){
            if(y == 0 || x == 0 || y == lines.size - 1 || x == lines[y].size - 1) continue
            val score = isHighest(lines, x, y, -1,  0).second * 
                        isHighest(lines, x, y,  1,  0).second * 
                        isHighest(lines, x, y,  0, -1).second * 
                        isHighest(lines, x, y,  0,  1).second

            if(score > highestScore) highestScore = score
        }
    }

    println("Result: $highestScore")
    assert(highestScore == 527340)
}
