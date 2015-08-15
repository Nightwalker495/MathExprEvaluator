package mathexprevaluator.models.parser;

/*******************************************************************************
 * Abstract class providing a functionality for binary mathematical operations.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-09
 */
abstract class AMathBinOper implements IMathExpr {
    
    protected final IMathExpr leftOperand;
    protected final IMathExpr rightOperand;
    
    private final MathBinOper operation;
    
    /***************************************************************************
     * Default constructor.
     * 
     * @param operation binary operation represented
     * @param leftOperand left operand
     * @param rightOperand right operand
     */
    public AMathBinOper(MathBinOper operation, IMathExpr leftOperand,
            IMathExpr rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        
        this.operation = operation;
    }
    
    /***************************************************************************
     * String representation of the object.
     * 
     * @return (leftOperand operationSymbol rightOperand)
     */
    @Override
    public String toString() {
        return String.format("(%s %s %s)", this.leftOperand, this.operation,
                this.rightOperand);
    }
    
}
