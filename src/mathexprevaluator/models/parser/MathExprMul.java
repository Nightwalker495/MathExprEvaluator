package mathexprevaluator.models.parser;

/*******************************************************************************
 * Class representing a "MUL" mathematical operation.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-04
 */
class MathExprMul extends AMathBinOper {

    /***************************************************************************
     * Default constructor.
     * 
     * @param multiplicand left operand
     * @param multiplier right operand
     */
    public MathExprMul(IMathExpr multiplicand, IMathExpr multiplier) {
        super(MathBinOper.MUL, multiplicand, multiplier);
    }

    /**************************************************************************/
    @Override
    public double eval() {
        return this.leftOperand.eval() * this.rightOperand.eval();
    }

}