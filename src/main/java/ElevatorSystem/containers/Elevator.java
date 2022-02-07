package ElevatorSystem.containers;

import java.util.ArrayList;
import java.util.Collections;

public class Elevator {

    private final int ID;
    private Double currentFloor;
    private Boolean occupied;
    private Integer MaxWeight;
    private Integer CurrentWeight;
    private Integer direction; //todo enum
    private ArrayList<Double> whereTo = new ArrayList<>();

    public Elevator(int id){
        this.ID = id;
        this.currentFloor = 0.0;
        this.occupied = false;
        this.direction = 0;
    }

    public int getID() {
        return ID;
    }

    public void setCurrentFloor(Double currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public void addWhereTo(Double floor) {
        if(this.currentFloor == floor.doubleValue())
            return;
        if(!this.whereTo.contains(floor))
            this.whereTo.add(floor);
        System.out.println(this.ID + " " + whereTo);
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public ArrayList<Double> getWhereTo() {
        return whereTo;
    }

    public Double getCurrentFloor() {
        return currentFloor;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    private void updateMove(Boolean up){
        if (up){
            this.currentFloor+=0.25;
            this.direction = +1;
        }
        else{
            this.currentFloor-=0.25;
            this.direction =-1;
        }
    }

    public void updateFloor() {

        if (this.whereTo.size()==0)
            this.direction = 0;
        else {
            Collections.sort(this.whereTo);

            if(this.getDirection() > 0) {
                updateMove(true);
            }
            else if(this.getDirection() < 0){
                Collections.reverse(this.whereTo);
                updateMove(false);
            }
            else {
                if (this.whereTo.get(0) > this.currentFloor)
                    updateMove(true);
                else
                    updateMove(false);
            }

            if(this.whereTo.contains(this.currentFloor)){
                int index = this.whereTo.indexOf(this.currentFloor);
                this.whereTo.remove(index);
                if(this.whereTo.size()>index){
                    double nextFloor = this.whereTo.get(index);
                    if(nextFloor> this.currentFloor)
                        updateMove(true);
                    else
                        updateMove(false);
                }
            }
        }

        System.out.println(this.ID + " Sorted " +  this.whereTo);

    }

}
