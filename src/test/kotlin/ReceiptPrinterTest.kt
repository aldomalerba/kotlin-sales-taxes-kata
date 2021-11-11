import io.mockk.every
import io.mockk.verify
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReceiptPrinterTest{

    @Test
    fun `print with empty string return Sales Taxes 0 And Total 0`() {

        val printer = ReceiptPrinter()

        val result = printer.print("")

        assertThat(result).isEqualTo("""
            |Sales Taxes: 0
            |Total: 0
        """)

    }
}