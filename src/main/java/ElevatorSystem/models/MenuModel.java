package ElevatorSystem.models;

import ElevatorSystem.AppNavigator;

public class MenuModel {

    private final AppNavigator appNavigator;
    public MenuModel(AppNavigator appNavigator){
        this.appNavigator = appNavigator;
    }

    public void startSimulation(int elevatorsNum, int floorsNum){
        appNavigator.startSimulation(elevatorsNum, floorsNum);
    }


}
