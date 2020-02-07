
package Analisis;

import Entorno.Simbolo;
import java.util.LinkedList;
import javafx.scene.control.TextArea;

/**
 *
 * @author Wid
 */
public class Lexico {
    
   private int fila, columna,estado ,indice, actual, numeroSimbolo, numeroErrores;
   private String lexema,nuevaCadena, cadenaEntrada;
   private char buscador;
   public LinkedList<Simbolo> tablaSimbolos;
   public LinkedList<Error> tablaErrores;
      
   public Lexico(){
   
       fila = 1;
       columna = 1;
       estado =0;
       tablaSimbolos = new LinkedList<>();
       tablaErrores = new LinkedList<>();
   
   }
   
   
   public String ObtenerTokens(TextArea entrada){
       return  entrada.getText();
   }
   
   public void automataFinitoDeterministico(String text){
   
        cadenaEntrada = text.trim();
        buscador = ' ';
        lexema = "";
        numeroSimbolo =0;
        numeroErrores = 0;
        fila = 1;
        columna = 0;

        for(indice = 0; indice <= cadenaEntrada.length(); indice++){
            if(indice != cadenaEntrada.length()){
                buscador = cadenaEntrada.charAt(indice);

            }else{
                buscador = ' ';
            }
            
            switch(estado){
             
                case 0:
                    if((buscador > 96 && buscador < 123) || ( buscador > 64 && buscador < 91)){
                        lexema += buscador;
                        estado = 1;
                        columna++;
                    }
                    // Digito
                    else if(buscador > 47 && buscador < 58){

                        lexema += buscador;
                        estado = 2;
                        columna++;
                    }
                                        // -> Simbolo de asignacion
                    else if(buscador == 45 ){
                        lexema += buscador;
                        estado = 3;
                        columna++;

                    }
                    // S = [ {, " , + , / , *, ; ,}, . , ',', |, <, >, %, ? ,]
                    else if((buscador == 123) ||(buscador ==34) || (buscador ==43 ) || (buscador == 47) ||
                            (buscador == 42) || (buscador == 59) || (buscador == 125) || (buscador == 46) ||
                            (buscador == 44)|| (buscador == 124)|| (buscador == 60)|| (buscador == 62)|| (buscador == 37)|| (buscador == 63)
                            || (buscador == 33)){
                        lexema += buscador;
                        estado = 4;
                        columna++;
                    }
                    break;
            } 
            
            
        }    
       
   }
   
    
}
