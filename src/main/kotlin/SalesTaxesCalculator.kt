class SalesTaxesCalculator : TaxesCalculator {
    override fun taxes(basketItem: BasketItem): Double {
        return (basketItem.quantity * basketItem.price) * 0.1
    }

}
