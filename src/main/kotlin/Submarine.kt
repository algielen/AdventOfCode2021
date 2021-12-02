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
}