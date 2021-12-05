import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Assignments {

    @Test
    fun day1() {
        val submarine = Submarine()
        submarine.loadDepths("/input_1.txt")
        val increases = submarine.countIncreases()

        assertEquals(1711, increases)
    }

    @Test
    fun day1b() {
        val submarine = Submarine()
        submarine.loadDepths("/input_1.txt")
        val increases = submarine.countWindowsIncreases()

        assertEquals(1743, increases)
    }

    @Test
    fun day2() {
        val submarine = Submarine()
        submarine.loadInstructions("/input_2.txt")
        submarine.executeInstructions()
        assertEquals(1864715580, submarine.position * submarine.depth)
    }
}