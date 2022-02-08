package ElevatorSystem.models;

import ElevatorSystem.containers.Building;
import ElevatorSystem.containers.Direction;
import ElevatorSystem.containers.Elevator;

import java.util.ArrayList;

public class ElevatorSystemModel {

    private final Building building;
    private ArrayList<Elevator> elevators =new ArrayList<>();
    private ArrayList<Double> pickups = new ArrayList<>();


    public ElevatorSystemModel(Building building)  {
        this.building = building;

        for(int i =0; i < this.building.getElevatorsNum(); i++){
            elevators.add(new Elevator(i));
        }
    }

    //pryjęcie przez system, że ktoś przywołał windę (tableNum==-1) lub wybrał piętro bedąc w środku
    public void pickup(Double floor, int tableNum){

        if(tableNum==-1){
            for(Elevator elevator : elevators){
                if(elevator.getWhereTo().contains(floor)){
                    this.makeMove();
                    return;
                }
            }
            pickups.add(floor);
            System.out.println(pickups);
            this.manageSystem();
        }
        else{
            for(Elevator elevator : elevators)
                if(elevator.getID() == tableNum){
                    elevator.addWhereTo(floor);
                    elevator.updateFloor();
                    break;
                }
        }
    }

    public Building getBuilding() {
        return building;
    }

    //sprawdzenie czy dana winda może przyjąć zlecenie
    private boolean canTakeElevator(Elevator elevator, Double pickupFloor){
        return elevator.getDirection() == Direction.STOP ||
                elevator.getDirection() == Direction.UP && elevator.getCurrentFloor() + 0.5 <= pickupFloor ||
                elevator.getDirection() == Direction.DOWN && elevator.getCurrentFloor() -0.5 >= pickupFloor ;
    }

    //wysłanie zlecenia do konkretnej windy
    private void sendPickupToElevator(Elevator elevator, Double pickupFloor, int index) {
        elevator.addWhereTo(pickupFloor);
        this.pickups.remove(index);
    }

    //aktualizowanie pozycji windy
    public void makeMove(){
        System.out.println("\n-----------------------------\n");
        for(Elevator elevator : this.elevators){
            elevator.updateFloor();
        }
    }

    //metoda rozdzielająca zlecenia pomiędzy windy
    public void manageSystem() {
        int i=-1;
        while(pickups.size()>0 && i < pickups.size()-1){
            i++;
            for(Elevator elevator : this.elevators){
                if(canTakeElevator(elevator, pickups.get(i))){
                    sendPickupToElevator(elevator, pickups.get(i), i);
                    i=-1;
                    break;
                }
            }

            //jeśli każda winda jest zajęta, a wezwanie jest nie po drodze, to dajemy to zlecenie windzie, która ma ich najmniej
            if(pickups.size()>0){
                int min= building.getFloors();
                Elevator potentialElevator = null;
                for(Elevator elevator : elevators){
                    if(elevator.getWhereTo().size()<min){
                        min = elevator.getWhereTo().size();
                        potentialElevator = elevator;
                    }
                }
                while(pickups.size()>0)
                sendPickupToElevator(potentialElevator, pickups.get(0),0 );
            }
            this.makeMove();
        }
    }

    public ArrayList<Elevator> getElevators() {
        return elevators;
    }
}
