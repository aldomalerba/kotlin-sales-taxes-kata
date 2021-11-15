import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SalesTaxesCalculatorTest {

    @Test
    fun `calculate base tax`() {
        val calculator = SalesTaxesCalculator()

        val result = calculator.taxes(BasketItem(2, "anyItem", 10.00))

        assertThat(result).isEqualTo(2.00)
    }

}