package e2e

import BasketStringParser
import ReceiptPrinter
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class PrintReceiptAcceptanceTest {

    @Test
    fun `Input 1`() {

        val printer = ReceiptPrinter(BasketStringParser())

        val result = printer.print(
                  """2 book at 12.49
                    |1 music CD at 14.99
                    |1 chocolate bar at 0.85""")

        assertThat(result).isEqualTo(
              """2 book: 24.98
                |1 music CD: 16.49
                |1 chocolate bar: 0.85
                |Sales Taxes: 1.50
                |Total: 42.32"""
        )

    }

}