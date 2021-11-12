package model;

public class Variable {

    private String nombreVariable;
    private String producciones;

    public Variable(String nombreVariable, String producciones) {
        this.nombreVariable = nombreVariable;
        this.producciones = producciones;
    }

    public String getProducciones() {
        return producciones;
    }

    public String getNombreVariable() {
        return nombreVariable;
    }
}
