package scgipp.ui.FXScenario;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

/**
 * User: hugo_<br/>
 * Date: 28/08/2017<br/>
 * Time: 22:34<br/>
 */
public class NodeCustomizer {

    static class Delta { double x, y; }

    private NodeCustomizer() {}

    public static void makeDraggable(Node node) {

        final Delta dragDelta = new Delta();
        node.setOnMousePressed(mouseEvent -> {
            dragDelta.x = node.getScene().getWindow().getX() - mouseEvent.getScreenX();
            dragDelta.y = node.getScene().getWindow().getY() - mouseEvent.getScreenY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.getScene().getWindow().setX(mouseEvent.getScreenX() + dragDelta.x);
            node.getScene().getWindow().setY(mouseEvent.getScreenY() + dragDelta.y);
        });

    }

    public static void makeDraggableWhenNotMaximized(Node node) {
        final Delta dragDelta = new Delta();
        node.setOnMousePressed(mouseEvent -> {
            if ( !((Stage)node.getScene().getWindow()).isMaximized() ) {
                dragDelta.x = node.getScene().getWindow().getX() - mouseEvent.getScreenX();
                dragDelta.y = node.getScene().getWindow().getY() - mouseEvent.getScreenY();
            }
        });

        node.setOnMouseDragged(mouseEvent -> {
            if ( !((Stage)node.getScene().getWindow()).isMaximized() ) {
                node.getScene().getWindow().setX(mouseEvent.getScreenX() + dragDelta.x);
                node.getScene().getWindow().setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });
    }

    public static void makeMovable(Node node) {

        final Delta dragDelta = new Delta();
        node.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = node.getLayoutX() - mouseEvent.getSceneX();
            dragDelta.y = node.getLayoutY() - mouseEvent.getSceneY();
            node.setCursor(Cursor.MOVE);
        });
        node.setOnMouseReleased(mouseEvent -> node.setCursor(Cursor.HAND));
        node.setOnMouseDragged(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
            node.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
        });
        node.setOnMouseEntered(mouseEvent -> node.setCursor(Cursor.HAND));

    }

    public static void setUpMenuBar(@NotNull Scenario scenario, @NotNull Pane menuBar, Button btExit, Button btMaximize, Button btHide) {

        makeDraggableWhenNotMaximized(menuBar);

        if (btExit != null)
            btExit.setOnAction(event -> scenario.finish());

        if (btMaximize != null) {
            btMaximize.setOnAction(event -> scenario.getStage().setMaximized(!scenario.getStage().isMaximized()));
            menuBar.setOnMouseClicked(event -> {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2)
                    scenario.getStage().setMaximized(!scenario.getStage().isMaximized());
            });
        }

        if (btHide != null) {
            //btHide.setOnAction(event -> scenario.stage.);
        }

    }

}
