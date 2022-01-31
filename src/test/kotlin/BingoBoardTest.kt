import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BingoBoardTest {

    @Test
    fun fillAndMarkOne() {
        val bingo = BingoBoard(1, 1)
        bingo.fill(listOf(listOf(3)))

        assertFalse(bingo.board[0][0].matched)

        val hit = bingo.tryValue(3)

        assertTrue(hit)
        assertTrue(bingo.board[0][0].matched)
    }

    @Test
    fun fillAndMarkOneInSquareMatrixX() {
        val bingo = BingoBoard(2, 2)
        bingo.fill(
            listOf(
                listOf(1, 3),
                listOf(2, 4)
            )
        )

        assertFalse(bingo.board[0][1].matched)
        assertEquals(3, bingo.board[0][1].value)

        val hit = bingo.tryValue(3)

        assertTrue(hit)
        assertTrue(bingo.board[0][1].matched)
    }

    @Test
    fun fillAndMarkOneInSquareMatrixY() {
        val bingo = BingoBoard(2, 2)
        bingo.fill(
            listOf(
                listOf(1, 3),
                listOf(2, 4)
            )
        )

        assertFalse(bingo.board[1][0].matched)
        assertEquals(2, bingo.board[1][0].value)

        val hit = bingo.tryValue(2)

        assertTrue(hit)
        assertTrue(bingo.board[1][0].matched)
    }

    @Test
    fun fillAndMarkOneInRectangularMatrix() {
        val bingo = BingoBoard(3, 2)
        bingo.fill(
            listOf(
                listOf(1, 2, 3),
                listOf(5, 4, 6)
            )
        )

        assertFalse(bingo.board[0][2].matched)
        assertEquals(3, bingo.board[0][2].value)

        val hit = bingo.tryValue(3)

        assertTrue(hit)
        assertTrue(bingo.board[0][2].matched)
    }
}