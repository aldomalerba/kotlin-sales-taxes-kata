import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

class ReceiptPrinterTest{

    private val basketParser = mockk<BasketParser>()
    private val printer = ReceiptPrinter(basketParser)

    @Test
    fun `print an empty basket`() {

        val result = printer.print("")

        assertThat(result).isEqualTo("""
            Sales Taxes: 0
            Total: 0
        """.trimIndent())

    }


    @Test
    fun `print a basket with one item parsed correctly`() {

        every { basketParser.parse(any()) } returns Basket(
            listOf(BasketItem(1, "anyItem", 10.00))
        )

        val result = printer.print("anyItem")

        assertThat(result).isEqualTo("""
            1 anyItem: 10.00
            Sales Taxes: 0
            Total: 0
        """.trimIndent())
    }
}