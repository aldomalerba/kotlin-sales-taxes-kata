import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

class BasketStringParserTest {

    @Test
    fun `parse a basket`() {

        val parser = BasketStringParser()

        val result = parser.parse("1 anyItem at 10.00\n1 anyItem at 5.00")

        assertThat(result.items).contains(BasketItem(1, "anyItem", 10.00))
        assertThat(result.items).contains(BasketItem(1, "anyItem", 5.00))
    }

}