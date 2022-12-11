package aoc

import readInput
import kotlin.math.*
import java.math.BigInteger
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

data class Monkey(
    val items: MutableList<Long>,
    val op: String,
    val testDivBy: Int,
    val testOutcome:List<Int>,
    var itemsInspected: Long = 0
)

fun parseMonkeys(iter: Iterator<String>) : List<Monkey> {
    val monkeys = mutableListOf<Monkey>()

    for(line in iter){
        if(line.startsWith("Monkey")){
            val items = iter.next().split(": ")[1].split(", ").map { it.toLong() }.toMutableList()
            val op = iter.next().split("new = ")[1]
            val testDivBy = iter.next().split(" ").last().toInt()
            val testTrue = iter.next().split(" ").last().toInt()
            val testFalse = iter.next().split(" ").last().toInt()

            monkeys.add(Monkey(items, op, testDivBy, listOf(testFalse, testTrue)))
        }
    }

    return monkeys
}

fun simulate(monkeys: List<Monkey>, rounds: Int, decreaseWorryLevel: Boolean){
    val mod = monkeys.map { it.testDivBy.toLong() }.reduce(Long::times)

    repeat(rounds){
        for(monkey in monkeys){
            while(monkey.items.isNotEmpty()){
                var level = monkey.op.replace("old", monkey.items.removeFirst().toString()).split(" ").let { 
                    if(it[1] == "+") it[0].toLong() + it[2].toLong()
                    else it[0].toLong() * it[2].toLong()
                }
                monkey.itemsInspected++
                if(decreaseWorryLevel) level /= 3 else level %= mod
                monkeys[monkey.testOutcome[max(1 - (level % monkey.testDivBy), 0).toInt()]].items.add(level)
            }
        }
    }
}

fun day11Task1(){
    val monkeys = parseMonkeys(readInput("day11-input")!!.iterator()).also { simulate(it, 20, true) }
    val result = monkeys.map { it.itemsInspected }.sorted().takeLast(2).reduce { v1, v2 -> v1 * v2 }
    println("Result: $result")
    assert(result == 58056L)
}

fun day11Task2(){
    val monkeys = parseMonkeys(readInput("day11-input")!!.iterator()).also { simulate(it, 10000, false) }
    val result = monkeys.map { it.itemsInspected }.sorted().takeLast(2).reduce { v1, v2 -> v1 * v2 }
    println("Result: $result")
    assert(result == 15048718170)
}
