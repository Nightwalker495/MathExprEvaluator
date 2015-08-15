package mathexprevaluator.models.parser;

/*******************************************************************************
 * Base class for all mathematical expressions.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-04
 */
public class MathExpr implements IMathExpr {
    
    // TODO Think about using a ArithmeticException somewhere.
    private static final char BRACKET_OPEN = '(';
    private static final char BRACKET_CLOSE = ')';
    
    private IMathExpr child;
    
    /***************************************************************************
     * Default constructor.
     * 
     * @param expr expression to process
     * @throws InvalidMathExprException thrown in case of invalid syntax
     */
    public MathExpr(String expr) throws InvalidMathExprException {
        if (expr.equals("")) {
            this.child = null;
        } else {
            // Remove all spaces, they occasionally make parsing more difficult.
            this.parse(expr.replace(" ", ""));
        }
    }
    
    /***************************************************************************
     * Evaluates the expression contained.
     * 
     * @return calculated numerical value
     */
    @Override
    public double eval() {
        return (this.child == null) ? 0.0 : this.child.eval();
    }
    
    /***************************************************************************
     * String representation of the object.
     * 
     * @return standard symbolic representation of the math. expression
     */
    @Override
    public String toString() {
        return (this.child == null) ? String.format("%.2f", 0.0) : 
                this.child.toString();
    }
    
    /***************************************************************************
     * Parses the expression.
     * 
     * @param expr expression to parse
     * @throws InvalidMathExprException thrown in case of syntax error
     */
    private void parse(String expr) throws InvalidMathExprException {
        if (!this.containsDigit(expr)) {
            throw new InvalidMathExprException("Expression must contain at "
                    + "least one digit.");
        }
        /* The following algorithm is used to parse the math. expression:
        
        Note: The subsequent splitting of the expression has to be done when the
        particular standard symbol representing the mathematical operation is
        not inside of brackets. That's why the outermost bracket pair is always
        removed when no point to split at has been found and the parsing starts
        all over again.
        
        1) Find a '+' sign (left-to-right) and split at this point.
           If none found, go to step 2.
        2) Find a '-' sign (right-to-left) and split at this point.
           If none found, go to step 3.
        3) Find a '*' sign (left-to-right) and split at this point.
           If none found, go to step 4.
        4) Find a '/' sign (right-to-left) and split at this point.
           If none found, go to step 5.
        5) Remove outermost brackets.
           If success, go to step 1, if none found, go to step 6.
        6) Parse the remaining expression as a math element - a number.
           If an error occurs, the expression is invalid.
        */
        while (true) {
            try {
                for (MathBinOper operator: MathBinOper.values()) {
                    IMathExpr subExpr = this.parseOperator(expr, operator,
                            operator.getLeftToRightPriority());
                    if (subExpr != null) {
                        this.child = subExpr;
                        return;
                    }
                }
                
                expr = this.removeOuterBrackets(expr);
            } catch (IllegalArgumentException ex) {
                // If everything fails, try to treat the rest of the expression
                // as a math element.
                this.child = this.parseMathElement(expr);
                
                return;
            }
        }
    }
    
    /***************************************************************************
     * Parser a specified mathematical operation in the expressions.
     * 
     * @param expr expression to find the operation in
     * @param operator operation to find
     * @param processLeft2Right priority flow
     * 
     * @return newly created math expression, or null in case of failure
     * @throws InvalidMathExprException throw in case of syntax error
     */
    private IMathExpr parseOperator(String expr, MathBinOper operator,
            boolean processLeft2Right) throws InvalidMathExprException {
        int startPos, endPos, step;
        
        if (processLeft2Right) { // Parse expression forwards.
            startPos = 0;
            endPos = expr.length();
            step = 1;
        } else { // Parse expression backwards.
            startPos = expr.length() - 1;
            endPos = -1; // Going backwards, even 0 index counts.
            step = -1;
        }
        
        int i = startPos;
        while (i != endPos) {
            if (this.isOutsideOfBracket(expr, i)) {
                char c = expr.charAt(i);
                
                if (c == operator.getSign()) {
                    IMathExpr left = new MathExpr(expr.substring(0, i));
                    IMathExpr right = new MathExpr(expr.substring(i + 1,
                            expr.length()));
                    
                    return MathBinOper.buildBinaryOper(operator, left, right);
                }
            }
            
            i += step;
        }
        
        return null;
    }
    
    /***************************************************************************
     * Checks whether the String contains at least one digit.
     * 
     * @param expr string to check
     * @return true if string contains at least one digit, false otherwise
     */
    private boolean containsDigit(String expr) {
        for (int i = 0; i < expr.length(); i++) {
            if (Character.isDigit(expr.charAt(i))) {
                return true;
            }
        }
        
        return false;
    }
    
    /***************************************************************************
     * Checks whether the specified position in the String is outside of
     * brackets.
     * 
     * @param expr expression to check
     * @param pos position within the expression
     * 
     * @return true is the position is not within the brackets, false otherwise
     */
    private boolean isOutsideOfBracket(String expr, int pos) {
        int level = 0;
        
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            
            if (c == BRACKET_OPEN) {
                level++;
            } else if (c == BRACKET_CLOSE) {
                level--;
            }
            
            if (i == pos) {
                return level == 0;
            }
        }
        
        return true;
    }
    
    /***************************************************************************
     * Parses a mathematical element - a number.
     * 
     * @param expr expression to parse
     * @return newly created math expression
     * 
     * @throws InvalidMathExprException thrown in case of syntax error
     */
    private IMathExpr parseMathElement(String expr)
            throws InvalidMathExprException {
        try {
            double val = Double.parseDouble(expr);
            
            return new MathExprConst(val);
        } catch (NumberFormatException ex) {
            throw new InvalidMathExprException("Invalid syntax, "
                    + "unknown character: " + expr);
        }
    }
    
    /***************************************************************************
     * Removes outside brackets from the expression.
     * 
     * @param expr expression to remove brackets from
     * 
     * @return a new string resulting from the brackets removal process
     */
    private String removeOuterBrackets(String expr) {
        int openPos = expr.indexOf(BRACKET_OPEN);
        if (openPos == -1) {
            throw new IllegalArgumentException("No opening bracket");
        }
        
        int closePos = expr.lastIndexOf(BRACKET_CLOSE);
        if (closePos == -1) {
            throw new IllegalArgumentException("No closing bracket");
        }
        
        String before = expr.substring(0, openPos);
        String middle = expr.substring(openPos + 1, closePos);
        String after = expr.substring(closePos + 1, expr.length());
        
        return before + middle + after;
    }
    
}
