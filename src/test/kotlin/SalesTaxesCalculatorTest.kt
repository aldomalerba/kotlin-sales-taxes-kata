import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

class SalesTaxesCalculatorTest {

    private val calculator = SalesTaxesCalculator()

    @Test
    fun `calculate base tax`() {
        val result = calculator.taxes(BasketItem(2, "anyItem", 10.00, false))

        assertThat(result).isEqualTo(2.00)
    }

    @Test
    fun `calculate base tax except books`() {
        val result = calculator.taxes(BasketItem(2, "book", 10.00, false))
        assertThat(result).isEqualTo(0.00)
    }

    @Test
    fun `calculate base tax except food`() {
        val result = calculator.taxes(BasketItem(2, "chocolate bar", 10.00, false))
        val result2 = calculator.taxes(BasketItem(2, "box of chocolates", 10.00, false))
        assertThat(result + result2).isEqualTo(0.00)
    }

    @Test
    fun `calculate base tax except medical products`() {
        val result = calculator.taxes(BasketItem(2, "pills", 10.00, false))
        assertThat(result).isEqualTo(0.00)
    }

    @Test
    fun `calculate additional imported tax`() {
        val basketItem = BasketItem(3, "imported box of chocolates", 11.25, true)
        val result = calculator.taxes(basketItem)
        assertThat(basketItem.totalPrice() + result).isEqualTo(35.55)
    }
}