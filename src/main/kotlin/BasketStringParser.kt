class BasketStringParser : BasketParser {
    override fun parse(basket: String): Basket {

        val items = basket.lines();
        return Basket(items.map { BasketItem(it.getQuantity(), it.getName(), it.getPrice()) })

    }

    private fun String.getQuantity() : Int = substringBefore(" ").toInt()
    private fun String.getPrice() : Double = substringAfterLast(" ").toDouble()
    private fun String.getName() : String = substringAfter(" ").substringBeforeLast("at").trim()
}
