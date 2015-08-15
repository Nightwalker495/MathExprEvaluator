package mathexprevaluator;

import javafx.application.Application;
import javafx.stage.Stage;
import mathexprevaluator.controllers.MEEController;
import mathexprevaluator.models.MEEModel;
import mathexprevaluator.views.MEEView;

/*******************************************************************************
 * Main class for the <b>Math expression evaluator</b> project.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-06
 */
public class MathExprEvaluatorMain extends Application {
    
    private static final String APP_NAME = "Math Expression Evaluator";
    
    /***************************************************************************
     * Program entry-point.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /***************************************************************************
     * Starts the application.
     * 
     * @param stage Stage to draw upon
     * @throws Exception thrown in case of an error
     */
    @Override
    public void start(Stage stage) throws Exception {
        MEEModel model = new MEEModel();
        MEEView view = new MEEView(APP_NAME, stage);
        MEEController controller = new MEEController(model, view);
        
        view.show();
    }
    
}
