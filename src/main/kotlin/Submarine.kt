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

    fun loadDepths(parsedDepths: List<Int>) {
        depthReadings.addAll(parsedDepths)
    }

    fun loadInstructions(parsedInstructions: List<Instruction>) {
        instructions.addAll(parsedInstructions)
    }

    fun loadPowerReadings(parsedReadings: List<String>) {
        powerReadings.addAll(parsedReadings)
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

    // meh
    fun calculateGamma(): Int {
        val count = countBitValues()

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

    fun calculateEpsilon(): Int {
        val count = countBitValues()

        val epsilonArray = Arrays.stream(count)
            .map { indexOfMin(it) }
            .map { it.digitToChar() }
            .collect(Collectors.toList())
        val binaryString = String(epsilonArray.toCharArray())
        val value = binaryString.toInt(2)
        return value
    }

    private fun countBitValues(): Array<Array<Int>> {
        val count = Array(lengthOfPowerReading()) { Array(2) { 0 } }
        for (powerReading in powerReadings) {
            for ((bitPosition, bit) in powerReading.withIndex()) {
                val value = Character.getNumericValue(bit)
                count[bitPosition][value]++
            }
        }
        return count
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

    fun calculateOxygenRating(): Int {
        return calculateRating(OxygenCriteria())
    }

    fun calculateCO2Rating(): Int {
        return calculateRating(CO2Criteria())
    }

    fun calculateRating(criteria: Criteria<String>): Int {
        var filteredArrays = powerReadings.toMutableList()

        for (index in 0 until lengthOfPowerReading()) {
            val zeros = mutableListOf<String>()
            val ones = mutableListOf<String>()

            for (powerReading in filteredArrays) {
                when (powerReading[index]) {
                    '0' -> {
                        zeros += powerReading
                    }
                    '1' -> {
                        ones += powerReading
                    }
                    else -> throw IllegalArgumentException("Case not implemented in when : ${powerReading[index]}")
                }
            }

            filteredArrays = criteria.select(zeros, ones)
        }

        if (filteredArrays.size != 1) {
            if (allElementsAreEqual(filteredArrays)) {
                filteredArrays = mutableListOf(filteredArrays.first())
            } else {
                throw IllegalStateException("Failed to find the oxygen rating, final arrays : $filteredArrays")
            }
        }

        val remaining = filteredArrays.first()
        return remaining.toInt(2)
    }

    private fun allElementsAreEqual(filteredArrays: MutableList<String>): Boolean {
        if (filteredArrays.size > 1) {
            val first = filteredArrays.first()
            if (filteredArrays.all { first == it }) {
                return true
            }
        }
        return false
    }
}