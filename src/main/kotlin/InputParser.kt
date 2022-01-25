class InputParser {
    private fun readResource(path: String): String {
        val resource = javaClass.getResource(path)
        if (resource == null) {
            throw RuntimeException("Could not load resource file : $path")
        }
        val input = resource.readText()
        return input
    }

    fun loadDepths(path: String): List<Int> {
        val input = readResource(path)
        val parsedDepths = input.split(System.lineSeparator())
            .filter(String::isNotEmpty)
            .map(String::toInt)
        return parsedDepths
    }

    fun loadInstructions(path: String): List<Instruction> {
        val input = readResource(path)
        val parsedInstructions = input.split(System.lineSeparator())
            .filter(String::isNotEmpty)
            .map(Instruction.Companion::parse)
        return parsedInstructions
    }

    fun loadPowerReadings(path: String): List<String> {
        val input = readResource(path)
        val parsedInstructions = input.split(System.lineSeparator())
            .filter(String::isNotEmpty)
        return parsedInstructions
    }
}