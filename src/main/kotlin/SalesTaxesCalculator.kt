class SalesTaxesCalculator : TaxesCalculator {
    override fun taxes(basketItem: BasketItem): Double {
        return basesSalesTax(basketItem)
    }

    private fun basesSalesTax(basketItem: BasketItem) : Double {
        return if(isExcept(basketItem)) 0.00
        else basketItem.totalPrice() * 0.1
    }

    private fun isExcept(basketItem: BasketItem) = basketItem.name == "book" || basketItem.name == "chocolate bar"

}
