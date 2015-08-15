package mathexprevaluator.models.parser;

/*******************************************************************************
 * Class representing a "DIV" mathematical operation.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-04
 */
class MathExprDiv extends AMathBinOper {

    /***************************************************************************
     * Default constructor.
     * 
     * @param dividend left operand
     * @param divisor right operand
     */
    public MathExprDiv(IMathExpr dividend, IMathExpr divisor) {
        super(MathBinOper.DIV, dividend, divisor);
    }

    /**************************************************************************/
    @Override
    public double eval() {
        return this.leftOperand.eval() / this.rightOperand.eval();
    }

}