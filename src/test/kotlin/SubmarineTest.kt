import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SubmarineTest {

    @Test
    fun countIncreasesOneIncrease() {
        val sub = Submarine()
        sub.depthReadings.addAll(listOf(1, 2))
        val actual = sub.countIncreases()
        assertEquals(1, actual)
    }

    @Test
    fun countIncreasesTwoIncrease() {
        val sub = Submarine()
        sub.depthReadings.addAll(listOf(1, 2, 3))
        val actual = sub.countIncreases()
        assertEquals(2, actual)
    }

    @Test
    fun countIncreasesOneElement() {
        val sub = Submarine()
        sub.depthReadings.addAll(listOf(1))
        val actual = sub.countIncreases()
        assertEquals(0, actual)
    }

    @Test
    fun countIncreasesOneDecrease() {
        val sub = Submarine()
        sub.depthReadings.addAll(listOf(2, 1))
        val actual = sub.countIncreases()
        assertEquals(0, actual)
    }

    @Test
    fun countIncreasesOneIncreaseOneDecrease() {
        val sub = Submarine()
        sub.depthReadings.addAll(listOf(1, 2, 1))
        val actual = sub.countIncreases()
        assertEquals(1, actual)
    }

    @Test
    fun countWindowsIncreasesOneIncrease() {
        val sub = Submarine()
        sub.depthReadings.addAll(
            listOf(1, 1, 1, 2)
        )
        val actual = sub.countWindowsIncreases()
        assertEquals(1, actual)
    }

    @Test
    fun countWindowsIncreasesTwoIncreases() {
        val sub = Submarine()
        sub.depthReadings.addAll(
            listOf(1, 1, 1, 2, 2)
        )
        val actual = sub.countWindowsIncreases()
        assertEquals(2, actual)
    }

    @Test
    fun countWindowsIncreasesThreeIncreases() {
        val sub = Submarine()
        sub.depthReadings.addAll(
            listOf(1, 1, 1, 2, 2, 2)
        )
        val actual = sub.countWindowsIncreases()
        assertEquals(3, actual)
    }

    @Test
    fun countWindowsIncreasesOneDecrease() {
        val sub = Submarine()
        sub.depthReadings.addAll(
            listOf(2, 1, 1, 1)
        )
        val actual = sub.countWindowsIncreases()
        assertEquals(0, actual)
    }

    @Test
    fun subStartsAt0() {
        val sub = Submarine()
        assertEquals(0, sub.position)
        assertEquals(0, sub.depth)
    }

    @Test
    fun forwardIncreasesPosition() {
        val sub = Submarine()
        sub.forward(1)
        assertEquals(1, sub.position)
    }

    @Test
    fun downDoesntIncreasesDepth() {
        val sub = Submarine()
        sub.down(1)
        assertEquals(0, sub.depth)
    }

    @Test
    fun upDoesntDecreasesDepth() {
        val sub = Submarine()
        sub.up(1)
        assertEquals(0, sub.depth) //nobody told me submarine can't fly
    }

    @Test
    fun executeForwardInstruction() {
        val sub = Submarine()
        val instruction = Instruction(Instruction.Movement.FORWARD, 1)

        sub.execute(instruction)
        assertEquals(1, sub.position)
    }

    @Test
    fun executeForwardInstructions() {
        val sub = Submarine()
        sub.instructions.addAll(
            listOf(Instruction(Instruction.Movement.FORWARD, 1))
        )
        sub.executeInstructions()
        assertEquals(1, sub.position)
    }

    @Test
    fun executeTwoForwardInstructions() {
        val sub = Submarine()
        sub.instructions.addAll(
            listOf(
                Instruction(Instruction.Movement.FORWARD, 1),
                Instruction(Instruction.Movement.FORWARD, 2)
            )
        )
        sub.executeInstructions()
        assertEquals(3, sub.position)
    }

    @Test
    fun executeDownInstruction() {
        val sub = Submarine()
        val instruction = Instruction(Instruction.Movement.DOWN, 1)

        sub.execute(instruction)
        assertEquals(1, sub.aim)
    }

    @Test
    fun downIncreasesAim() {
        val sub = Submarine()
        sub.down(1)
        assertEquals(1, sub.aim)
    }

    @Test
    fun upDecreasesAim() {
        val sub = Submarine()
        sub.up(1)
        assertEquals(-1, sub.aim)
    }

    @Test
    fun downThenForward() {
        val sub = Submarine()
        sub.down(2)
        sub.forward(2)
        assertEquals(4, sub.depth)
    }

    @Test
    fun calculateGammaOneValue() {
        val sub = Submarine()
        sub.powerReadings.addAll(listOf("00100"))
        val gamma = sub.calculateGamma()

        assertEquals(4, gamma)
    }

    @Test
    fun calculateGammaTwoEqualValues() {
        val sub = Submarine()
        sub.powerReadings.addAll(listOf("00100", "00100"))
        val gamma = sub.calculateGamma()

        assertEquals(4, gamma)
    }

    @Test
    fun calculateGammaThreeValues() {
        val sub = Submarine()
        sub.powerReadings.addAll(listOf("10100", "10100", "01011"))
        val gamma = sub.calculateGamma()

        assertEquals(20, gamma)
    }

    @Test
    fun calculateEpsilonOneValue() {
        val sub = Submarine()
        sub.powerReadings.addAll(listOf("00100")) // inverse: 11011 =27
        val epsilon = sub.calculateEpsilon()

        assertEquals(27, epsilon)
    }

    @Test
    fun calculateEpsilonTwoValues() {
        val sub = Submarine()
        sub.powerReadings.addAll(listOf("00100", "00100"))
        val epsilon = sub.calculateEpsilon()

        assertEquals(27, epsilon)
    }

    @Test
    fun calculateEpsilonThreeValues() {
        val sub = Submarine()
        sub.powerReadings.addAll(listOf("00100", "00100", "11011"))
        val epsilon = sub.calculateEpsilon()

        assertEquals(27, epsilon)
    }
}