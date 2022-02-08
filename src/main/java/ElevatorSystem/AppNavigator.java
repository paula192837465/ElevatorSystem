package ElevatorSystem;

import ElevatorSystem.containers.Building;
import ElevatorSystem.controllers.ElevatorSystemController;
import ElevatorSystem.controllers.MenuController;
import ElevatorSystem.models.ElevatorSystemModel;
import ElevatorSystem.models.MenuModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppNavigator extends Application {

    MenuModel menuModel = new MenuModel(this);
    Stage primaryStage;

    public AppNavigator() throws InterruptedException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            this.primaryStage = stage;
            this.primaryStage.setTitle("Elevator System");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuView.fxml"));
            Parent root = loader.load();

            // set initial data into controller
            MenuController menuController = loader.getController();
            menuController.init(menuModel);

            // add layout to a scene and show them all
            Scene scene = new Scene(root);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startSimulation(int elevatorsNum, int floorsNum){
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ElevatorSystemView.fxml"));
            Parent root = loader.load();

            Building building = new Building(floorsNum, elevatorsNum);
            ElevatorSystemModel elevatorSystemModel = new ElevatorSystemModel(building);

            // set initial data into controller
            ElevatorSystemController elevatorSystemController = loader.getController();
            elevatorSystemController.init(elevatorSystemModel);

            // add layout to a scene and show them all
            Scene scene = new Scene(root);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
