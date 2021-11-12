package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Algoritmo;
import model.Variable;

import java.awt.*;
import java.util.ArrayList;

public class MainViewController {

    private int layoutXtf1;

    private int layoutXlabel;

    private int layoutXtf2;

    private int layoutY;

    private ArrayList<Variable> gramatica;

    private Algoritmo algoritmo;

    @FXML
    private GridPane resultadoGrid;


    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Pane cadenaWPane;

    @FXML
    private TextField cadenawTF;

    @FXML
    private Label resultadoLabel;

    @FXML
    private Pane cantidadVariablesPane;

    @FXML
    private TextField cantidadVariablesTF;

    @FXML
    private Button definirCantidadBtn;

    @FXML
    private Button definirGramaticaBtn;

    @FXML
    private AnchorPane gramaticaPane;

    @FXML
    private Button ingresarCadenaBtn;

    @FXML
    private Button reiniciarBtn;

    @FXML
    private Pane tablaCYKPane;

    @FXML
    void ingresarCadenaBtn(ActionEvent event) {
        if(cadenawTF.getText().equals("")) {
            Toolkit.getDefaultToolkit().beep();
            Alert optionPane = new Alert(Alert.AlertType.WARNING, "¡No ingreso la cadena!");
            optionPane.show();
        }else{
            algoritmo = new Algoritmo(gramatica, cadenawTF.getText());
            ingresarCadenaBtn.setDisable(true);
            rellenarTabla();
        }
    }

    void rellenarTabla(){
        boolean pertenece = algoritmo.algoritmoCYK();
        String [][] resultado = algoritmo.getResultado();
        for(int fila = 0; fila < resultado.length; fila++) {
            for(int columna = 0; columna < resultado[fila].length; columna++){
                TextField t = new TextField();
                t.setText(resultado[fila][columna]);
                t.setDisable(true);
                resultadoGrid.add(t, columna, fila);
            }
        }
        if(pertenece) {
            resultadoLabel.setText("El lenguaje si pertenece a la grámatica");
        }
        tablaCYKPane.setVisible(true);
    }

    @FXML
    void reiniciar(ActionEvent event) {
        initialize();
        scrollPane.setVisible(true);
        cantidadVariablesPane.setVisible(true);
        cadenawTF.setText("");
        reiniciarBtn.setVisible(false);
        definirGramaticaBtn.setVisible(true);
        cadenaWPane.setVisible(false);
        cantidadVariablesTF.setText("");
        ingresarCadenaBtn.setDisable(false);
        definirCantidadBtn.setVisible(true);
        definirGramaticaBtn.setDisable(true);
    }


    @FXML
    void initialize(){
        gramatica = new ArrayList<>();

    }


    @FXML
    void definirCantidadVariables(ActionEvent event) {
        layoutXtf1 = 159;
        layoutXtf2 = 318;
        layoutXlabel = 215;
        layoutY = 20; //+48
        int cantidadVariables = 0;

        gramaticaPane.getChildren().clear();

        try {
            if(cantidadVariablesTF.getText().equals("")){
                Toolkit.getDefaultToolkit().beep();
                Alert optionPane = new Alert(Alert.AlertType.WARNING, "¡No ingreso la cantidad de variables!");
                optionPane.show();
            }else {
                cantidadVariables = Integer.parseInt(cantidadVariablesTF.getText());
            }
        } catch (NumberFormatException e){
            Toolkit.getDefaultToolkit().beep();
            Alert optionPane = new Alert(Alert.AlertType.WARNING, "¡Solo se admiten números!");
            optionPane.show();
        }

        if(cantidadVariables < 13) {

            //v produce w
            for (int i = 0; i < cantidadVariables; i++) {
                TextField tf1 = new TextField();
                tf1.setMinWidth(41);
                tf1.setMinHeight(24);
                tf1.setPrefWidth(42);
                tf1.setMinWidth(25);
                tf1.setMaxHeight(26);
                tf1.setMaxWidth(43);
                tf1.setLayoutX(layoutXtf1);
                tf1.setLayoutY(layoutY + (48 * i));
                tf1.setId("v" + i);

                TextField tf2 = new TextField();
                // tf2.setMinWidth(148);
                //tf2.setMinHeight(24);
                // tf2.setPrefWidth(149);
                // tf2.setMinWidth(25);
                // tf2.setMaxHeight(150);
                // tf2.setMaxWidth(26);
                tf2.setLayoutX(layoutXtf2);
                tf2.setLayoutY(layoutY + (48 * i));
                tf2.setId("w" + i);

                Label label = new Label("produce a");
                label.setFont(new Font("Arial", 21));
                label.setLayoutX(layoutXlabel);
                label.setLayoutY(layoutY + (48 * i));
                gramaticaPane.getChildren().add(tf1);
                gramaticaPane.getChildren().add(tf2);
                gramaticaPane.getChildren().add(label);
            }
            definirGramaticaBtn.setDisable(false);
        } else{
            Toolkit.getDefaultToolkit().beep();
            Alert optionPane = new Alert(Alert.AlertType.WARNING, "¡La cantidad debe ser menor que 13!");
            optionPane.show();
        }
    }

    @FXML
    void definirGramatica(ActionEvent event) {
        boolean textFieldVacios = false;
        boolean encontrado = false;
        int index = 0;
        for(Node nodo : gramaticaPane.getChildren()){
            if(nodo instanceof TextField){
                TextField tf = (TextField) nodo;
                if(tf.getText().equals("")){
                    textFieldVacios = true;
                }
            }
        }
        if(textFieldVacios == true){
            Toolkit.getDefaultToolkit().beep();
            Alert optionPane = new Alert(Alert.AlertType.WARNING, "¡No ingreso alguno de los datos necesarios!");
            optionPane.show();
        } else{
            for(int i = 0; i < gramaticaPane.getChildren().size(); i++){
                encontrado = false;
                Node nodo = gramaticaPane.getChildren().get(i);
                if(nodo instanceof TextField){
                    if(nodo.getId().equals("v"+index)){
                        TextField tf1 = (TextField) nodo;
                        for(int j = 0; j < gramaticaPane.getChildren().size() && !encontrado; j++){
                            Node nodo2 = gramaticaPane.getChildren().get(j);
                            if(nodo2 instanceof TextField){
                                if(nodo2.getId().equals("w"+index)){
                                    TextField tf2 = (TextField) nodo2;
                                    Variable variable = new Variable(tf1.getText(), tf2.getText());
                                    gramatica.add(variable);
                                    encontrado = true;
                                    index++;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(gramatica.size());
        gramaticaPane.getChildren().clear();
        gramaticaPane.setVisible(false);
        scrollPane.setVisible(false);
        cantidadVariablesPane.setVisible(false);
        cadenaWPane.setVisible(true);
        definirGramaticaBtn.setVisible(false);
        reiniciarBtn.setVisible(true);
        definirCantidadBtn.setVisible(false);
    }

}
