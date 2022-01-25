import java.util.stream.Collectors

class InputParser {
    private fun readResource(path: String): String {
        val resource = javaClass.getResource(path)
        if (resource == null) {
            throw RuntimeException("Could not load resource file : $path")
        }
        val input = resource.readText()
        return input
    }

    // TODO replace stream
    fun loadDepths(path: String): MutableList<Int>? {
        val input = readResource(path)
        val parsedDepths = input.split(System.lineSeparator()).stream()
            .filter(String::isNotEmpty)
            .map(String::toInt)
            .collect(Collectors.toList())
        return parsedDepths
    }

    fun loadInstructions(path: String): MutableList<Instruction>? {
        val input = readResource(path)
        val parsedInstructions = input.split(System.lineSeparator()).stream()
            .filter(String::isNotEmpty)
            .map(Instruction.Companion::parse)
            .collect(Collectors.toList())
        return parsedInstructions
    }

    fun loadPowerReadings(path: String): MutableList<String>? {
        val input = readResource(path)
        val parsedInstructions = input.split(System.lineSeparator()).stream()
            .filter(String::isNotEmpty)
            .collect(Collectors.toList())
        return parsedInstructions
    }
}