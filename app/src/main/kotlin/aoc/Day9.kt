package aoc

import readInput
import kotlin.math.*
import kotlin.io.println

enum class Direction(val vector: Vector) {
    L(Vector(-1, 0)),
    U(Vector(0, 1)),
    R(Vector(1, 0)),
    D(Vector(0, -1))
}

data class Vector(var x: Int, var y: Int){

    operator fun minus(vector: Vector) : Vector {
        return Vector(x - vector.x, y - vector.y)
    }

    operator fun plusAssign(vector: Vector){
        x += vector.x
        y += vector.y
    }

}

class Rope(knotCount: Int) {

    val knots = List(knotCount) { Vector(0, 0) }
    val tailPositions = hashSetOf<Vector>(Vector(0, 0))

    fun simulate(input: List<String>){
        for(line in input){
            val (direction, count) = line.split(" ").let { Pair(Direction.valueOf(it[0]), it[1].toInt()) }
            for(i in 0 until count) move(direction)
        }
    }

    fun move(direction: Direction){
        knots[0] += direction.vector

        for(i in 1 until knots.size){
            val diff = knots[i-1] - knots[i]

            if(abs(diff.x) > 1 || abs(diff.y) > 1){
                knots[i] += Vector(diff.x.coerceIn(-1, 1), diff.y.coerceIn(-1, 1))
                tailPositions.add(knots.last().copy())
            }
        }
    }

}

fun day9Task1(){
    val rope = Rope(2).apply { simulate(readInput("day9-input")!!) }
    println("Result: ${rope.tailPositions.size}")
    assert(rope.tailPositions.size == 6384)
}

fun day9Task2(){
    val rope = Rope(10).apply { simulate(readInput("day9-input")!!) }
    println("Result: ${rope.tailPositions.size}")
    assert(rope.tailPositions.size == 2734)
}
