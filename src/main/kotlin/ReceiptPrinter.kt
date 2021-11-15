class ReceiptPrinter(private val basketParser: BasketParser) {

    fun print(basketString: String): String {

        val basket = basketParser.parse(basketString)


        val items = basket.items.joinToString("\n") {
            "${it.quantity} ${it.name}: ${it.taxedPrice().toTwoDecimals()}"
        }

        val footer = "Sales Taxes: ${basket.salesTaxes().toTwoDecimals()}\nTotal: ${basket.totalPrice().toTwoDecimals()}"

        return listOf(items,footer).joinToString("\n").trimIndent()

    }

    private fun Double.toTwoDecimals(): String = String.format("%.2f", this)

}
