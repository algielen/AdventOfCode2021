import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class HelloTest {

    @Test
    @Disabled
    internal fun hello() {
        assert("hello" != "hello")
    }
}