package scgipp.ui.visible;

import javafx.beans.property.*;
import scgipp.service.entities.Devolution;
import java.util.ArrayList;
import java.util.List;

public class ObservableDevolution {
    private Devolution devolution;

    public ObservableDevolution(Devolution devolution) {
        this.devolution = devolution;
    }

    public Devolution getDevolution() {
        return devolution;
    }

    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(devolution.getId());
    }

    public IntegerProperty saleIdProperty() { return new SimpleIntegerProperty(devolution.getSale().getId()); }

    public StringProperty productNameProperty() {
        return new SimpleStringProperty(devolution.getProduct().getName());
    }

    public BooleanProperty restoredProperty() { return new SimpleBooleanProperty(devolution.getRestoreToStock()); }

    public StringProperty devolutionDateProperty() { return new SimpleStringProperty(devolution.getDevolutionDate()); }

    public IntegerProperty quantityProperty() { return new SimpleIntegerProperty(devolution.getQuantity()); }

    public static List<ObservableDevolution> devolutionListTAsObservableDevolutionList(List<Devolution> list) {

        List<ObservableDevolution> observableDevolutions = new ArrayList<>();
        for (Devolution devolution : list)
            observableDevolutions.add(new ObservableDevolution(devolution));
        return observableDevolutions;
    }
}
