class ReceiptPrinter(private val basketParser: BasketParser, private val taxesCalculator: TaxesCalculator) {

    fun print(basketString: String): String {

        val basket = basketParser.parse(basketString)

        val itemsTaxed = basket.items.map { BasketItemTaxed(it,taxesCalculator.taxes(it)) }

        val items = itemsTaxed.joinToString("\n") {
            "${it.item.quantity} ${it.item.name}: ${it.totalPrice().toTwoDecimals()}"
        }

        val footer = "Sales Taxes: ${itemsTaxed.sumOf { it.taxes }.toTwoDecimals()}\nTotal: ${basket.totalPrice().toTwoDecimals()}"

        return listOf(items,footer).joinToString("\n").trimIndent()

    }

    private fun Double.toTwoDecimals(): String = String.format("%.2f", this)

}
