
package Graphic_Interface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Wid
 */
public class MainWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML  Button btnCompile;
     @FXML  TextArea txtCodeInput, txtConsole;
     @FXML MenuItem itemCompilar, itemOpen;
     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
