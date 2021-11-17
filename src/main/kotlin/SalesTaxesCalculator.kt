import kotlin.math.ceil
class SalesTaxesCalculator : TaxesCalculator {

    override fun taxes(basketItem: BasketItem): Double {
        return (basesSalesTax(basketItem) + importedTax(basketItem)) * basketItem.quantity
    }

    private fun importedTax(basketItem: BasketItem) = if (basketItem.imported) { (basketItem.price * 0.05).round() } else 0.00

    private fun basesSalesTax(basketItem: BasketItem) = if (isExcept(basketItem)) 0.00 else (basketItem.price * 0.1).round()

    private fun Double.round() = ceil(this * 20.0) / 20.0;

    private fun isExcept(basketItem: BasketItem) =
        basketItem.name == "book" || basketItem.name.contains("chocolate") || basketItem.name.contains("pills")


}
