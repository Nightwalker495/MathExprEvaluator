package mathexprevaluator.models.parser;

import static java.lang.Math.pow;

/*******************************************************************************
 * Class representing a "POW" mathematical function.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-04
 */
class MathExprPow extends AMathBinOper {

    /***************************************************************************
     * Default constructor
     * 
     * @param base base
     * @param exponent exponent to raise the base to
     */
    public MathExprPow(IMathExpr base, IMathExpr exponent) {
        super(MathBinOper.POW, base, exponent);
    }

    /**************************************************************************/
    @Override
    public double eval() {
        return pow(this.leftOperand.eval(), this.rightOperand.eval());
    }
    
}

