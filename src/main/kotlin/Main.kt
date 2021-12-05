fun main() {
    println("Hello to advent of code 2021!")

    val submarine = Submarine()
    submarine.loadDepths("/input_1.txt")
    val increases = submarine.countIncreases()
    println("Simple increases : $increases")

    val windowIncreases = submarine.countWindowsIncreases()
    println("Window increases : $windowIncreases")

    submarine.loadInstructions("/input_2.txt")
    println("Submarine is starting to move ...")
    submarine.executeInstructions()
    println("Submarine position after movement : pos : ${submarine.position}, depth : ${submarine.depth}")

    submarine.loadPowerReadings("/input_3.txt")
    println("Deciphering power values ...")
    val gamma = submarine.calculateGamma()
    val epsilon = submarine.calculateEpsilon()
    println("Power readings : gamma $gamma, epsilon $epsilon")
    val powerConsumption = gamma * epsilon
    println("Final consumption : $powerConsumption")
}