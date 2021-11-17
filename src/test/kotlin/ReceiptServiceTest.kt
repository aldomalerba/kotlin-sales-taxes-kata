import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

class ReceiptServiceTest{

    private val taxesCalculator = mockk<TaxesCalculator>(relaxed = true)
    private val basketParser = mockk<BasketParser>(relaxed = true)
    private val service = ReceiptService(basketParser, taxesCalculator, ReceiptPrinter())

    @Test
    fun `print a receipt with one item`() {

        val anyBasketItem = BasketItem(1, "anyItem", 10.00, false)

        every { basketParser.parse(any()) } returns Basket(listOf(anyBasketItem))

        every { taxesCalculator.taxes(any()) } returns 0.00

        val result = service.print("anyBasketWithOneItems")

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
                BasketItem(1, "anyItem 1", 1.00, false),
                BasketItem(1, "anyItem 2", 2.00, false)
            )
        )

        every { taxesCalculator.taxes(any()) } returns 0.00 andThen 0.00


        val result = service.print("anyBasketWithTwoItems")

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
                BasketItem(2, "anyItem 1", 1.00, false),
                BasketItem(1, "anyItem 2", 2.00, false)
            )
        )

        every { taxesCalculator.taxes(any()) } returns 3.00 andThen 1.00

        val result = service.print("anyBasket")

        assertThat(result).isEqualTo("""
            2 anyItem 1: 5.00
            1 anyItem 2: 3.00
            Sales Taxes: 4.00
            Total: 8.00
        """.trimIndent())
    }

    @Test
    fun `print imported BasketItems`() {

        every { basketParser.parse(any()) } returns Basket(
            listOf(
                BasketItem(1, "anyItem 1", 1.00, true)
            )
        )

        every { taxesCalculator.taxes(any()) } returns 0.00

        val result = service.print("anyBasket")

        assertThat(result).isEqualTo("""
            1 imported anyItem 1: 1.00
            Sales Taxes: 0.00
            Total: 1.00
        """.trimIndent())
    }
}