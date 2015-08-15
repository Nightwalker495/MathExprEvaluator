package mathexprevaluator.models;

import mathexprevaluator.models.parser.IMathExpr;
import mathexprevaluator.models.parser.InvalidMathExprException;
import mathexprevaluator.models.parser.MathExpr;

/*******************************************************************************
 * Model which processes the expressions from the user and evaluates them.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-06
 */
public class MEEModel {
    
    /***************************************************************************
     * Evaluates the mathematical expression.
     * 
     * @param expr mathematical expression to evaluate
     * 
     * @return calculation result
     * 
     * @throws InvalidMathExprException thrown in case that a syntax error is
     * found in the expression
     */
    public double evalExpr(String expr) throws InvalidMathExprException {
        IMathExpr mathExpr = new MathExpr(expr);
        
        return mathExpr.eval();
    }
    
}
