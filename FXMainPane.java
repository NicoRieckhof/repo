import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This panel is the basic panel, inside which other panels are placed.  
 * Before beginning to implement, design the structure of your GUI in order to 
 * understand what panels go inside which ones, and what buttons or other components
 * go in which panels.  
 * @author ralexander
 *
 */
// Make the main panel's layout be a VBox
public class FXMainPane extends VBox {

	// Student Task #2:
	private Button btnHello, btnHowdy, btnChinese, btnClear, btnExit;
	private Label lblFeedback;
	private TextField txtFeedback;
	private HBox hbox1, hbox2;
	
	// Student Task #4:
	private DataManager dataManager;
	/**
	 * The MainPanel constructor sets up the entire GUI in this approach.  Remember to
	 * wait to add a component to its containing component until the container has
	 * been created.  This is the only constraint on the order in which the following 
	 * statements appear.
	 */
	FXMainPane() {
		// Student Task #2:
		btnHello = new Button("Hello");
		btnHowdy = new Button("Howdy");
		btnChinese = new Button("Chinese");
		btnClear = new Button("Clear");
		btnExit = new Button("Exit");

		lblFeedback = new Label("Feedback:");
		txtFeedback = new TextField();

		hbox1 = new HBox();
		hbox2 = new HBox();
		
		// Student Task #4:
		dataManager = new DataManager();

		Insets inset = new Insets(10);
		HBox.setMargin(btnHello, inset);
		HBox.setMargin(btnHowdy, inset);
		HBox.setMargin(btnChinese, inset);
		HBox.setMargin(btnClear, inset);
		HBox.setMargin(btnExit, inset);

		hbox1.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.CENTER);
		
		ButtonHandler handler = new ButtonHandler();
		btnHello.setOnAction(handler);
		btnHowdy.setOnAction(handler);
		btnChinese.setOnAction(handler);
		btnClear.setOnAction(handler);
		btnExit.setOnAction(handler);
		
		// Student Task #3:
		hbox2.getChildren().addAll(lblFeedback, txtFeedback);
		hbox1.getChildren().addAll(btnHello, btnHowdy, btnChinese, btnClear, btnExit);

		this.getChildren().addAll(hbox1, hbox2);
		
	}
	
	// Student Task #4:
	private class ButtonHandler implements EventHandler<ActionEvent> {
	    @Override
	    public void handle(ActionEvent event) {
	        if (event.getTarget() == btnHello) {
	            txtFeedback.setText(dataManager.getHello());
	        } else if (event.getTarget() == btnHowdy) {
	            txtFeedback.setText(dataManager.getHowdy());
	        } else if (event.getTarget() == btnChinese) {
	            txtFeedback.setText(dataManager.getChinese());
	        } else if (event.getTarget() == btnClear) {
	            txtFeedback.setText("");
	        } else if (event.getTarget() == btnExit) {
	            Platform.exit();
	            System.exit(0);
	        }
	    }
	}
}
	
