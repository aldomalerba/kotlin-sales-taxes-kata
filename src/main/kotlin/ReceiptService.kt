class ReceiptService(private val basketParser: BasketParser, private val taxesCalculator: TaxesCalculator,private val receiptPrinter: Printer) {

    fun print(basketString: String): String {
        val basket = basketParser.parse(basketString)

        val itemsTaxed = basket.items.map { BasketItemTaxed(it,taxesCalculator.taxes(it)) }

        return receiptPrinter.print(itemsTaxed)
    }

}
