class ReceiptPrinter(private val basketParser: BasketParser) {

    fun print(basketString: String): String {

        val basket = basketParser.parse(basketString)

        val items = basket.items.joinToString("\n") { "${it.quantity} ${it.name}: ${it.price.toTwoDecimals()}" }

        val footer = "Sales Taxes: 0.00\nTotal: ${basket.items.sumOf { it.price }.toTwoDecimals()}"

        return listOf(items,footer).joinToString("\n").trimIndent()

    }

    private fun Double.toTwoDecimals(): String = String.format("%.2f", this)

}
