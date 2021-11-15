class BasketItem(val quantity: Int, val name: String, val price: Double, val tax: Double) {

    fun taxedPrice(): Double = (price + tax) * quantity

}
