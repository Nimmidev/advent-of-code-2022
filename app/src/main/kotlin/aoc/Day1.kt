package aoc

import readInput

fun day1Task1(){
    var caloriesTotal = 0;
    var result = 0;

    for(line in readInput("day1-input")!!){
        if(line.isEmpty()){
            if(caloriesTotal > result) result = caloriesTotal;
            caloriesTotal = 0;
        } else {
            caloriesTotal += line.toInt()
        }
    }

    println("Result: $result")
    assert(result == 69528)
}

fun day1Task2(){
    var caloriesTotal = 0;
    var caloriesMaxList = mutableListOf<Int>();

    for(line in readInput("day1-input")!!){
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

    val result = caloriesMaxList.sum()
    println("Result: $result")
    assert(result == 206152)
}
