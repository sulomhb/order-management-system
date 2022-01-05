import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import kotlin.IllegalArgumentException



internal class OrderTest {

    @Test
     fun `80 red 2x2-pieces, 67 blue 1x2-pieces and 50 yellow 4x2-piece`() : Unit {
         val stock = Stock();
         val order = mapOf<String, Int>(
            pieceToString(2,2,"red") to 80,
            pieceToString(1,2,"blue") to 67,
            pieceToString(4,2,"yellow") to 50,
        );
        for((piece) in order) {
            stock.addPieceToStock(piece, 100);
        }
        val orderCanBeDelivered : Boolean = stock.checkIfOrderCanBeDelivered(order);
        assertThat(orderCanBeDelivered, equalTo(true));
    }

    @Test
    fun `30 blue 3x2 pieces, 150 purple 4x2 pieces`() : Unit {
        val stock = Stock();
        val order = mapOf<String, Int>(
            pieceToString(3,2,"red") to 30,
            pieceToString(4,2,"purple") to 150,
        );
        for((piece) in order) {
            stock.addPieceToStock(piece, 100);
        }
        val orderCanBeDelivered : Boolean = stock.checkIfOrderCanBeDelivered(order);
        assertThat(orderCanBeDelivered, equalTo(false));
    }

    @Test
    fun `23 blue 2x2 pieces, 47 green 3x2 pieces, 89 red 4x2 pieces, 70 black 5x2 pieces`() : Unit {
        val stock = Stock();
        val order = mapOf<String, Int>(
            pieceToString(2,2,"blue") to 23,
            pieceToString(3,2,"green") to 47,
            pieceToString(4,2,"red") to 89,
            pieceToString(5,2,"black") to 70,
        );
        for((piece) in order) {
            stock.addPieceToStock(piece, 100);
        }
        val orderCanBeDelivered : Boolean = stock.checkIfOrderCanBeDelivered(order);
        assertThat(orderCanBeDelivered, equalTo(true));
    }

    @Test(expected = IllegalArgumentException::class)
    fun `-1 red 1x2 piece, -50 2x2 green pieces`() : Unit {
        val stock = Stock();
        val order = mapOf<String, Int>(
            pieceToString(1,2,"red") to -1,
            pieceToString(2,2,"green") to -50,
        );
        for((piece) in order) {
            stock.addPieceToStock(piece, 100);
        }
        val orderCanBeDelivered : Boolean = stock.checkIfOrderCanBeDelivered(order);
        assertThat(orderCanBeDelivered, equalTo(false));
    }

}
