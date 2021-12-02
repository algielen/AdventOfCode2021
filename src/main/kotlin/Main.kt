fun main(args: Array<String>) {
    println("Hello to advent of code 2021!")

    var submarine = Submarine()
    submarine.loadDepths("/input_1.txt")
    val increases = submarine.countIncreases()
    println("Simple increases : $increases")

    val windowIncreases = submarine.countWindowsIncreases()
    println("Window increases : $windowIncreases")

    submarine.loadInstructions("/input_2.txt")
    println("Submarine is starting to move ...")
    submarine.executeInstructions()
    println("Submarine position after movement : pos : ${submarine.position}, depth : ${submarine.depth}")
}