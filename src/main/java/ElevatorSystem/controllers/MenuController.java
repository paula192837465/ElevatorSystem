package ElevatorSystem.controllers;

import ElevatorSystem.models.MenuModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MenuController {

    @FXML
    AnchorPane dataPane;

    @FXML
    Button simulationButton;

    @FXML
    TextField FloorsNumField, ElevatorNumField;


    private MenuModel menuModel;

    public void init(MenuModel menuModel){
        this.menuModel = menuModel;

        simulationButton.disableProperty().bind(
                Bindings.isEmpty(FloorsNumField.textProperty())
                        .or(Bindings.isEmpty(ElevatorNumField.textProperty()))
        );
    }

    @FXML
    public void startSimulationPressed(){
        this.getDataAndStart();
    }

    public void getDataAndStart(){
        String elevatorNum = ElevatorNumField.getText();
        String floorsNum = FloorsNumField.getText();
        if(Integer.parseInt(elevatorNum) <=0 || Integer.parseInt(floorsNum) <=0)
            throw new IllegalArgumentException("You need to enter a positive numbers in both TextFields");
        menuModel.startSimulation(Integer.parseInt(elevatorNum), Integer.parseInt(floorsNum));
    }
}
