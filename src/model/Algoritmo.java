package model;

import java.util.ArrayList;

/**
 * Clase Algoritmo que genera la estructura base para los CYK.
 * @author Bryan Guapacha --> GitHub Account: BryangGF0822
 * @author Danna Garcia --> Github Account: Dannasofiagarcia
 *
 */
public class Algoritmo {

    private ArrayList<Variable> gramatica;
    private String cadena;
    private String[][] resultado;

    /**
     * Metodo contructor
     * @param gramatica: ArrayList de variables que se almacenan.
     * @param cadena: Cadena alfabetica que entra como parametro.
     */
    public Algoritmo(ArrayList<Variable> gramatica, String cadena) {
        this.gramatica = gramatica;
        this.cadena = cadena;
        resultado = new String[gramatica.size()][gramatica.size()];
    }

    /**
     * Metodo de tipo Boolean que almacena todo el proceso del algoritmo CYK.
     * @return de tipo Boolean (false/true)
     */
    public boolean algoritmoCYK(){
        String subcadena = "";
        boolean agregado = false;
        boolean pertenece = false;
        int fila = 0;
        int columna = 0;
        int longitudMaxima = cadena.length();
        ArrayList<Variable> gramaticaResultado = new ArrayList<>();
        String[] tempSubcadenas;
        String[] tempS;
        Variable temp = null;


        //Longitud es j
        //i es la posición en la cadena w
        //aaa
        //012
        //01
        //12
        //2

        for(int longitud = 0; longitud < longitudMaxima; longitud++) {
            int posMaxima = cadena.length()-longitud;
            for (int i = 0; i < posMaxima; i++) {


                if (longitud == 0) {
                    subcadena = cadena.charAt(i) + "";
                    temp = producidaPor(gramatica, subcadena);
                    if (temp != null) {
                        resultado[fila][columna] = temp.getNombreVariable();
                        gramaticaResultado.add(new Variable(temp.getNombreVariable(), subcadena));
                    } else {
                        resultado[fila][columna] = "Null";
                    }
                } else {
                    //tempSubcadenas = cadena.split("");
                    //subcadena = obtenerPosicionesCadena(tempSubcadenas, i, longitud);
                    subcadena = "";
                    for(int k = 1; k < longitud+1 || k == 1; k++){
                        if(k > 1){
                            subcadena += ",";
                        }
                        if(resultado[i][k-1] != null) {
                            subcadena += resultado[i][k-1];
                        }
                        if(resultado[i + k][longitud - k] != null){
                            subcadena += resultado[i + k][longitud - k];
                        }
                    }

                    boolean primerElementoAgregado = false;
                    tempSubcadenas = subcadena.split(",");
                    for(int t = 0; t < tempSubcadenas.length; t++) {
                        temp = producidaPor(gramatica, tempSubcadenas[t]);
                        if(temp != null && primerElementoAgregado == false) {
                            resultado[fila][columna] = temp.getNombreVariable();
                        } else if( temp != null && primerElementoAgregado == true){
                            resultado[fila][columna] += ", " + temp.getNombreVariable();
                        }
                    }

                }


                /**
                else if(longitud == 2){
                    for (int j = 0; j < x.length(); j++) {
                        nombreGramatica += producidaPor(gramaticaResultado, x.substring(0, j)).getNombreVariable();
                    }
                    temp = new Variable(producidaPor(gramatica, nombreGramatica).getNombreVariable(), x);
                    gramaticaResultado.add(temp);
                }

                } else{

                    if(longitud > 2){
                            temp = producidaPor(gramaticaResultado, x.substring(i, longitud));
                            if(temp != null) {
                                nombreGramatica += temp.getNombreVariable();
                            }
                            temp = producidaPor(gramaticaResultado, x.substring(longitud, longitud+1));
                    }else {
                        for (int j = 0; j < x.length(); j++) {
                            nombreGramatica += producidaPor(gramaticaResultado, x.substring(0, j)).getNombreVariable();
                        }
                        temp = new Variable(producidaPor(gramatica, nombreGramatica).getNombreVariable(), x);
                        gramaticaResultado.add(temp);
                    }
                 ***/
                fila++;
                }
            columna++;
            fila = 0;
            imprimirMatriz();
            }
            if(resultado[0][longitudMaxima-1] != null){
                if(resultado[0][longitudMaxima-1].equals("S")) {
                    pertenece = true;
                }
            }
            System.out.println(pertenece);
            return pertenece;
        }


    /**
     * Metodo que retorna una variable producida por una gramatica y una cadena.
     * @param gramaticas: ArrayList de gramaticas de tipo Variable.
     * @param x: Cadena que entra como parametro.
     * @return Retorna un objeto de tipo variable.
     */
    public Variable producidaPor(ArrayList<Variable> gramaticas, String x){
        boolean flag = false;
        Variable buscada = null;
        for(int i = 0; i < gramaticas.size() && !flag; i++){
            if(gramaticas.get(i).getProducciones().contains(x)){
                flag = true;
                buscada = gramaticas.get(i);
            }
        }
        return buscada;
    }
    /**
     * Metodo que permite obtener las posiciones en una cadena.
     * @param cadena: Arreglo que contiene la cadena a analizar.
     * @param inicio: Posicion inicial de la cadena, el primer caracter.
     * @param longitud: Tamaño o longitud de la cadena.
     * @return
     */
    public String obtenerPosicionesCadena(String[] cadena, int inicio, int longitud){
        String resultado = "";
        int longitudRecorrida = 0;
        if(cadena.length >= inicio+longitud) {
            for (int i = inicio; i < cadena.length && longitudRecorrida < longitud; i++) {
                resultado += cadena[i];
                longitudRecorrida++;
            }
        }
        return resultado;
    }
    
    /**
     * Metodo que me permite imprimir la matriz.
     * Este metodo es de tipo Void y no recibe parametros.
     */
    public void imprimirMatriz() {
        System.out.println();
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[i].length; j++) {
                System.out.print(resultado[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * Metodo que me permite agregar una variable a la gramatica.
     * @param variable: Objeto de tipo Variable que se desea agregar a la lista.
     */
    public void agregarVariable(Variable variable){
        gramatica.add(variable);
    }

//------------- Metodos Getters and Setters -------------//
    
    public ArrayList<Variable> getGramatica() {
        return gramatica;
    }

    public String getCadena() {
        return cadena;
    }

    public String[][] getResultado() {
        return resultado;
    }
}

//------------------------------------------------------//
