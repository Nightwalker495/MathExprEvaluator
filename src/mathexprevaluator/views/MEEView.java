package mathexprevaluator.views;

import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/*******************************************************************************
 * The view class which handles all the GUI drawing mechanisms.
 * 
 * @author Milan Ondrasovic
 * @version 2015-08-06
 */
public class MEEView {
    
    private static final int WIN_WIDTH = 500;
    private static final int WIN_HEIGHT = 260;
    
    private static final String INFO_TEXT_KEY = "infoTextKey";
    private static final String IN_EXPR_LABEL_KEY = "inExprLabelKey";
    private static final String IN_EXPR_FIELD_KEY = "inExprFieldKey";
    private static final String EVAL_BUTTON_KEY = "evalButtonKey";
    private static final String CLEAR_BUTTON_KEY = "clearButtonKey";
    private static final String RES_LABEL_KEY = "resultLabelKey";
    private static final String RES_FIELD_KEY = "resultFieldKey";
    
    private final Stage stage;
    private final GridPane root;
    private final Button evalButton;
    private final Button clearButton;
    private final TextField exprTextField;
    private final TextField resultTextField;
    
    /***************************************************************************
     * Default constructor.
     * 
     * @param appTitle application title
     * @param stage stage to draw upon
     */
    public MEEView(String appTitle, Stage stage) {
        this.stage = stage;
        this.setUpStage();
        
        this.root = new GridPane();
        
        HashMap<String, Node> nodes = new HashMap<>();
        
        Node tmpNode = this.createInfoText();
        nodes.put(INFO_TEXT_KEY, tmpNode);
        
        this.exprTextField = this.createExprTextField();
        nodes.put(IN_EXPR_FIELD_KEY, this.exprTextField);
        
        tmpNode = this.createExprLabel("Enter the expression:",
                this.exprTextField);
        nodes.put(IN_EXPR_LABEL_KEY, tmpNode);
        
        this.evalButton = this.createEvalButton("Evaluate");
        nodes.put(EVAL_BUTTON_KEY, this.evalButton);
        
        this.clearButton = this.createClearButton("Clear");
        nodes.put(CLEAR_BUTTON_KEY, this.clearButton);
        
        this.resultTextField = this.createResultTextFiedl();
        nodes.put(RES_FIELD_KEY, this.resultTextField);
        
        tmpNode = this.createResultLabel("Result:", this.resultTextField);
        nodes.put(RES_LABEL_KEY, tmpNode);
        
        this.initLayout(nodes);
        
        Scene scene = new Scene(this.root, WIN_WIDTH, WIN_HEIGHT);
        
        this.stage.setTitle(appTitle);
        this.stage.setScene(scene);
    }
    
    /***************************************************************************
     * Shows the window on the screen.
     */
    public void show() {
        this.stage.show();
    }
    
    /***************************************************************************
     * Getter. Retrieves the expression on the input.
     * 
     * @return expression on the input
     */
    public String getExpression() {
        return this.exprTextField.getText();
    }
    
    /***************************************************************************
     * Setter. Sets the value of the result.
     * 
     * @param result result
     */
    public void setResult(String result) {
        this.resultTextField.setText(result);
    }
    
    /***************************************************************************
     * Clears all GUI components this action is appropriate for.
     */
    public void clear() {
        this.exprTextField.setText("");
        this.resultTextField.setText("");
    }
    
    /***************************************************************************
     * Displays the error message.
     * 
     * @param msg error message to display to user
     */
    public void displayErrMsg(String msg) {
        this.resultTextField.setText("Error: " + msg);
    }
    
    /***************************************************************************
     * Adds a handler for the "evaluate" button.
     * 
     * @param handler action handler
     */
    public void addEvalButtonHandler(EventHandler<ActionEvent> handler) {
        this.addButtonHandler(this.evalButton, handler);
    }
    
    /***************************************************************************
     * Adds a handler for the "clear" button.
     * 
     * @param handler action handler
     */
    public void addClearButtonHandler(EventHandler<ActionEvent> handler) {
        this.addButtonHandler(this.clearButton, handler);
    }
    
    /***************************************************************************
     * Adds a handler to the specified button.
     * 
     * @param button button to attach the handler to
     * @param handler action handler
     */
    private void addButtonHandler(Button button,
            EventHandler<ActionEvent> handler) {
        button.setOnAction(handler);
    }
    
    /***************************************************************************
     * Sets up the basic stage properties.
     */
    private void setUpStage() {
        this.stage.setMinWidth(WIN_WIDTH);
        this.stage.setMaxWidth(WIN_WIDTH);
        
        this.stage.setMinHeight(WIN_HEIGHT);
        this.stage.setMaxHeight(WIN_HEIGHT);
    }
    
    /***************************************************************************
     * Creates a text containing basic information about the program
     * functionality.
     * 
     * @return Node containing the newly created GUI component
     */
    private Node createInfoText() {
        Text title = new Text("Math expression evaluator\n");
        title.setStyle("-fx-font-weight: bold");
        
        Text info = new Text("Evaluates the expression on the input.\n"
                + "The following symbols may be used:\n");
        info.setStyle("-fx-font-style: italic");
        Text infoSymbols = new Text("+ (add), - (sub), * (mul), / (div), ^ "
                + "(pow) and ( )");
        infoSymbols.setStyle("-fx-font-weight: bold");
        
        return new VBox(title, new TextFlow(info, infoSymbols));
    }
    
    /***************************************************************************
     * Creates a label providing a description for the input text field.
     * 
     * @param labelText text description the component will show
     * @param inputExprTextField input text which this label is attached to
     * 
     * @return Node containing the newly created GUI component
     */
    private Node createExprLabel(String labelText,
            TextField inputExprTextField) {
        Label label = new Label("_" + labelText);
        label.setLabelFor(inputExprTextField);
        label.setMnemonicParsing(true);
        
        return label;
    }
    
    /***************************************************************************
     * Creates a input text field for the expression entering.
     * 
     * @return Node containing the newly created GUI component
     */
    private TextField createExprTextField() {
        return new TextField("0");
    }
    
    /***************************************************************************
     * Creates an "evaluate" button.
     * 
     * @param buttonLabel text the button will show
     * 
     * @return Node containing the newly created GUI component
     */
    private Button createEvalButton(String buttonLabel) {
        return this.createButton(buttonLabel);
    }
    
    /***************************************************************************
     * Creates a "clear" button.
     * 
     * @param buttonLabel text the button will show
     * 
     * @return Node containing the newly created GUI component
     */
    private Button createClearButton(String buttonLabel) {
        return this.createButton(buttonLabel);
    }
    
    /***************************************************************************
     * Creates a button with specified text.
     * 
     * @param buttonLabel text the button will show
     * 
     * @return Node containing the newly created GUI component
     */
    private Button createButton(String buttonLabel) {
        Button button = new Button(buttonLabel);
        button.setPrefSize(130.0, 30.0);
        
        return button;
    }
    
    /***************************************************************************
     * Creates a label describing the result output text field.
     * 
     * @param labelText description the component will show
     * @param outResultTextField text field the text will be attached to
     * 
     * @return Node containing the newly created GUI component
     */
    private Node createResultLabel(String labelText,
            TextField outResultTextField) {
        Label label = new Label(labelText);
        label.setLabelFor(outResultTextField);
        
        return label;
    }
    
    /***************************************************************************
     * Creates a result text field.
     * 
     * @return Node containing the newly created GUI component
     */
    private TextField createResultTextFiedl() {
        return new TextField("0");
    }
    
    /***************************************************************************
     * Initializes the layout of all components creates. 
     * 
     * @param nodes map containing String-Node pairs, where the String is one
     * of the key String by which a particular GUI component is identified.
     */
    private void initLayout(HashMap<String, Node> nodes) {
        this.root.setPadding(new Insets(5.0));
        this.root.setVgap(15.0);
        this.root.setHgap(5.0);
        this.root.setPrefSize(WIN_WIDTH, WIN_HEIGHT);
        this.root.setMaxSize(Region.USE_COMPUTED_SIZE, 
                Region.USE_COMPUTED_SIZE);
        
        this.initLayoutConstraints();
        
        this.storeNodeInGrid(nodes, INFO_TEXT_KEY,     0, 0, 4, 1);
        this.storeNodeInGrid(nodes, IN_EXPR_LABEL_KEY, 0, 1, 1, 1);
        this.storeNodeInGrid(nodes, IN_EXPR_FIELD_KEY, 1, 1, 4, 1);
        this.storeNodeInGrid(nodes, EVAL_BUTTON_KEY,   0, 2, 1, 1);
        this.storeNodeInGrid(nodes, CLEAR_BUTTON_KEY,  1, 2, 1, 1);
        this.storeNodeInGrid(nodes, RES_LABEL_KEY,     0, 3, 1, 1);
        this.storeNodeInGrid(nodes, RES_FIELD_KEY,     1, 3, 4, 1);
    }
    
    /***************************************************************************
     * Initializes layout constraints.
     */
    private void initLayoutConstraints() {
        this.root.getColumnConstraints().addAll(
                this.createConstraint(Priority.NEVER),
                this.createConstraint(Priority.ALWAYS)
        );
    }
    
    /***************************************************************************
     * Creates a column constraint.
     * 
     * @param priority priority of the constraint
     * 
     * @return newly created ColumnConstraints instance
     */
    private ColumnConstraints createConstraint(Priority priority) {
        ColumnConstraints constraint = new ColumnConstraints();
        constraint.setHgrow(priority);
        
        return constraint;
    }
    
    /***************************************************************************
     * Store a GUI component in the layout manager.
     * 
     * @param nodes map containing nodes to attach to the layout
     * @param guiKey key of the GUI component to attach
     * @param col column of the gird
     * @param row row of the gird
     * @param colSpan column span of the component within the grid
     * @param rowSpan row span of the component within the grid
     */
    private void storeNodeInGrid(HashMap<String, Node> nodes, String guiKey,
            int col, int row, int colSpan, int rowSpan) {
        Node node = nodes.get(guiKey);
        if (node == null) {
            throw new IllegalArgumentException("Invalid GUI key: " + guiKey);
        }
        
        this.root.add(nodes.get(guiKey), col, row, colSpan, rowSpan);
    }
    
}
