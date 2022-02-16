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

        val gameResult = game.play()

        assertEquals(BingoGame.GameResult.ONE_WINNER, gameResult)
    }

    @Test
    fun getWinnerBoard() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(3)))

        val instructions = listOf(3)
        val game = BingoGame(listOf(board), instructions)

        game.play()
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

        game.play()
        val winnerBoards = game.winnerBoards()

        assertContains(winnerBoards, board2)
    }

    @Test
    fun getWinnerBoardNoWinnerGetsEmptyList() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(3)))

        val instructions = listOf(99)
        val game = BingoGame(listOf(board), instructions)

        game.play()
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

        val gameResult = game.play()

        assertEquals(BingoGame.GameResult.MULTIPLE_WINNERS, gameResult)
    }

    @Test
    fun playNoWinner() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(99)))

        val instructions = listOf(3)
        val game = BingoGame(listOf(board), instructions)

        val gameResult = game.play()

        assertEquals(BingoGame.GameResult.NO_WINNER, gameResult)
    }

    @Test
    fun lastValuePlayed() {
        val board = BingoBoard(1, 1)
        board.fill(listOf(listOf(99)))

        val instructions = listOf(1, 2, 3)
        val game = BingoGame(listOf(board), instructions)
        game.play()

        val last = game.numbersPlayed().last()

        assertEquals(3, last)
    }
}