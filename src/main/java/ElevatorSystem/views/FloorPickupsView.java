package ElevatorSystem.views;

import ElevatorSystem.controllers.ElevatorSystemController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class FloorPickupsView extends GridPane{
    private final GridPane liftPickups;
    private int floorsNum;
    private ArrayList<Button> pickupsButtons = new ArrayList<>();
    private ElevatorSystemController elevatorSystemController;
    private int pickupTableNum;

    public FloorPickupsView(int floors, int tableNum, ElevatorSystemController elevatorSystemController){

        this.pickupTableNum = tableNum;
        this.elevatorSystemController =elevatorSystemController;
        this.floorsNum =floors;
        liftPickups = new GridPane();
        liftPickups.setPadding(new Insets(5, 0, 0, 5));
        this.getChildren().add(liftPickups);
        GridPane.setRowIndex(liftPickups, 0);
        GridPane.setColumnIndex(liftPickups, 1);
        GridPane.setRowSpan(liftPickups, 1);

        createFloorPickupView();
    }

    //stworzenie klawiatury numerycznej do przywoływania wind
    public void createFloorPickupView(){
        for (int n = 1; n< floorsNum+1; n++) {
            Button button = createNumberButton(n-1);
            pickupsButtons.add(button);
            int row = (n-1) / 3;
            int col = (n-1) % 3 ;
            liftPickups.add(button, col,  row);
        }
    }

    private Button createNumberButton(Integer number) {
        Button button = createButton(Integer.toString(number));
        button.setOnAction(e -> buttonHandler(e, number.doubleValue()));
        return button ;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        if(pickupTableNum==-1)
            button.setPrefSize(50.0, 50.0);
        else
            button.setPrefSize(10.0, 10.0);

        return button ;
    }

    public ArrayList<Button> getPickupsButtons() {
        return pickupsButtons;
    }

    //obsługa naciśnięcia danego przycisku na klawiaturze
    private void buttonHandler(ActionEvent e, Double number){
        System.out.println(number);
        this.elevatorSystemController.pickup(number, pickupTableNum);
    }
}
