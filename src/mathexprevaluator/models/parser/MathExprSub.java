package mathexprevaluator.models.parser;

/*******************************************************************************
 * Class representing a "SUB" mathematical operation.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-04
 */
class MathExprSub extends AMathBinOper {

    /***************************************************************************
     * Default constructor.
     * 
     * @param minuend left operand
     * @param subtrahend right operand
     */
    public MathExprSub(IMathExpr minuend, IMathExpr subtrahend) {
        super(MathBinOper.SUB, minuend, subtrahend);
    }

    /**************************************************************************/
    @Override
    public double eval() {
        return this.leftOperand.eval() - this.rightOperand.eval();
    }

}