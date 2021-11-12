import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

class ReceiptPrinterTest{

    private val taxesCalculator = mockk<TaxesCalculator>();
    private val basketParser = mockk<BasketParser>(relaxed = true)
    private val printer = ReceiptPrinter(basketParser, taxesCalculator)

    @Test
    fun `print an empty receipt`() {

        val result = printer.print("")

        assertThat(result).isEqualTo("""
            Sales Taxes: 0.00
            Total: 0.00
        """.trimIndent())

    }

    @Test
    fun `print a receipt with one item`() {

        every { basketParser.parse(any()) } returns Basket(
            listOf(BasketItem(1, "anyItem", 10.00))
        )

        val result = printer.print("anyBasketWithOneItems")

        assertThat(result).isEqualTo("""
            1 anyItem: 10.00
            Sales Taxes: 0.00
            Total: 10.00
        """.trimIndent())
    }

    @Test
    fun `print a receipt with more than one item`() {

        every { basketParser.parse(any()) } returns Basket(
            listOf(
                BasketItem(1, "anyItem 1", 1.00),
                BasketItem(1, "anyItem 2", 2.00)
            )
        )

        val result = printer.print("anyBasketWithTwoItems")

        assertThat(result).isEqualTo("""
            1 anyItem 1: 1.00
            1 anyItem 2: 2.00
            Sales Taxes: 0.00
            Total: 3.00
        """.trimIndent())
    }

    @Test
    fun `print apply sales taxes to BasketItems`() {

        every { basketParser.parse(any()) } returns Basket(
            listOf(
                BasketItem(1, "anyItem 1", 1.00),
                BasketItem(1, "anyItem 2", 2.00)
            )
        )

        every { taxesCalculator.from(any()) } returns
                BasketItemTaxed(BasketItem(1, "anyItem 1", 1.00), 1.50) andThen
                BasketItemTaxed(BasketItem(1, "anyItem 2", 2.00), 1.00)

        val result = printer.print("anyBasket")

        assertThat(result).isEqualTo("""
            1 anyItem 1: 2.50
            1 anyItem 2: 3.00
            Sales Taxes: 2.50
            Total: 3.00
        """.trimIndent())
    }

}