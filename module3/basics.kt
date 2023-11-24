//all code in Kotlin is written inside functions
//main function is the entry point of every Kotlin program
fun main() {
    println("Hello, world!")

    val name = "Kotlin"
    println("Hello, $name!")

    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
    for ((index, value) in items.withIndex()) {
        println("the element at $index is $value")
    }
    //array.indexOf(elementToFind)
    var index = items.indexOf("banana")
    val indexFirst = items.indexOfFirst { it.startsWith("a") }
    val indexLast = items.indexOfLast { it.startsWith("a") }
    println("index: $index, indexFirst: $indexFirst, indexLast: $indexLast")

    while(index < items.size) {
        println("items[$index] = ${items[index]}")
        index++
    }

    //breaking the outer loop
    loop@ for (i in 1..100) {
        for (j in 1..100) {
            if (i > 10) break@loop
        }
    }

    val x = 10
    if (x in 1..10) {
        println("fits in range")
    }

    for (x in 1..5) {
        print(x)
    }

    //step is the increment value
    for (x in 1..10 step 2) {
        print(x)
    }

    println("\ndownTo and step")
    //downTo is the decrement value
    for (x in 9 downTo 0 step 3) {
        print(x)
    }

}//end main
fun funny(text: String?) {
	if (text != null)
		println(text)
	else
		println("Nothing to print :(")
}
interface RegularCat {
    fun pet()
    fun feed(food: Food)
}

interface SickCat {
    fun checkStomach()
    fun giveMedicine(pill: Pill)
}



