class BingoGame(val bingoBoards: List<BingoBoard>, val instructions: List<Int>) {
    private val winners = mutableListOf<BingoBoard>()
    private val numbersPlayed = mutableListOf<Int>()

    fun play(): GameResult {
        for (instruction in instructions) {
            for (board in bingoBoards) {

                val roundResult = board.announceValue(instruction)
                if (roundResult == BingoBoard.RoundResult.BINGO) {
                    winners.add(board)
                }
            }
            numbersPlayed.add(instruction)

            if (winners.isNotEmpty()) {
                if (winners.size == 1) {
                    return GameResult.ONE_WINNER
                } else {
                    return GameResult.MULTIPLE_WINNERS
                }
            }
        }
        return GameResult.NO_WINNER
    }

    fun winnerBoards() = winners

    fun numbersPlayed() = numbersPlayed

    enum class GameResult {
        NO_WINNER,
        ONE_WINNER,
        MULTIPLE_WINNERS,
    }

}

