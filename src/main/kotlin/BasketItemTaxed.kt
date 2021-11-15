class BasketItemTaxed(val item: BasketItem,val taxes: Double) {

    fun totalPrice(): Double = (item.quantity * item.price) + taxes

}
