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
public abstract class WidgetCustomizer {

    static class Delta { double x, y; }

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

    public static void makeKiller(Node node) {
        node.setOnMousePressed(mouseEvent -> {
            System.exit(0);
        });
    }

}
