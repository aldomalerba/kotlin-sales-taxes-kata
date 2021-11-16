class BasketStringParser : BasketParser {
    override fun parse(basket: String): Basket {

        val items = basket.lines();
        return Basket(items.map {
            BasketItem(it.getQuantity(), it.getName().remove("imported"), it.getPrice(), it.isImported())
        })

    }

    private fun String.getQuantity() : Int = substringBefore(" ").toInt()
    private fun String.getPrice() : Double = substringAfterLast(" ").toDouble()
    private fun String.getName() : String = substringAfter(" ").substringBeforeLast("at").trim()
    private fun String.isImported() : Boolean = contains("imported")
    private fun String.remove(word: String) : String = split(" ").filterNot { it == word }.joinToString(" ")
}
