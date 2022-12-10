package aoc

import readInput
import kotlin.math.max


fun simulateCPU(input: List<String>) : Pair<Int, String> {
    var X = 1; var clock = 0; var total = 0
    var screen = List(6) { MutableList(40){ '.' } }

    for(line in input) {
        for(i in 0 until mapOf('n' to 1, 'a' to 2)[line[0]]!!){
            screen[clock/40%6][clock%40] = if(X in (clock%40)-1..(clock%40)+1) '#' else '.'
            if((++clock - 20) % 40 == 0) total += clock * X
        }

        if(line[0] == 'a') X += line.split(" ")[1].toInt()
    }

    return Pair(total, screen.map { it.joinToString("") }.joinToString("\n"))
}

fun day10Task1(){
    val (result, _) = simulateCPU(readInput("day10-input")!!)
    println("Result: $result")
    assert(result == 13820)
}

fun day10Task2(){
    val (_, result) = simulateCPU(readInput("day10-input")!!)
    println("Result: \n$result")
    assert(result == """
        ####.#..#..##..###..#..#..##..###..#..#.
        ...#.#.#..#..#.#..#.#.#..#..#.#..#.#.#..
        ..#..##...#....#..#.##...#....#..#.##...
        .#...#.#..#.##.###..#.#..#.##.###..#.#..
        #....#.#..#..#.#.#..#.#..#..#.#.#..#.#..
        ####.#..#..###.#..#.#..#..###.#..#.#..#.
    """.trimIndent())
}
