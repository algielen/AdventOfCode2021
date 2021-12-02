fun main(args: Array<String>) {
    println("Hello to advent of code 2021!")

    var submarine = Submarine()
    submarine.loadDepths("/input_1.txt")
    println(submarine.countIncreases())
}