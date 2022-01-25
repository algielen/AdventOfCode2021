class Instruction(public val movement: Movement, public val distance: Int) {
    enum class Movement {
        FORWARD,
        UP,
        DOWN
    }

    companion object {
        fun parse(line: String): Instruction {
            val (movementStr, distanceStr) = line.split(" ")
            val movement = Movement.valueOf(movementStr.uppercase())
            val distance = distanceStr.toInt()
            val instruction = Instruction(movement, distance)
            return instruction
        }
    }

}