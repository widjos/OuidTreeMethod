
package Graphic_Interface;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wid
 */
public class MainWindowController extends Stage {

    /**
     * Initializes the controller class.
     */
     @FXML private  Button btnCompile;
     @FXML private  TextArea txtCodeInput, txtConsole;
     @FXML private  MenuItem itemCompilar, itemOpen;
     @FXML private FileChooser cargaDeArchivos;
     



        // Abrir archivo
    @FXML
    public void openFiile(MouseEvent event){
        cargarArchivo();
    } 
    
    public void cargarArchivo(){

            // Limpiar el area antes de cargar
            txtCodeInput.clear();

           cargaDeArchivos = new FileChooser();
           cargaDeArchivos.setTitle("Abrir un Archivo");
           cargaDeArchivos.setInitialFileName("Mi Archivo.flp");
           FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("LFP Archivos (*.lfp)", "*.lfp");
           FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
           cargaDeArchivos.getExtensionFilters().add(extFilter);
           cargaDeArchivos.getExtensionFilters().add(extFilter2);
        txtCodeInput.setWrapText(true);

           try {
               File seleccionado = cargaDeArchivos.showOpenDialog(this);
               BufferedReader archivo = new BufferedReader(new FileReader(seleccionado.getPath()));

               String linea = "";
               String lin = "";
               while ((linea = archivo.readLine()) != null) {
                   lin += linea + "\n";

               }
               txtCodeInput.appendText(lin);

               if (archivo != null)
                   archivo.close();

           } catch (Exception exep2) {


           }

   }
    
}
