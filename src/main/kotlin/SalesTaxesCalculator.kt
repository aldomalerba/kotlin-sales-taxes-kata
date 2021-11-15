class SalesTaxesCalculator : TaxesCalculator {
    override fun taxes(basketItem: BasketItem): Double {
        return basesSalesTax(basketItem)
    }

    private fun basesSalesTax(basketItem: BasketItem) = basketItem.totalPrice() * 0.1


}
