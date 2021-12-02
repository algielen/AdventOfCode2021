import Instruction.Movement.*
import java.util.stream.Collectors

class Submarine {
    var depthReadings = ArrayList<Int>()
    var instructions = ArrayList<Instruction>()
    var position = 0
    var depth = 0

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

    fun forward(i: Int) {
        position += i
    }

    fun down(i: Int) {
        depth += i
    }

    fun up(i: Int) {
        depth -= i
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
}