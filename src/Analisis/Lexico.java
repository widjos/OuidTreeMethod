
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
   public LinkedList<Entorno.Error> tablaErrores;
      
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
                    // Comentario de una linea
                    else if(buscador == 47 ){
                        lexema += buscador;
                        estado = 5;
                        columna++;
                    }
                    // comentario de multiple linea <
                    else if(buscador == 60 ){
                        lexema += buscador;
                        estado = 6;
                        columna++;
                    }
                    else if(buscador >= 32 && buscador <= 126){
                             lexema += buscador;
                             estado = 4;
                             columna++;
                    
                    }
                    else {
                        //guardarlo
                        if(buscador == ' ' || buscador == '\n' || buscador == '\t'){
                            validarEspacios(buscador);
                        }
                        else{
                           
                            lexema += buscador;
                            numeroErrores++;
                            tablaErrores.add(new Entorno.Error(numeroErrores, "Simbolo indefinido"+lexema, fila, columna));
                            columna++;
                            lexema="";
                            estado=0;
                        }
                    }                    
                    break;
                case 1:
                    //Viene letra recursivamente entonces el estado 1 regresa al estado 1
                    if((buscador > 64 && buscador < 91) || (buscador > 96 && buscador < 123)){
                        lexema += buscador;
                        columna++;
                        estado = 1;
                    }
                      // O viene un digito recursivamente
                    else if (buscador > 47 && buscador < 58) {

                        lexema += buscador;
                        columna++;
                        estado = 1;
                    }

                    // Ahora el analizador recibe "_" en un id

                    else if (buscador == 95) {
                        lexema += buscador;
                        columna++;
                        estado = 1;
                    }
                    else{
                        numeroSimbolo++;
                        tablaSimbolos.add(new Simbolo(numeroSimbolo,lexema,"TK_Id",fila,columna));
                        lexema="";
                        columna++;
                        estado=0;
                        validarEspacios(buscador);
                    }
                    break;
                case 2:
                    /// Si vienen digitos
                    if (buscador > 47 && buscador < 58)
                    {

                        lexema += buscador;
                        columna++;
                        estado = 2;

                    }
                    else if (buscador == 46)
                    {

                        lexema += buscador;
                        columna++;
                        estado = 16;

                    }
                    else {

                        numeroSimbolo++;
                        tablaSimbolos.add(new Simbolo(numeroSimbolo,lexema,"TK_Digito",fila,columna));
                        lexema = "";
                        columna++;
                        estado = 0;

                        validarEspacios(buscador);


                    }
                    break;
                case 16:
                    if (buscador > 47 && buscador < 58){
                        lexema += buscador;
                        columna++;
                        estado = 17;
                    }
                    else{
                        // Guardar el token aqui para luego continuar el analisis
                        validarErrores();
                            tablaErrores.add(new Entorno.Error(numeroErrores, "Simbolo indefinido"+lexema, fila, columna));
                        columna++;
                        lexema = "";
                        estado = 0;
                    }
                    break;
                case 17:
                    if (buscador > 47 && buscador < 58){
                        lexema += buscador;
                        columna++;
                        estado = 17;
                    }else{
                        numeroSimbolo++;
                        tablaSimbolos.add(new Simbolo(numeroSimbolo,lexema,"TK_Digito",fila,columna));
                        lexema = "";
                        columna++;
                        estado = 0;

                        validarEspacios(buscador);
                    }
                    break;
                case 3:
                    //viene -
                    if(buscador == 62){
                        lexema += buscador;
                        columna++;
                        estado = 18;
                    }
                    else{
                        numeroSimbolo++;
                        tablaSimbolos.add(new Simbolo(numeroSimbolo,lexema,"TK_Simbolo",fila,columna));                        
                        lexema = "";
                        columna++;
                        estado = 0;

                        validarEspacios(buscador);

                    }
                    break;
                case 18:
                    // viene >
                    numeroSimbolo++;
                    tablaSimbolos.add(new Simbolo(numeroSimbolo,lexema,devolverToken(lexema),fila,columna));    
                    lexema = "";
                    columna++;
                    estado = 0;

                    validarEspacios(buscador);
                    break;
                case 4:

                    numeroSimbolo++;
                    tablaSimbolos.add(new Simbolo(numeroSimbolo,lexema,devolverToken(lexema),fila,columna));    
                    lexema = "";
                    columna++;
                    estado = 0;                    
                     validarEspacios(buscador);                    
                        
   
                    

                    break;
                    
                case 6:
                    if(buscador == 33){
                        lexema += buscador;
                        columna++;
                        estado = 19;  
                        System.out.println("lexema"+ lexema);
                    
                    }else{
                        validarErrores();
                        tablaErrores.add(new Entorno.Error(numeroErrores, "Error indefinido "+lexema, fila, columna));
                        columna++;
                        lexema = "";
                        estado = 0;                       
                    
                    }                    
                    break;
                case 19:
                    // viene !
                    if(buscador == 33){
                        lexema += buscador;
                        columna++;
                        estado = 22;                    
                             System.out.println("lexema"+ lexema);
                    
                    }else{
                        columna++;
                        estado = 19;                      
                    }  

                    break;  
                case 22:
                    if(buscador == 62){
                        lexema += buscador;
                        columna++;
                        estado = 0;
                             System.out.println("lexema"+ lexema);
                    
                        lexema = "";
                    }
                    else if(buscador == 33){
                        lexema += buscador;
                             System.out.println("lexema"+ lexema);
                        columna++;
                        estado = 22;                    
                    
                    }                      
                    else{
                        validarErrores();
                        tablaErrores.add(new Entorno.Error(numeroErrores, "Error indefinido "+lexema, fila, columna));
                        columna++;
                        lexema = "";
                        estado = 0;                    
                    
                    }
                    break;
                case 5:
                    if(buscador == 47){
                        lexema += buscador;
                        columna++;
                        estado = 5;                     
                    
                    }else  if(buscador == 10){
                        lexema += buscador;
                        columna++;
                        estado = 0;
                        lexema = "";                     
                    }
                    else {
                        lexema += buscador;
                        columna++;
                        estado = 5;   
                    
                    }
                    break;
                    
            } 
            
            
        }    
       
   }
   
   
    private String devolverToken(String palabra)
    {

        palabra = palabra.toLowerCase();

        // Cambiar todo los iguales a matches()


        if (palabra.equals("}"))
        {
            return "TK_Cerrar";
        }
        else if (palabra.equals("\""))
        {
            return "TK_Comillas";
        }
        else if (palabra.equals("+"))
        {
            return "TK_Mas";
        }
        else if (palabra.equals("*"))
        {
            return "TK_Por";
        }
        else if (palabra.equals("?"))
        {
            return "TK_Interrogacion";
        }
        else if (palabra.equals("."))
        {
            return "TK_Concatenacion";
        }
        else if (palabra.equals("|"))
        {
            return "TK_Disyuncion";
        }
        else if (palabra.equals(":"))
        {
            return "TK_DosPuntos";
        }
        else if (palabra.equals(";"))
        {
            return "TK_PuntoComa";
        }
        else if (palabra.equals(","))
        {
            return "TK_Coma";
        }
        else if (palabra.equals("~"))
        {
            return "TK_Virgulilla";
        }         
        else if (palabra.equals("{"))
        {
            return "TK_Abrir";
        }
        else if (palabra.equals("%"))
        {
            return "TK_Porcentaje";
        }        
        else if (palabra.equals("->"))
        {
            return "TK_Asignar";

        }
        else {

            return "TK_Simbolo";
        }
    }

   
    private void validarErrores() {
        while (indice != cadenaEntrada.length() && cadenaEntrada.charAt(indice) != ' '
                && cadenaEntrada.charAt(indice) != '\n' && cadenaEntrada.charAt(indice)
                != '\t') {
            lexema += cadenaEntrada.charAt(indice);
            indice++;
            columna++;

        }
    }
    
    public void validarEspacios(char c)
    {
        if (c == ' ' || c == 9)
        {
            columna++;
        }
        else if (c == '\n')
        {
            fila++;
            columna = 0;
        }
        else
        {
            indice--;
        }
    }    
   
    
}
