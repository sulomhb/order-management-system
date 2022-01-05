import java.lang.IllegalArgumentException

/**
 * @author Suleyman Selcuk
 */

class Stock() {
    var pieceStock = hashMapOf<Any?, Int>();


    /**
    * @param piece - Specific piece with length, width and color given as standard string.
    * @param amount - Amount of pieces to operate with.
    * @exception class IllegalArgumentException
    */

    fun addPieceToStock(piece : String, amount : Int) : Unit{
            if (amount < 0) {
                throw IllegalArgumentException("Amount is invalid. Cannot add negative numbers of pieces to stock.")
            } else {
                pieceStock[piece] = amount;
            }
    }

    /**
    * @param piece - Specific piece with length, width and color given as standard string.
    * @param amountOrdered - Amount of pieces ordered.
    * @exception class IllegalArgumentException
     */

    fun removePieceFromStock(piece : String, amountOrdered : Int) : Boolean {
        // Get quantity of given piece in stock
        var givenPieceQuantity = pieceStock.get(piece);

        // Throw exception if ordered amount is invalid.
        if(amountOrdered < 0) {
            throw IllegalArgumentException("Amount is invalid. Cannot order negative numbers of pieces to stock.")
            return false;
        }
        // If there is enough pieces in stock
        if(givenPieceQuantity != null && givenPieceQuantity - amountOrdered >= 0) {
            // Pack down ordered amount of pieces, and subtract from stock.
            givenPieceQuantity -= amountOrdered;
            // Update quantity of stock after order has been packed.
            pieceStock[piece] = givenPieceQuantity;
            // Order is successfully packed and stock is updated.
            return true;
        }
        // Order could not be processed due to insufficient amount. New pieces will be ordered.
        return false;
    }

    /**
     * @param order - Order with specific piece and amount of pieces.
     * @return  allPiecesStatusResult - Boolean value that tells if every piece is available after checking the quantity of each piece.
     */

    fun checkIfOrderCanBeDelivered(order: Map<String, Int>) : Boolean {
        // Checking every piece in order if it is available. If available, we add "true" to our status-array, if not we add "false".
        var storePieceStatus = arrayListOf<Boolean>();
        for ((piece, amount) in order) {
            // If there is enough pieces in stock:
            if (amount > pieceStock[piece]!!) {
                storePieceStatus.add(false);
                println("We only have ${pieceStock[piece]} ${piece} pieces available. You ordered ${amount} pieces which is not available.");
            }
        }
        // Checking if any of the pieces are given "false" status for every piece that is ordered.
        if(false !in storePieceStatus) {
            // If every piece is available, remove them from stock.
            for ((piece, amount) in order) {
                removePieceFromStock(piece, amount);
                println("${amount} of ${piece} piece is packed - ${pieceStock[piece]} left in stock.");
                continue;
            }
        }

        // Return order status. If false: pieces not available : else pieces available and order can be delivered.
        return (false !in storePieceStatus);
    }

}

/**
 * @param length - Length of piece.
 * @param width - Width of piece.
 * @param color - Color of piece.
 * @return standard string format for each piece with length, width and color.
 */

fun pieceToString(length: Int, width: Int, color: String): String {
    /* Function used to display piece as string, such that it can be used as a key with a value (amount of pieces) in our PieceStock Map.*/
    return "${length} x ${width} - ${color}";
}


/*
fun main() {
    // Creating a "warehouse"
    var stock = Stock();
    // Creating a storage compartment for pieces.
    var pieceStock = stock.pieceStock;

    // Example order
    val exampleOrder = mapOf<String, Int>(
        pieceToString(2,2,"red") to -20,
        pieceToString(1,2,"purple") to -50,
    );

    // Adding ordered piece to stock for testing purposes
    for ((piece) in exampleOrder) {
        stock.addPieceToStock(piece, 100);
    }

    val orderCanBeDelivered : Boolean = stock.checkIfOrderCanBeDelivered(exampleOrder);

    if(orderCanBeDelivered) {
        println("Order has been processed and completed.\n");
    } else {
        println("Order could not be delivered due to insufficient amount of pieces. No pieces were removed from stock.\n");
    }
}
*/
