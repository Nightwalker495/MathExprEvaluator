package mathexprevaluator.models.parser;

/***************************************************************************
 * A class representing "ADD" mathematical operation.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-04
 */
class MathExprAdd extends AMathBinOper {
    
    /***************************************************************************
     * Default constructor.
     * 
     * @param augend left operand
     * @param addend right operand
     */
    public MathExprAdd(IMathExpr augend, IMathExpr addend) {
        super(MathBinOper.ADD, augend, addend);
    }
    
    /**************************************************************************/
    @Override
    public double eval() {
        return this.leftOperand.eval() + this.rightOperand.eval();
    }

}
