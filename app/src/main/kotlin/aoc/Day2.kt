package aoc

import readInput

enum class Choice(
    val score: Int, 
    weakAgainst: () -> Choice
) {
    Rock(1, { Choice.Paper }),
    Paper(2, { Choice.Scissors }),
    Scissors(3, { Choice.Rock });

    val weakAgainst by lazy { weakAgainst() } 
}

enum class Outcome(val score: Int, val choiceFor: (Choice) -> Choice) {
    Win(6, { it.weakAgainst }),
    Draw(3, { it }),
    Lose(0, { it.weakAgainst.weakAgainst })
}

val charChoiceMap = mapOf(
    "A" to Choice.Rock,
    "B" to Choice.Paper,
    "C" to Choice.Scissors,
    "X" to Choice.Rock,
    "Y" to Choice.Paper,
    "Z" to Choice.Scissors
)

val charOutcomeMap = mapOf(
    "X" to Outcome.Lose,
    "Y" to Outcome.Draw,
    "Z" to Outcome.Win
)

fun day2Task1(){
    var result = 0
    
    for(line in readInput("day2-input")!!){
        val (choiceOpponent, choiceYou) = line.split(" ").map { charChoiceMap[it]!! }
        result += choiceYou.score

        if(choiceOpponent == choiceYou) result += Outcome.Draw.score
        else if(choiceOpponent.weakAgainst == choiceYou) result += Outcome.Win.score
    }

    println("Result: $result")
    assert(result == 11603)
}

fun day2Task2(){
    var result = 0
    
    for(line in readInput("day2-input")!!){
        val (choiceOpponent, outcome) = line.split(" ").let { 
            Pair(charChoiceMap[it[0]]!!, charOutcomeMap[it[1]]!!)
        }

        result += outcome.choiceFor(choiceOpponent).score + outcome.score
    }

    println("Result: $result")
    assert(result == 12725)
}
