package ElevatorSystem.controllers;

import ElevatorSystem.containers.Building;
import ElevatorSystem.containers.Elevator;
import ElevatorSystem.models.ElevatorSystemModel;
import ElevatorSystem.views.FloorPickupsView;
import ElevatorSystem.views.LiftShaftColumnView;
import ElevatorSystem.views.LiftShaftView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ElevatorSystemController {

    @FXML
    Pane pane;

    @FXML
    GridPane gridPane;

    @FXML
    GridPane liftShafts;

    @FXML
    Label pickupText;

    private Building building;
    private ElevatorSystemModel elevatorSystemModel;
    private final ArrayList<LiftShaftColumnView> liftShaftColumnViews = new ArrayList<>();
    private final ArrayList<ArrayList<LiftShaftView>> floorPerElevatorViews = new ArrayList<>();
    private Timer clock;

    public void init(ElevatorSystemModel elevatorSystemModel) {
        this.elevatorSystemModel = elevatorSystemModel;
        this.building = elevatorSystemModel.getBuilding();

        //fix pref size of components
        liftShafts.setPrefSize(70*building.getElevatorsNum(), 50*building.getFloors());
        pane.setPrefSize(80*building.getElevatorsNum()+200, 50*building.getFloors()+200);
        gridPane.setLayoutX(75*building.getElevatorsNum());
        pickupText.setLayoutX(75*building.getElevatorsNum());


        Image shaftBg = new Image("/img/shaft.png", 70, 50, false, true);

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


        //zegar obsuługujący symulację
        clock = new Timer();
        clock.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                simulate();
            }
        }, 0, 1500);
    }

    private void simulate(){
        this.elevatorSystemModel.makeMove();
        render();
    }

    public void pickup(double number, int tableNum){
        this.elevatorSystemModel.pickup(number, tableNum);
        render();
    }

    private void render() {
        for (Elevator elevator : this.elevatorSystemModel.getElevators()) {
            for (int i = 0; i < building.getFloors(); i++)
                if (elevator.getCurrentFloor() % 1 == 0 && i != elevator.getCurrentFloor().intValue())
                    this.floorPerElevatorViews.get(elevator.getID()).get(building.getFloors() - 1 - i).makeInvisible();

            if (elevator.getCurrentFloor() % 1 == 0)
                this.floorPerElevatorViews.get(elevator.getID()).get(building.getFloors() - 1 - elevator.getCurrentFloor().intValue()).makeVisible();

        }
    }
}
