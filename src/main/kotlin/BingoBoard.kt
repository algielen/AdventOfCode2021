class BingoBoard(val width: Int, val height: Int) {
    var board = Array(height) { Array(width) { Case(0, height, width) } }
    var finalNumber = -1

    constructor(values: List<List<Int>>) : this(values.first().size, values.size) {
        fill(values)
    }

    fun fill(values: List<List<Int>>) {
        // TODO check sizes
        // TODO check duplicates
        for ((i, list) in values.withIndex()) {
            for ((j, value) in list.withIndex()) {
                board[i][j] = Case(value, i, j)
            }
        }
    }

    fun announceValue(proposedValue: Int): RoundResult {
        for (list in board) {
            for (case in list) {
                if (case.value == proposedValue && !case.matched) {
                    case.matched = true
                    if (checkLineIsBingo(case.i) || checkRowIsBingo(case.j)) {
                        finalNumber = proposedValue
                        return RoundResult.BINGO
                    }
                    return RoundResult.HIT
                }
            }
        }
        return RoundResult.MISS
    }

    private fun checkRowIsBingo(row: Int): Boolean {
        for (i in 0 until height) {
            if (!board[i][row].matched) {
                return false
            }
        }
        return true
    }

    private fun checkLineIsBingo(line: Int): Boolean {
        return board[line].all { it.matched }
    }

    fun calculateRemainingNumbersScore(): Int {
        val sumOfUnmatched = board.flatMap { it.asIterable() }
            .filter { !it.matched }
            .sumOf { it.value }
        return sumOfUnmatched
    }

    fun calculateFinalScore(): Int {
        return calculateRemainingNumbersScore() * finalNumber
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for (cases in board) {
            for (case in cases) {
                val paddedValue = case.value.toString().padStart(2, ' ')
                builder.append(paddedValue)
                builder.append(' ')
            }
            builder.append('\n')
        }
        return builder.toString()
    }


    enum class RoundResult {
        HIT,
        MISS,
        BINGO,
        UNKNOWN
    }
}