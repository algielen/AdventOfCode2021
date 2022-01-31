class BingoBoard(width: Int, height: Int) {
    var board = Array(height) { Array(width) { Case(0, false) } }

    fun fill(values: List<List<Int>>) {
        // TODO check sizes
        // TODO check duplicates
        for ((h, list) in values.withIndex()) {
            for ((w, value) in list.withIndex()) {
                board[h][w] = Case(value, false)
            }
        }
    }

    fun tryValue(proposedValue: Int): Boolean {
        for (list in board) {
            for (case in list) {
                if (case.value == proposedValue && !case.matched) {
                    case.matched = true
                    return true
                }
            }
        }
        return false
    }
}