package ElevatorSystem.controllers;

import ElevatorSystem.containers.Building;
import ElevatorSystem.containers.Elevator;
import ElevatorSystem.models.ElevatorSystemModel;
import ElevatorSystem.views.FloorPickupsView;
import ElevatorSystem.views.LiftShaftColumnView;
import ElevatorSystem.views.LiftShaftView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystemController {

    @FXML
    Pane pane;

    @FXML
    GridPane gridPane;

    @FXML
    GridPane liftShafts;

    @FXML
    Button simulate;

    @FXML
    Label pickupText;


    private Building building;
    private ElevatorSystemModel elevatorSystemModel;
    private List<LiftShaftColumnView> liftShaftColumnViews = new ArrayList<>();
    private ArrayList<List<LiftShaftView>> floorPerElevatorViews = new ArrayList<>();

    public void init(ElevatorSystemModel elevatorSystemModel) {
        this.elevatorSystemModel = elevatorSystemModel;
        this.building = elevatorSystemModel.getBuilding();

        //fix pref size of components
        liftShafts.setPrefSize(70*building.getElevatorsNum(), 50*building.getFloors());
        pane.setPrefSize(80*building.getElevatorsNum()+200, 50*building.getFloors()+200);
        gridPane.setLayoutX(75*building.getElevatorsNum());
        simulate.setLayoutX(75*building.getElevatorsNum());
        pickupText.setLayoutX(75*building.getElevatorsNum());


        Image shaftBg = new Image("/img/szyb.png", 70, 50, false, true);

        for(int i=0; i< building.getElevatorsNum(); i++){
            //tworzy nową kolumnę
            LiftShaftColumnView liftShaftColumnView = new LiftShaftColumnView(i,this);

            //ustawia tła szybów
            liftShaftColumnView.setShaftImage(shaftBg);
            //ustawia ilość pięter
            liftShaftColumnView.setLiftShaft(building.getFloors());
            //dodaje kolumnę do listy
            liftShaftColumnViews.add(i, liftShaftColumnView);
            //get references to all shaft elements
            floorPerElevatorViews.add(liftShaftColumnView.getShafts());
            //dodaje kolumnę do grida
            liftShafts.add(liftShaftColumnView, i, 0);
        }

        //klawiatura do przywoływania piętra
        GridPane pickups = new FloorPickupsView(building.getFloors(), -1,this);
        gridPane.add(pickups,0,0);

        simulate.setOnAction(e -> buttonHandler(e));

    }

    private void buttonHandler(ActionEvent e){
        this.elevatorSystemModel.makeMove();
        updateSimulation();
    }

    public void pickup(double number, int tableNum){
        this.elevatorSystemModel.pickup(number, tableNum);
        updateSimulation();
    }

    private void updateSimulation(){
            for(Elevator elevator : this.elevatorSystemModel.getElevators()){ //todo problem z pojawianiem się
                for(int i=0;i< building.getFloors();i++){
                    if ( elevator.getCurrentFloor()%1 ==0 && i !=elevator.getCurrentFloor().intValue() ){
                        System.out.println("nie git " + i + " " + elevator.getCurrentFloor() + " " + elevator.getID());
                        this.floorPerElevatorViews.get(elevator.getID()).get(building.getFloors()-1-elevator.getCurrentFloor().intValue()).makeInvisible();
                    }
                    else if( i == elevator.getCurrentFloor().intValue() && elevator.getCurrentFloor() % 1 == 0){
//                        System.out.println("Git " + i + " " + elevator.getCurrentFloor());
                        this.floorPerElevatorViews.get(elevator.getID()).get(building.getFloors()-1-elevator.getCurrentFloor().intValue()).makeVisible();
                    }

//
                }


            }

    }

}
