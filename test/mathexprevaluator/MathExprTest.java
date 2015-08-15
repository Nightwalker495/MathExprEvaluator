package mathexprevaluator;

import mathexprevaluator.models.parser.MathExpr;
import mathexprevaluator.models.parser.InvalidMathExprException;
import mathexprevaluator.models.parser.IMathExpr;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import org.junit.rules.TestName;

/**
 * Test Class for mathematical expression parsing and subsequent evaluation.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-04
 */
public class MathExprTest {
    @Rule
    public TestName name = new TestName();
    
    public MathExprTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    private void printCurrTestMethod() {
        System.out.format("Starting test of '%s()' method...\n\n",
                name.getMethodName());
    }
    
    @Test
    public void testConsts() throws InvalidMathExprException {
        this.printCurrTestMethod();
        
        this.testExpression("1", 1);
        this.testExpression("2", 2);
        this.testExpression("    0    ", 0);
        this.testExpression("  (((((5)))))", 5);
    }
    
    @Test
    public void testOperAdd() throws InvalidMathExprException {
        this.printCurrTestMethod();
        
        this.testExpression("2 + 2", 4);
        this.testExpression("   2   +    2", 4);
        this.testExpression("1 + 1 + 1", 3);
        this.testExpression("100 + 200 + 300 + 400 + 500 + 600 + 700", 2800);
        this.testExpression("1 + (1 + 2)", 4);
        
    }
    
    @Test
    public void testOperSub() throws InvalidMathExprException {
        this.printCurrTestMethod();
        
        this.testExpression("-5", -5);
        this.testExpression("  -123", -123);
        this.testExpression(" 1 - 1 ", 0);
        this.testExpression("100 - 2", 98);
        this.testExpression("-1 - 2 - 3", -6);
        this.testExpression(" (-1)", -1);
        this.testExpression("    -    ( ( 2) )  ", -2);
    }
    
    @Test
    public void testOperMul() throws InvalidMathExprException {
        this.printCurrTestMethod();
        
        this.testExpression("1 * 1", 1);
        this.testExpression("2 * 2 * 2", 8);
        this.testExpression("       100*100", 10000);
        this.testExpression("( 1 * ( 2 * ( 3 * ( 4 ) ) ) )", 24);
    }
    
    @Test
    public void testOperDiv() throws InvalidMathExprException {
        this.printCurrTestMethod();
        
        this.testExpression("2 / 2", 1);
        this.testExpression("8 / 2 / 2 / 2", 1);
        this.testExpression("    1     /      1 ", 1);
        this.testExpression("  5 / 2", 2.5);
    }
    
    @Test
    public void testOperPow() throws InvalidMathExprException {
        this.printCurrTestMethod();
        
        this.testExpression("1000000^0", 1);
        this.testExpression("2 ^ 2", 4);
        this.testExpression("1 ^ 10", 1);
        this.testExpression("    3   ^  2 ", 9);
        this.testExpression("(2 ^ 2) ^ 2", 16);
        this.testExpression(" (3 ^ (2 ^ 1)) ^ 2", 81);
        this.testExpression("2^2^2", 16);
        this.testExpression("-2^2", -4);
        this.testExpression("(-5)^2", 25);
    }
    
    @Test
    public void testOperCombo() throws InvalidMathExprException {
        this.printCurrTestMethod();
        
        this.testExpression("1 + 2 - 1 + 3 - 1 + 4", 8);
        this.testExpression("1 + -2 + 1 - 3", -3);
        this.testExpression("2 * 2 + 1", 5);
        this.testExpression("1 + 1 * 2 - 3", 0);
        this.testExpression("(1 + 1) * 2", 4);
        this.testExpression("((1 + 1) * 2) / (3 - 1)", 2);
        this.testExpression(" 1 / (1 + 1)", 0.5);
    }
    
    @Test
    public void testInvalidSyntax() throws InvalidMathExprException {
        this.printCurrTestMethod();
        
        this.testInvalidExpression("a");
        this.testInvalidExpression("+++++++");
        this.testInvalidExpression("(a + ??) * 5");
        this.testInvalidExpression(">><<><>}{}\">\">>\"|?}?|\"?\"|>\"?");
        this.testInvalidExpression("    . ");
        this.testInvalidExpression("1 + a + 2 + b * 1");
        
    }
    
    private void testExpression(String expr, double expRes)
            throws InvalidMathExprException {
        System.out.format("**************************************************\n"
                + "Testing \"%s\" for value \"%.2f\".\n", expr, expRes);
        
        IMathExpr inst = new MathExpr(expr);
        double calcRes = inst.eval();
        String errMsg = String.format("\tExpected =  %.2f; calculated = %.2f "
                + "for expression [ %s ]\n",
                expRes, calcRes, expr);
        
        System.out.format("Format of processed expression: %s\n\n", inst);
        
        assertEquals(errMsg, expRes, calcRes, 0.001);
    }
    
    private void testInvalidExpression(String expr) {
        System.out.format("**************************************************\n"
                + "Testing \"%s\" for parser error.\n", expr);
        
        try {
            IMathExpr inst = new MathExpr(expr);
            
            fail(String.format("Parsing of expr. \"%s\" should fail. "
                    + "The produced expression is: \"%s\".\n", expr, inst));
        } catch (InvalidMathExprException ex) {
            System.err.println("Expression parsing error: " + ex.getMessage());
        }
    }
}
