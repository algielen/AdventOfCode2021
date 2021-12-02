fun main(args: Array<String>) {
    println("Hello to advent of code 2021!")

    var submarine = Submarine()
    submarine.loadDepths("/input_1.txt")
    val increases = submarine.countIncreases()
    println("Simple increases : $increases")

    val windowIncreases = submarine.countWindowsIncreases()
    println("Window increases : $windowIncreases")
}