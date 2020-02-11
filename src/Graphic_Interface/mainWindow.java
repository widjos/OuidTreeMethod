
package Graphic_Interface;


import Analisis.Lexico;
import Entorno.Simbolo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Wid
 */
public class mainWindow extends Stage {

    /**
     * Initializes the controller class.
     */
     @FXML private  Button btnCompile;
     @FXML private  TextArea txtCodeInput, txtConsole;
     @FXML private  MenuItem itemCompilar, itemOpen;
     @FXML private FileChooser cargaDeArchivos;
     private Lexico scanner;
     

    public mainWindow(){
        
        

        
    }
    

        // Abrir archivo
    @FXML
     public void openFile(Event event){
        cargarArchivo();
    }
     
    @FXML 
    public void Compile(MouseEvent event){
    
      scanner = new Lexico();        
      if(!txtCodeInput.getText().isEmpty()){
             scanner.automataFinitoDeterministico(txtCodeInput.getText());
             if(scanner.tablaErrores.isEmpty()){
                 System.out.println("No hay errores");
                 printSimbolsLex(scanner.tablaSimbolos);
             }
          
                
             else
              System.out.println("Existen erroes");
          
      }else{
      
          System.out.println("No hay nada para analizar");
      }

    }
     
    
    public void printSimbolsLex(LinkedList<Simbolo> temp){
        
        txtConsole.clear();
        for(Simbolo a : temp){
        
            txtConsole.appendText("Lexema = "+a.getLexema() + "   Token = "+ a.getToken() + "  Fila = "+a.getFilal() + "  Columna = " +a.getColumna()+"\n");
        }
    
    
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
