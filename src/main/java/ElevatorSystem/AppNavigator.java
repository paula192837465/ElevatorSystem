package ElevatorSystem;

import ElevatorSystem.containers.Building;
import ElevatorSystem.controllers.ElevatorSystemController;
import ElevatorSystem.models.ElevatorSystemModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppNavigator extends Application {
    Building building = new Building(10, 4);
    ElevatorSystemModel elevatorSystemModel = new ElevatorSystemModel(building);

    public AppNavigator() throws InterruptedException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {

            stage.setTitle("Elevator System");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ElevatorSystemView.fxml"));
            Parent root = loader.load();

            // set initial data into controller
            ElevatorSystemController elevatorSystemController = loader.getController();
            elevatorSystemController.init(elevatorSystemModel);

            // add layout to a scene and show them all
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }


}
