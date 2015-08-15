package mathexprevaluator.models.parser;

/*******************************************************************************
 * Class representing a mathematical constant - a number.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-04
 */
class MathExprConst implements IMathExpr {

    private final double val;

    /***************************************************************************
     * Default constructor.
     * 
     * @param val constant value
     */
    public MathExprConst(double val) {
        this.val = val;
    }

    /**************************************************************************/
    @Override
    public double eval() {
        return this.val;
    }

    /***************************************************************************
     * String representation of the object.
     * 
     * @return constant value
     */
    @Override
    public String toString() {
        return String.format("%.2f", this.val);
    }

}