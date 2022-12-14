package aoc

import kotlin.text.toInt
import kotlin.io.readLine
import readInput

fun main() {
    var day: Int? = null
    val maxDay = 11

    while(day == null){
        print("Select day to run (1-$maxDay): ")
        readlnOrNull()?.let {
            try {
                val inputNbr = it.toInt()

                if(inputNbr > 0 && inputNbr <= maxDay){
                    day = inputNbr
                }
            } catch(e: NumberFormatException){

            }
        }
    }

    println("")

    if(day == 1){
        day1Task1()
        day1Task2()
    } else if(day == 2){
        day2Task1()
        day2Task2()
    } else if(day == 3){
        day3Task1()
        day3Task2()
    } else if(day == 4){
        day4Task1()
        day4Task2()
    } else if(day == 5){
        day5Task1()
        day5Task2()
    } else if(day == 6){
        day6Task1()
        day6Task2()
    } else if(day == 7){
        day7Task1()
        day7Task2()
    } else if(day == 8){
        day8Task1()
        day8Task2()
    } else if(day == 9){
        day9Task1()
        day9Task2()
    } else if(day == 10){
        day10Task1()
        day10Task2()
    } else if(day == 11){
        day11Task1()
        day11Task2()
    }
}
