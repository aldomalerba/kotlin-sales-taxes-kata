import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

class BasketStringParserTest {

    @Test
    fun `parse a basket`() {

        val parser = BasketStringParser()

        val result = parser.parse("1 anyItem 1 at 1.00\n2 anyItem 2 at 5.00")

        assertThat(result.items.first().quantity).isEqualTo(1)
        assertThat(result.items.first().name).isEqualTo("anyItem 1")
        assertThat(result.items.first().price).isEqualTo(1.00)
        assertThat(result.items.last().quantity).isEqualTo(2)
        assertThat(result.items.last().name).isEqualTo("anyItem 2")
        assertThat(result.items.last().price).isEqualTo(5.00)
    }

}