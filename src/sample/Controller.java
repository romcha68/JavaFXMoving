package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    public interface Direction {
        String UP = "up";
        String DOWN = "down";
        String LEFT = "left";
        String RIGHT = "right";
    }

    @FXML
    private AnchorPane root;

    @FXML
    private ToggleGroup select;

    @FXML
    private RadioButton clockwiseSelect;

    @FXML
    private Label movingLabel;

    private boolean clockwise = true;
    private String direction = Direction.RIGHT;

    final Timer time = new Timer();

    @FXML
    void selectChange(ActionEvent event) {


        clockwise = clockwiseSelect.isSelected();
        switch (direction) {
            case (Direction.RIGHT):
                direction = Direction.LEFT;
                break;
            case (Direction.LEFT):
                direction = Direction.RIGHT;
                break;
            case (Direction.DOWN):
                direction = Direction.UP;
                break;
            case (Direction.UP):
                direction = Direction.DOWN;
                break;
        }
    }

    @FXML
    public void initialize() {

        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveLabel();
            }
        }, 100, 100);
    }

    public void moveLabel() {
        switch (direction) {
            case (Direction.RIGHT):
                AnchorPane.setLeftAnchor(movingLabel, AnchorPane.getLeftAnchor(movingLabel) + 10);
                AnchorPane.setRightAnchor(movingLabel, AnchorPane.getRightAnchor(movingLabel) - 10);
                if (AnchorPane.getRightAnchor(movingLabel) < 10) {
                    if (clockwise) {
                        direction = Direction.DOWN;
                    } else {
                        direction = Direction.UP;
                    }
                }
                break;
            case (Direction.DOWN):
                AnchorPane.setTopAnchor(movingLabel, AnchorPane.getTopAnchor(movingLabel) + 10);
                AnchorPane.setBottomAnchor(movingLabel, AnchorPane.getBottomAnchor(movingLabel) - 10);
                if (AnchorPane.getBottomAnchor(movingLabel) < 10) {
                    if (clockwise) {
                        direction = Direction.LEFT;
                    } else {
                        direction = Direction.RIGHT;
                    }
                }
                break;
            case (Direction.LEFT):
                AnchorPane.setLeftAnchor(movingLabel, AnchorPane.getLeftAnchor(movingLabel) - 10);
                AnchorPane.setRightAnchor(movingLabel, AnchorPane.getRightAnchor(movingLabel) + 10);
                if (AnchorPane.getLeftAnchor(movingLabel) < 10) {
                    if (clockwise) {
                        direction = Direction.UP;
                    } else {
                        direction = Direction.DOWN;
                    }
                }
                break;
            case (Direction.UP):
                AnchorPane.setTopAnchor(movingLabel, AnchorPane.getTopAnchor(movingLabel) - 10);
                AnchorPane.setBottomAnchor(movingLabel, AnchorPane.getBottomAnchor(movingLabel) + 10);
                if (AnchorPane.getTopAnchor(movingLabel) < 10) {
                    if (clockwise) {
                        direction = Direction.RIGHT;
                    } else {
                        direction = Direction.LEFT;
                    }
                }
        }
    }
}
