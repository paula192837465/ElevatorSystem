package ElevatorSystem.containers;

public class Building {

    private final Integer floors;
    private Integer elevatorsNum;

    public Building(Integer floors, Integer elevatorsNum){
        this.floors = floors;
        this.elevatorsNum = elevatorsNum;
    }

    public Integer getFloors() {
        return floors;
    }

    public Integer getElevatorsNum() {
        return elevatorsNum;
    }
}
