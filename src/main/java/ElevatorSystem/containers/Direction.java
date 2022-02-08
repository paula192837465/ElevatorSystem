package ElevatorSystem.containers;

public enum Direction {
    UP,
    DOWN,
    STOP;

    public Direction opposite(){
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            default -> null;
        };
    }

    public Integer toNumber(){
        return switch (this) {
            case UP -> 1;
            case DOWN -> -1;
            case STOP -> 0;
        };
    }
}
