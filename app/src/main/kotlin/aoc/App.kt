package aoc

import kotlin.text.toInt
import kotlin.io.readLine
import readInput

fun main() {
    var day: Int? = null
    val maxDay = 2

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
    }
}
