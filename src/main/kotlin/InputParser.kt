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

    fun loadBingoInstructions(path: String): BingoGame {
        val input = readResource(path)
        var file = input.split(System.lineSeparator())

        val instructions = file[0].split(",")
            .filter(String::isNotEmpty)
            .map { it.toInt() }

        file = file.drop(2) // drop instructions and empty line

        val boards = mutableListOf<BingoBoard>()

        val currentBoard = mutableListOf<List<Int>>()
        for (line in file) {
            if (line.isEmpty()) {
                val board = BingoBoard(currentBoard)
                boards.add(board)
                currentBoard.clear()

            } else {
                val numbers = line.split(" ")
                    .filter { it.isNotBlank() }
                    .map { it.toInt() }
                currentBoard.add(numbers)
            }
        }

        return BingoGame(boards, instructions)
    }
}