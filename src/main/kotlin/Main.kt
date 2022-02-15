fun main() {
    println("Hello to advent of code 2021!")

    val parser = InputParser()

    val submarine = Submarine()
    submarine.loadDepths(parser.loadDepths("/input_1.txt"))
    val increases = submarine.countIncreases()
    println("Simple increases : $increases")

    val windowIncreases = submarine.countWindowsIncreases()
    println("Window increases : $windowIncreases")

    submarine.loadInstructions(parser.loadInstructions("/input_2.txt"))
    println("Submarine is starting to move ...")
    submarine.executeInstructions()
    println("Submarine position after movement : pos : ${submarine.position}, depth : ${submarine.depth}")

    submarine.loadPowerReadings(parser.loadPowerReadings("/input_3.txt"))
    println("Deciphering power values ...")
    val gamma = submarine.calculateGamma()
    val epsilon = submarine.calculateEpsilon()
    println("Power readings : gamma $gamma, epsilon $epsilon")
    val powerConsumption = gamma * epsilon
    println("Final consumption : $powerConsumption")

    val oxygen = submarine.calculateOxygenRating()
    println("Oxygen rating :$oxygen")

    val co2 = submarine.calculateCO2Rating()
    println("CO2 rating :$co2")

    val lifeSupport = co2 * oxygen
    println("Life support rating : $lifeSupport")

    val bingo = parser.loadBingoInstructions("/input_4.txt")
    println("Bingo loaded, ${bingo.bingoBoards.size} boards, ${bingo.instructions.size} instructions")
    val gameResult = bingo.play()
    if (gameResult == BingoGame.GameResult.ONE_WINNER) {
        println("And we have a winner !")
        val winner = bingo.winnerBoards().first()
        println(winner)
        val boardScore = winner.calculateScore()
        val lastNumber = bingo.numbersPlayed().last()
        val finalScore = boardScore * lastNumber
        println("His score is $boardScore x $lastNumber = $finalScore")
    } else {
        println("Not the result we expected ... we have ${bingo.winnerBoards().size} winners")
    }

}