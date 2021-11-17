class ReceiptPrinter : Printer {

    override fun print(itemsTaxed: List<BasketItemTaxed>): String {

        val items = itemsTaxed.joinToString("\n") {
            "${it.item.quantity}${imported(it.item)}${it.item.name}: ${it.totalPrice().toTwoDecimals()}"
        }

        val salesTaxes = itemsTaxed.sumOf { it.taxes }
        val total = itemsTaxed.sumOf { it.totalPrice() }

        val footer =
            "Sales Taxes: ${salesTaxes.toTwoDecimals()}\nTotal: ${total.toTwoDecimals()}"

        return listOf(items, footer).joinToString("\n")

    }

    private fun imported(item: BasketItem): String {
        return if (item.imported) " imported " else " ";
    }

    private fun Double.toTwoDecimals(): String = String.format("%.2f", this)

}
