import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertContains

internal class BingoGameTest {

    @Test
    fun playOneWinner() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(3)))

        val instructions = listOf(3)
        val game = BingoGame(listOf(board), instructions)

        val gameResult = game.play(BingoGame.RuleSet.CLASSICAL)

        assertEquals(BingoGame.GameResult.ONE_WINNER, gameResult)
    }

    @Test
    fun getWinnerBoard() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(3)))

        val instructions = listOf(3)
        val game = BingoGame(listOf(board), instructions)

        game.play(BingoGame.RuleSet.CLASSICAL)
        val winnerBoards = game.winnerBoards()

        assertContains(winnerBoards, board)
    }

    @Test
    fun getCorrectWinnerBoard() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(99)))
        val board2 = BingoBoard(1, 1)
        board2.fill(listOf(listOf(3)))

        val instructions = listOf(3)
        val game = BingoGame(listOf(board, board2), instructions)

        game.play(BingoGame.RuleSet.CLASSICAL)
        val winnerBoards = game.winnerBoards()

        assertContains(winnerBoards, board2)
    }

    @Test
    fun getWinnerBoardNoWinnerGetsEmptyList() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(3)))

        val instructions = listOf(99)
        val game = BingoGame(listOf(board), instructions)

        game.play(BingoGame.RuleSet.CLASSICAL)
        val winnerBoards = game.winnerBoards()

        assertEquals(0, winnerBoards.size)
    }

    @Test
    fun playTwoWinners() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(3)))
        val board2 = BingoBoard(1, 1)
        board2.fill(listOf(listOf(3)))

        val instructions = listOf(3)
        val game = BingoGame(listOf(board, board2), instructions)

        val gameResult = game.play(BingoGame.RuleSet.CLASSICAL)

        assertEquals(BingoGame.GameResult.MULTIPLE_WINNERS, gameResult)
    }

    @Test
    fun playNoWinner() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(99)))

        val instructions = listOf(3)
        val game = BingoGame(listOf(board), instructions)

        val gameResult = game.play(BingoGame.RuleSet.CLASSICAL)

        assertEquals(BingoGame.GameResult.NO_WINNER, gameResult)
    }

    @Test
    fun lastValuePlayed() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(99)))

        val instructions = listOf(1, 2, 3)
        val game = BingoGame(listOf(board), instructions)
        game.play(BingoGame.RuleSet.CLASSICAL)

        val last = game.numbersPlayed().last()

        assertEquals(3, last)
    }

    @Test
    fun playToTheEndMultipleWinners() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(3)))
        val board2 = BingoBoard(1, 1)
        board2.fill(listOf(listOf(4)))

        val instructions = listOf(3, 4)
        val game = BingoGame(listOf(board, board2), instructions)

        val gameResult = game.play(BingoGame.RuleSet.TO_THE_END)

        assertEquals(BingoGame.GameResult.MULTIPLE_WINNERS, gameResult)
    }

    @Test
    fun playToTheEndOrderOfWinners() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(3)))
        val board2 = BingoBoard(1, 1)
        board2.fill(listOf(listOf(4)))

        val instructions = listOf(3, 4)
        val game = BingoGame(listOf(board, board2), instructions)

        val gameResult = game.play(BingoGame.RuleSet.TO_THE_END)

        assertEquals(game.winnerBoards()[0], board)
        assertEquals(game.winnerBoards()[1], board2)
    }

    @Test
    fun playToTheEndStopsAfterEveryoneWon() {
        val board = BingoBoard(2, 2)
        board.fill(
            listOf(
                listOf(1, 3),
                listOf(2, 4)
            )
        )

        val board2 = BingoBoard(2, 2)
        board2.fill(
            listOf(
                listOf(5, 7),
                listOf(6, 8)
            )
        )

        val instructions = listOf(1, 2, 5, 6, 7, 8)
        val game = BingoGame(listOf(board, board2), instructions)

        val gameResult = game.play(BingoGame.RuleSet.TO_THE_END)
        assertEquals(BingoGame.GameResult.MULTIPLE_WINNERS, gameResult)

        assertEquals(15, board2.calculateRemainingNumbersScore())
    }

    @Test
    fun playToTheEndStopsWithNoWinner() {
        val board = BingoBoard(2, 2)
        board.fill(
            listOf(
                listOf(1, 3),
                listOf(2, 4)
            )
        )

        val board2 = BingoBoard(2, 2)
        board2.fill(
            listOf(
                listOf(5, 7),
                listOf(6, 8)
            )
        )

        val instructions = listOf(10, 11, 12)
        val game = BingoGame(listOf(board, board2), instructions)

        val gameResult = game.play(BingoGame.RuleSet.TO_THE_END)
        assertEquals(BingoGame.GameResult.NO_WINNER, gameResult)
    }

    @Test
    fun playToTheEndBoardIsTakenOutOfGameAfterWinning() {
        val board = BingoBoard(2, 2)
        board.fill(
            listOf(
                listOf(1, 3),
                listOf(2, 4)
            )
        )

        val board2 = BingoBoard(2, 2)
        board2.fill(
            listOf(
                listOf(5, 7),
                listOf(6, 8)
            )
        )

        val instructions = listOf(1, 2, 5, 6, 3, 4)
        val game = BingoGame(listOf(board, board2), instructions)

        val gameResult = game.play(BingoGame.RuleSet.TO_THE_END)
        assertEquals(BingoGame.GameResult.MULTIPLE_WINNERS, gameResult)

        assertEquals(14, board.calculateRemainingNumbersScore())
    }
}