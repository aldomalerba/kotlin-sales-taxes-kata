data class Basket(val items: List<BasketItem>) {

    fun totalPrice(): Double = items.sumOf { it.price * it.quantity }

}
