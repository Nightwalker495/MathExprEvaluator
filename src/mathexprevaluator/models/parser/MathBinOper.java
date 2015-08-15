package mathexprevaluator.models.parser;

/*******************************************************************************
 * Enumerate type which contains all supported binary mathematical operations.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-04
 */
public enum MathBinOper {
    
    // The order must not be changed!
    ADD('+', true),
    SUB('-', false),
    MUL('*', true),
    DIV('/', false),
    POW('^', false);
    
    private final char sing;
    private final boolean leftToRightPriority;
    
    /***************************************************************************
     * Builds a new instance of a binary mathematical operation.
     * 
     * @param operation binary mathematical operation
     * @param leftExpr left operator
     * @param rightExpr right operator
     * 
     * @return new instance of math. expression
     */
    public static IMathExpr buildBinaryOper(MathBinOper operation,
            IMathExpr leftExpr, IMathExpr rightExpr) {
        switch (operation) {
            case ADD:
                return new MathExprAdd(leftExpr, rightExpr);
            case SUB:
                return new MathExprSub(leftExpr, rightExpr);
            case MUL:
                return new MathExprMul(leftExpr, rightExpr);
            case DIV:
                return new MathExprDiv(leftExpr, rightExpr);
            case POW:
                return new MathExprPow(leftExpr, rightExpr);
            default: // Default can never occur, hence the point of enum.
                return null;
        }
    }
    
    /***************************************************************************
     * Default constructor.
     * 
     * @param operSign operation standard symbol
     * @param leftToRightPriority whether the priority of the operation when
     * parsing is to be from left to the right of from the right to the left
     */
    private MathBinOper(char operSign, boolean leftToRightPriority) {
        this.sing = operSign;
        this.leftToRightPriority = leftToRightPriority;
    }
    
    /***************************************************************************
     * Getter.
     * 
     * @return standard symbol for the mathematical operation
     */
    public char getSign() {
        return this.sing;
    }
    
    /***************************************************************************
     * Getter.
     * 
     * @return True if left-to-right priority is set, False otherwise
     */
    public boolean getLeftToRightPriority() {
        return this.leftToRightPriority;
    }
    
    /***************************************************************************
     * String representation of the object.
     * 
     * @return standard symbol for the mathematical operation
     */
    @Override
    public String toString() {
        return "" + this.sing;
    }
    
}
