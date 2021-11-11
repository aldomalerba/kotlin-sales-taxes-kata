class ReceiptPrinter(val basketParser: BasketParser) {

    fun print(basketString: String): String {

        val basket = basketParser.parse(basketString)
        val item = basket.items.first();

        return """
            ${item.quantity} ${item.name}: ${String.format("%.2f", item.price)}
            Sales Taxes: 0
            Total: 0
            """.trimIndent()

    }

}
