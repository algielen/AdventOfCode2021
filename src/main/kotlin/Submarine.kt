import Instruction.Movement.*
import java.util.*
import java.util.stream.Collectors

class Submarine {
    var depthReadings = ArrayList<Int>()
    var instructions = ArrayList<Instruction>()
    var powerReadings = ArrayList<String>()
    var position = 0
    var depth = 0
    var aim = 0

    private fun readResource(path: String): String {
        val resource = javaClass.getResource(path)
        if (resource == null) {
            throw RuntimeException("Could not load resource file : $path")
        }
        val input = resource.readText()
        return input
    }

    fun loadDepths(path: String) {
        val input = readResource(path)
        val parsedDepths = input.split("\n").stream()
            .filter(String::isNotEmpty)
            .map(String::toInt)
            .collect(Collectors.toList())
        depthReadings.addAll(parsedDepths)
    }

    fun loadInstructions(path: String) {
        val input = readResource(path)
        val parsedInstructions = input.split("\n").stream()
            .filter(String::isNotEmpty)
            .map(Instruction.Companion::parse)
            .collect(Collectors.toList())
        instructions.addAll(parsedInstructions)
    }

    fun loadPowerReadings(path: String) {
        val input = readResource(path)
        val parsedInstructions = input.split("\n").stream()
            .filter(String::isNotEmpty)
            .collect(Collectors.toList())
        powerReadings.addAll(parsedInstructions)
    }


    fun countIncreases(): Int {
        var count = 0
        var previous = depthReadings.first()
        for (reading in depthReadings) {
            if (reading > previous) {
                count++
            }
            previous = reading
        }
        return count
    }

    fun countWindowsIncreases(): Int {
        if (depthReadings.size < 3) {
            throw IllegalStateException("Impossible to compare <3 reading by window")
        }
        var count = 0
        var previous = Triple(depthReadings[0], depthReadings[1], depthReadings[2])
        for (index in 2 until depthReadings.size) {
            val current = Triple(depthReadings[index - 2], depthReadings[index - 1], depthReadings[index])
            if (compareSum(current, previous)) {
                count++
            }
            previous = current
        }
        return count
    }

    private fun compareSum(current: Triple<Int, Int, Int>, previous: Triple<Int, Int, Int>) = current.first + current.second + current.third > previous.first + previous.second + previous.third

    fun forward(x: Int) {
        position += x
        depth += aim * x
    }

    fun down(x: Int) {
        aim += x
    }

    fun up(x: Int) {
        aim -= x
    }

    fun execute(instruction: Instruction) {
        when (instruction.movement) {
            FORWARD -> this.forward(instruction.distance)
            UP -> this.up(instruction.distance)
            DOWN -> this.down(instruction.distance)
        }
    }

    fun executeInstructions() {
        for (instruction in instructions) {
            execute(instruction)
        }
    }

    fun calculateGamma(): Int {
        val count = Array(lengthOfPowerReading()) { Array(2) { 0 } }
        for (powerReading in powerReadings) {
            for ((bitPosition, bit) in powerReading.withIndex()) {
                val value = Character.getNumericValue(bit)
                count[bitPosition][value]++
            }
        }

        val gammaArray = Arrays.stream(count)
            .map { indexOfMax(it) }
            .map { it.digitToChar() }
            .collect(Collectors.toList())
        val binaryString = String(gammaArray.toCharArray())
        val value = binaryString.toInt(2)
        return value
    }

    private fun lengthOfPowerReading() = powerReadings.first().length

    private fun indexOfMax(list: Array<Int>): Int {
        var max = list[0]
        var maxPosition = 0
        for ((i, value) in list.withIndex()) {
            if (value > max) {
                max = value
                maxPosition = i
            }
        }
        return maxPosition
    }

    fun calculateEpsilon(): Int { // TODO refactor
        val count = Array(lengthOfPowerReading()) { Array(2) { 0 } }
        for (epsilonValue in powerReadings) {
            for ((bitPosition, bit) in epsilonValue.withIndex()) {
                val value = Character.getNumericValue(bit)
                count[bitPosition][value]++
            }
        }

        val epsilonArray = Arrays.stream(count)
            .map { indexOfMin(it) }
            .map { it.digitToChar() }
            .collect(Collectors.toList())
        val binaryString = String(epsilonArray.toCharArray())
        val value = binaryString.toInt(2)
        return value
    }

    private fun indexOfMin(list: Array<Int>): Int {
        var max = list[0]
        var maxPosition = 0
        for ((i, value) in list.withIndex()) {
            if (value < max) {
                max = value
                maxPosition = i
            }
        }
        return maxPosition
    }
}