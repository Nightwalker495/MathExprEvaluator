package mathexprevaluator.models.parser;

/*******************************************************************************
 * Exception which handler syntax errors in mathematical expression.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-06
 */
public class InvalidMathExprException extends Exception {

    /***************************************************************************
     * Creates a new instance of <code>InvalidMathExprException</code> without
     * detail message.
     */
    public InvalidMathExprException() {
    }

    /***************************************************************************
     * Constructs an instance of <code>InvalidMathExprException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidMathExprException(String msg) {
        super(msg);
    }
    
}
