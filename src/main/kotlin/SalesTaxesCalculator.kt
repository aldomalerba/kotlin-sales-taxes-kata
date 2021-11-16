import java.lang.Math.round
import kotlin.math.roundToInt

class SalesTaxesCalculator : TaxesCalculator {
    override fun taxes(basketItem: BasketItem): Double {
        val baseTax = basesSalesTax(basketItem)
        val importedTax = if(basketItem.imported) basketItem.totalPrice() * 0.05 else 0.00
        return (baseTax + importedTax).round()
    }

    private fun basesSalesTax(basketItem: BasketItem) : Double {
        return if(isExcept(basketItem)) 0.00
        else basketItem.totalPrice() * 0.1
    }

    private fun Double.round() : Double = (this * 20.0).roundToInt() / 20.0;

    private fun isExcept(basketItem: BasketItem) = basketItem.name == "book" || basketItem.name.contains("chocolate")

}
