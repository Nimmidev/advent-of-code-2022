package aoc

import kotlin.text.toInt
import kotlin.io.readLine
import readInput

fun main() {
    var day: Int? = null
    val maxDay = 5

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
    }
}
