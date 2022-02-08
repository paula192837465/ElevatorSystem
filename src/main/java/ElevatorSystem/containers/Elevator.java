package ElevatorSystem.containers;

import java.util.ArrayList;
import java.util.Collections;

public class Elevator {

    private final int ID;
    private Double currentFloor;
    private Direction direction;
    private ArrayList<Double> whereTo = new ArrayList<>();

    public Elevator(int id){
        this.ID = id;
        this.currentFloor = 0.0;
        this.direction = Direction.STOP;
    }

    public int getID() {
        return ID;
    }

    public void setCurrentFloor(Double currentFloor) {
        this.currentFloor = currentFloor;
    }

    //dodanie zlecenia do tablicy zleceń windy
    public void addWhereTo(Double floor) {
        if(this.currentFloor == floor.doubleValue())
            return;
        if(!this.whereTo.contains(floor))
            this.whereTo.add(floor);
        System.out.println(this.ID + " " + whereTo);
    }

    public ArrayList<Double> getWhereTo() {
        return whereTo;
    }

    public Double getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //wykonanie kroku w górę/w dół
    private void updateMove(Boolean up){
        if (up){
            this.currentFloor+=0.25;
            this.direction = Direction.UP;
        }
        else{
            this.currentFloor-=0.25;
            this.direction = Direction.DOWN;
        }
    }

    //mechanizm poruszania się windy - tutaj decyduje gdzie jedzie w następnym kroku symulacji
    public void updateFloor() {
        if (this.whereTo.size()==0)
            this.direction = Direction.STOP;
        else {
            Collections.sort(this.whereTo);

            if(this.getDirection() == Direction.UP) {
                updateMove(true);
            }
            else if(this.getDirection() == Direction.DOWN){
                Collections.reverse(this.whereTo);
                updateMove(false);
            }
            else {
                updateMove(this.whereTo.get(0) > this.currentFloor);
            }

            if(this.whereTo.contains(this.currentFloor)){
                int index = this.whereTo.indexOf(this.currentFloor);
                this.whereTo.remove(index);
                if(this.whereTo.size()>index){
                    double nextFloor = this.whereTo.get(index);
                    if(nextFloor> this.currentFloor)
                        this.direction = Direction.UP;
                    else
                        this.direction = Direction.DOWN;
                }
                else if(this.whereTo.size()==index && this.whereTo.size()!=0){
                    this.direction = direction.opposite();
                }
            }
        }

        //Wyświetlanie aktualnego stanu danej windy
        System.out.println("Lift " + this.ID + " on floor " + this.getCurrentFloor() + " Sorted " +  this.whereTo);
    }

}
