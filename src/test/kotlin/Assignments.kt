import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Assignments {

    @Test
    fun day1() {
        val submarine = Submarine()
        val parser = InputParser()
        submarine.loadDepths(parser.loadDepths("/input_1.txt"))
        val increases = submarine.countIncreases()

        assertEquals(1711, increases)
    }

    @Test
    fun day1b() {
        val submarine = Submarine()
        val parser = InputParser()
        submarine.loadDepths(parser.loadDepths("/input_1.txt"))
        val increases = submarine.countWindowsIncreases()

        assertEquals(1743, increases)
    }

    @Test
    fun day2() {
        val submarine = Submarine()
        val parser = InputParser()
        submarine.loadInstructions(parser.loadInstructions("/input_2.txt"))
        submarine.executeInstructions()
        assertEquals(1864715580, submarine.position * submarine.depth)
    }

    @Test
    fun day3() {
        val submarine = Submarine()
        val parser = InputParser()
        submarine.loadPowerReadings(parser.loadPowerReadings("/input_3.txt"))
        val gamma = submarine.calculateGamma()
        val epsilon = submarine.calculateEpsilon()
        val powerConsumption = gamma * epsilon
        assertEquals(4191876, powerConsumption)
    }

    @Test
    fun day3bis() {
        val submarine = Submarine()
        val parser = InputParser()
        submarine.loadPowerReadings(parser.loadPowerReadings("/input_3.txt"))
        val oxygen = submarine.calculateOxygenRating()

        val co2 = submarine.calculateCO2Rating()
        val lifeSupport = co2 * oxygen
        assertEquals(3414905, lifeSupport)
    }


}