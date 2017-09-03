package scgipp.ui.framework;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * User: hugo_<br/>
 * Date: 28/08/2017<br/>
 * Time: 22:34<br/>
 */
public class WidgetCustomizer {

    static class Delta { double x, y; }

    private WidgetCustomizer() {}

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

}
