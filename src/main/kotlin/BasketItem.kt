class BasketItem(val quantity: Int, val name: String, val price: Double) {

    fun totalPrice() : Double = quantity * price

}
