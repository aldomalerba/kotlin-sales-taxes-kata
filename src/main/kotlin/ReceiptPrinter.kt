class ReceiptPrinter(private val basketParser: BasketParser, val taxesCalculator: TaxesCalculator) {

    fun print(basketString: String): String {

        val basket = basketParser.parse(basketString)

        val itemsWithTaxes = basket.items.map { it to taxesCalculator.taxes(it) }

        val items = itemsWithTaxes.joinToString("\n") {
            "${it.first.quantity} ${it.first.name}: ${((it.first.quantity * it.first.price) + it.second).toTwoDecimals()}"
        }

        val footer = "Sales Taxes: ${itemsWithTaxes.sumOf { it.second }.toTwoDecimals()}\nTotal: ${basket.totalPrice().toTwoDecimals()}"

        return listOf(items,footer).joinToString("\n").trimIndent()

    }

    private fun Double.toTwoDecimals(): String = String.format("%.2f", this)

}
