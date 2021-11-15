import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SalesTaxesCalculatorTest {

    private val calculator = SalesTaxesCalculator()

    @Test
    fun `calculate base tax`() {
        val result = calculator.taxes(BasketItem(2, "anyItem", 10.00))

        assertThat(result).isEqualTo(2.00)
    }

    @Test
    fun `calculate base tax except books`() {
        val result = calculator.taxes(BasketItem(2, "book", 10.00))
        assertThat(result).isEqualTo(0.00)
    }

    @Test
    fun `calculate base tax except food`() {
        val result = calculator.taxes(BasketItem(2, "chocolate bar", 10.00))
        assertThat(result).isEqualTo(0.00)
    }
}