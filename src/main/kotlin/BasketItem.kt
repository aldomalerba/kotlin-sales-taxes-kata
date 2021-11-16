class BasketItem(val quantity: Int, val name: String, val price: Double, val imported: Boolean) {

    fun totalPrice() : Double = quantity * price

}
