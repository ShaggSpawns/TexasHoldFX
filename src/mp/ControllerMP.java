package mp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerMP {
	@FXML
	Button callBtn;
	@FXML
	Button raiseBtn;
	@FXML
	Button foldBtn;

	@FXML
	private void handleButton(ActionEvent e) {
		if (e.getSource().equals(callBtn)) {

		}
	}
}
