import java.util.stream.Collectors

class Submarine {
    var depthReadings = ArrayList<Int>()

    fun loadDepths(path: String) {
        val resource = javaClass.getResource(path)
        if (resource == null) {
            throw RuntimeException("Could not load resource file : $path")
        }
        val input = resource.readText()
        val depths = input.split("\n").stream()
            .filter(String::isNotEmpty)
            .map(String::toInt)
            .collect(Collectors.toList())
        depthReadings.addAll(depths)
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
}