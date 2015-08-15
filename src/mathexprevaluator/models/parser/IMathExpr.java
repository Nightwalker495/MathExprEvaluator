package mathexprevaluator.models.parser;

/*******************************************************************************
 * Interface which will be implemented by all mathematical expressions.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-05
 */
public interface IMathExpr {
    
    /***************************************************************************
     * Calculates the value of the expression.
     * 
     * @return numerical value calculated
     */
    double eval();
    
}
