package aoc

import readInput

fun day1Task1(){
    val lines = readInput("day1-input")!!
    var caloriesTotal = 0;
    var caloriesMax = 0;

    for(line in lines){
        if(line.isEmpty()){
            if(caloriesTotal > caloriesMax) caloriesMax = caloriesTotal;
            caloriesTotal = 0;
        } else {
            caloriesTotal += line.toInt()
        }
    }

    println("Elf with the most calories is carrying a total of $caloriesMax calories.")
}

fun day1Task2(){
    val lines = readInput("day1-input")!!

    var caloriesTotal = 0;
    var caloriesMaxList = mutableListOf<Int>();

    for(line in lines){
        if(line.isEmpty()){
            if(caloriesMaxList.size < 3){
                caloriesMaxList.add(caloriesTotal)
            } else {
                val minCalories = caloriesMaxList.minOf { it }

                if(caloriesTotal > minCalories){
                    val minIndex = caloriesMaxList.indexOf(minCalories)
                    caloriesMaxList[minIndex] = caloriesTotal
                }
            }

            caloriesTotal = 0;
        } else {
            caloriesTotal += line.toInt()
        }
    }

    val total = caloriesMaxList.sum()
    println("The top three elfs have a total of $total calories.")
}
