package model;

/**
 * Clase del modelo que se encarga de las variables se utilizan en el agoritmo y 
 * estan compuestas por el nombre y producciones.
 * @author Bryan Guapacha --> GitHub Account: BryangGF0822
 * @author Danna Garcia --> Github Account: Dannasofiagarcia
 *
 */
public class Variable {

    private String nombreVariable;
    private String producciones;

    /**
     * Metodo constructor encargado de generar los objetos de tipo variable.
     * @param nombreVariable: nombre que se le asigna a la variable. 
     * @param producciones: producciones que asocian a la variable.
     */
    public Variable(String nombreVariable, String producciones) {
        this.nombreVariable = nombreVariable;
        this.producciones = producciones;
    }
//----------------- Getters and Setters ----------------//
    public String getProducciones() {
        return producciones;
    }

    public String getNombreVariable() {
        return nombreVariable;
    }
}

//------------------------------------------------------//