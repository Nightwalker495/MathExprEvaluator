package mathexprevaluator.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mathexprevaluator.models.MEEModel;
import mathexprevaluator.models.parser.InvalidMathExprException;
import mathexprevaluator.views.MEEView;

/*******************************************************************************
 * Controller handling the events for the entire application.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-08
 */
public class MEEController {
    
    private final MEEModel meeModel;
    private final MEEView meeView;
    
    /***************************************************************************
     * Default constructor.
     * 
     * @param meeModel application model
     * @param meeView application view
     */
    public MEEController(MEEModel meeModel, MEEView meeView) {
        this.meeModel = meeModel;
        this.meeView = meeView;
        
        this.meeView.addEvalButtonHandler(new EvalButtonHandler());
        this.meeView.addClearButtonHandler(new ClearButtonHandler());
    }
    
    /***************************************************************************
     * Class providing a functionality for handling "evaluate button" events.
     */
    private class EvalButtonHandler implements EventHandler<ActionEvent> {
        
        /***********************************************************************
         * Evaluates the expression.
         * 
         * @param t event
         */
        @Override
        public void handle(ActionEvent t) {
            String expr = meeView.getExpression();
            
            if (expr.equals("")) {
                meeView.displayErrMsg("No expression on the input");
                return;
            }
            
            try {
                double res = meeModel.evalExpr(expr);
                meeView.setResult(String.format("%.5f", res));
            } catch (InvalidMathExprException ex) {
                meeView.displayErrMsg(ex.getMessage());
            }
        }
        
    }
    
    /***************************************************************************
     * Class providing a functionality for handling "clear button" events.
     */
    private class ClearButtonHandler implements EventHandler<ActionEvent> {

        /***********************************************************************
         * Clears the view components.
         * 
         * @param t event
         */
        @Override
        public void handle(ActionEvent t) {
            meeView.clear();
        }
        
    }
}
