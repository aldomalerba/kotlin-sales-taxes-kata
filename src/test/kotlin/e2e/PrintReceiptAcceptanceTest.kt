package e2e

import BasketStringParser
import ReceiptPrinter
import SalesTaxesCalculator
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test


class PrintReceiptAcceptanceTest {

    @Test
    fun `Input 1`() {

        val printer = ReceiptPrinter(BasketStringParser(), SalesTaxesCalculator())

        val result = printer.print("""
            2 book at 12.49
            1 music CD at 14.99
            1 chocolate bar at 0.85""".trimIndent()
        )

        assertThat(result).isEqualTo("""
            2 book: 24.98
            1 music CD: 16.49
            1 chocolate bar: 0.85
            Sales Taxes: 1.50
            Total: 42.32""".trimIndent())

    }

    @Test
    fun `Input 2`() {

        val printer = ReceiptPrinter(BasketStringParser(), SalesTaxesCalculator())

        val result = printer.print("""
            1 imported box of chocolates at 10.00
            1 imported bottle of perfume at 47.50""".trimIndent()
        )

        assertThat(result).isEqualTo("""
            1 imported box of chocolates: 10.50
            1 imported bottle of perfume: 54.65
            Sales Taxes: 7.65
            Total: 65.15""".trimIndent())

    }

    @Test
    fun `Input 3`() {

        val printer = ReceiptPrinter(BasketStringParser(), SalesTaxesCalculator())

        val result = printer.print("""
            1 imported bottle of perfume at 27.99
            1 bottle of perfume at 18.99
            1 packet of headache pills at 9.75
            3 box of imported chocolates at 11.25""".trimIndent()
        )

        assertThat(result).isEqualTo("""
            1 imported bottle of perfume: 32.19
            1 bottle of perfume: 20.89
            1 packet of headache pills: 9.75
            3 imported box of chocolates: 35.55
            Sales Taxes: 7.90
            Total: 98.38""".trimIndent())

    }
}