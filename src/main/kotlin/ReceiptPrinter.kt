class ReceiptPrinter(private val basketParser: BasketParser, private val taxesCalculator: TaxesCalculator) {

    fun print(basketString: String): String {

        val basket = basketParser.parse(basketString)

        val itemsTaxed = basket.items.map { taxesCalculator.from(it) }

        val items = itemsTaxed.map {
            "${it.basketItem.quantity} ${it.basketItem.name}: ${((it.basketItem.price + it.tax)*it.basketItem.quantity).toTwoDecimals()}"
        }.joinToString("\n")

        val footer = "Sales Taxes: ${itemsTaxed.sumOf { it.tax }.toTwoDecimals()}\nTotal: ${basket.items.sumOf { it.price }.toTwoDecimals()}"

        return listOf(items,footer).joinToString("\n").trimIndent()

    }

    private fun Double.toTwoDecimals(): String = String.format("%.2f", this)

}
