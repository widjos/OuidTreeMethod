
package Entorno;

/**
 *
 * @author Wid
 */
public class Simbolo {

    /**
     * @return the simbolNumber
     */
    public int getSimbolNumber() {
        return simbolNumber;
    }

    /**
     * @param simbolNumber the simbolNumber to set
     */
    public void setSimbolNumber(int simbolNumber) {
        this.simbolNumber = simbolNumber;
    }

    /**
     * @return the lexema
     */
    public String getLexema() {
        return lexema;
    }

    /**
     * @param lexema the lexema to set
     */
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the filal
     */
    public int getFilal() {
        return filal;
    }

    /**
     * @param filal the filal to set
     */
    public void setFilal(int filal) {
        this.filal = filal;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    
    private int simbolNumber;
    private String lexema;
    private String token;
    private int filal;
    private int columna;

    public Simbolo(int simbolNumber, String lexema, String token, int filal, int columna) {
        this.simbolNumber = simbolNumber;
        this.lexema = lexema;
        this.token = token;
        this.filal = filal;
        this.columna = columna;
    }
    
    
    
    
}
