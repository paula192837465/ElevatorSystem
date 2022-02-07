package ElevatorSystem.models;

import ElevatorSystem.containers.Building;
import ElevatorSystem.containers.Elevator;

import java.util.ArrayList;

public class ElevatorSystemModel {

    private final Building building;
    private ArrayList<Elevator> elevators =new ArrayList<>(); //todo Observable
    private ArrayList<Double> pickups = new ArrayList<>();

    public ElevatorSystemModel(Building building)  {
        this.building = building;

        for(int i =0; i < this.building.getElevatorsNum(); i++){
            elevators.add(new Elevator(i));
        }
    }

    public void pickup(Double floor, int tableNum){
        if(tableNum==-1){
            pickups.add(floor);
            System.out.println(pickups);
            this.manageSystem();
        }
        else{
            for(Elevator elevator : elevators)
                if(elevator.getID() == tableNum){
                    elevator.addWhereTo(floor);
                    break;
                }
        }
    }

    public Building getBuilding() {
        return building;
    }

    private boolean canTakeElevator(Elevator elevator, Double pickupFloor){
        System.out.println(elevator.getID() + " " + elevator.getCurrentFloor() + " " + elevator.getDirection());
        return elevator.getDirection() == 0 ||
                elevator.getDirection() >0 && elevator.getCurrentFloor() + 0.5 <= pickupFloor ||
                elevator.getDirection() <0 && elevator.getCurrentFloor() -0.5 >= pickupFloor ;
    }

    private void sendPickupToElevator(Elevator elevator, Double pickupFloor, int index) {
        elevator.addWhereTo(pickupFloor);
        this.pickups.remove(index);
    }

    public void makeMove(){
        for(Elevator elevator : this.elevators){
            elevator.updateFloor();
            System.out.println("Lift " + elevator.getID() + " " + elevator.getCurrentFloor());
        }
    }

    public void manageSystem() {
        int i=-1;
        while(pickups.size()>0 && i < pickups.size()-1){
            i++;
            for(Elevator elevator : this.elevators){
                System.out.println("Current lift " + elevator.getID());
                if(canTakeElevator(elevator, pickups.get(i))){
                    sendPickupToElevator(elevator, pickups.get(i), i);
                    i=-1;
                    break;
                }
            }
            this.makeMove();
        }
    }

    public ArrayList<Elevator> getElevators() {
        return elevators;
    }
}
