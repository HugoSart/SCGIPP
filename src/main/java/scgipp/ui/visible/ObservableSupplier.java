package scgipp.ui.visible;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import scgipp.service.entities.Supplier;

import java.util.ArrayList;
import java.util.List;

public class ObservableSupplier {

    private Supplier supplier;

    public ObservableSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public StringProperty nameProperty(){
        return new SimpleStringProperty(supplier.getName());
    }

    public StringProperty cnpjProperty(){
        return new SimpleStringProperty(supplierCNPJ());
    }

    public static List<ObservableSupplier> supplierListAsObervableSupplierList(List<Supplier> supplierList) {

        List<ObservableSupplier> observableSuppliers = new ArrayList<>();

        for (Supplier supplier : supplierList) {
            observableSuppliers.add(new ObservableSupplier(supplier));
        }
        return observableSuppliers;
    }

    private String supplierCNPJ() {
        String label = "00.000.000/0000-00";
        String supplierCNPJ = supplier.getCpf_cnpj();
        StringBuilder newString = new StringBuilder();
        char c;
        int k = 0;

        for (int i = 0; i < label.length(); i++) {
            c = supplierCNPJ.charAt(k);

            if (label.charAt(i) == '.') {
                newString.append('.');
            }
            else if (label.charAt(i) == '/') {
                newString.append('/');
            }
            else if (label.charAt(i) == '-'){
                newString.append('-');
            }
            else {
                newString.append(c);
                k++;
            }
        }
        return newString.toString();
    }
}
