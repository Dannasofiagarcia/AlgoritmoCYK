package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Algoritmo;
import model.Variable;

import java.applet.Applet;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static javafx.application.Application.launch;
/**
public class Main{

    private static Algoritmo algoritmo;

    public static void main(String[] args) {
        ArrayList<Variable> gramatica = new ArrayList<>();
        gramatica.add(new Variable("S", "AB"));
        gramatica.add(new Variable("A", "AA|a"));
        gramatica.add(new Variable("B", "b"));
        algoritmo = new Algoritmo(gramatica, "aab");
        algoritmo.algoritmoCYK();

        gramatica.clear();
        gramatica.add(new Variable("S", "BA|AC"));
        gramatica.add(new Variable("A", "CC|b"));
        gramatica.add(new Variable("B", "AB|a"));
        gramatica.add(new Variable("B", "BA|a"));

        algoritmo = new Algoritmo(gramatica, "bbab");
        algoritmo.algoritmoCYK();

    }
}
     ***/



public class Main extends Application {

    private MainViewController mainController;

    public Main(){
        mainController = new MainViewController();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("MainView.fxml"));
        fxml.setController(mainController);
        Parent root = fxml.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Algoritmo CYK");
        stage.show();
    }
}
