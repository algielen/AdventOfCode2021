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
}