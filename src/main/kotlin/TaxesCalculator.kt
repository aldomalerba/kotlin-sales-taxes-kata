interface TaxesCalculator {
    fun from(basketItem: BasketItem) : BasketItemTaxed
}
