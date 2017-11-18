<<<<<<< HEAD
package scgipp.service.validators.DocumentValidator;

class ChainValidator {

    private String documentNumber;

    ChainValidator() {}

    boolean validCpfChain(){
        return !documentNumber.equals("00000000000") && !documentNumber.equals("11111111111") &&
                !documentNumber.equals("22222222222") && !documentNumber.equals("33333333333") &&
                !documentNumber.equals("44444444444") && !documentNumber.equals("55555555555") &&
                !documentNumber.equals("66666666666") && !documentNumber.equals("77777777777") &&
                !documentNumber.equals("88888888888") && !documentNumber.equals("99999999999") &&
                (documentNumber.length() == 11);
    }

    boolean validCnpjChain(){
        return !documentNumber.equals("00000000000000") && !documentNumber.equals("11111111111111") &&
                !documentNumber.equals("22222222222222") && !documentNumber.equals("33333333333333") &&
                !documentNumber.equals("44444444444444") && !documentNumber.equals("55555555555555") &&
                !documentNumber.equals("66666666666666") && !documentNumber.equals("77777777777777") &&
                !documentNumber.equals("88888888888888") && !documentNumber.equals("99999999999999") &&
                (documentNumber.length() == 14);
    }

    String stringCleaner(String documentNumber) {
        StringBuilder stringCleaned = new StringBuilder();
        char c;

        for (int i = 0; i < documentNumber.length(); i++) {
            c = documentNumber.charAt(i);

            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
                stringCleaned.append(c);
            }
        }
        return stringCleaned.toString();
    }

    void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
=======
package scgipp.service.validators.DocumentValidator;

class ChainValidator {

    private String documentNumber;

    ChainValidator() {}

    boolean validCpfChain(){
        return !documentNumber.equals("00000000000") && !documentNumber.equals("11111111111") &&
                !documentNumber.equals("22222222222") && !documentNumber.equals("33333333333") &&
                !documentNumber.equals("44444444444") && !documentNumber.equals("55555555555") &&
                !documentNumber.equals("66666666666") && !documentNumber.equals("77777777777") &&
                !documentNumber.equals("88888888888") && !documentNumber.equals("99999999999") &&
                (documentNumber.length() == 11);
    }

    boolean validCnpjChain(){
        return !documentNumber.equals("00000000000000") && !documentNumber.equals("11111111111111") &&
                !documentNumber.equals("22222222222222") && !documentNumber.equals("33333333333333") &&
                !documentNumber.equals("44444444444444") && !documentNumber.equals("55555555555555") &&
                !documentNumber.equals("66666666666666") && !documentNumber.equals("77777777777777") &&
                !documentNumber.equals("88888888888888") && !documentNumber.equals("99999999999999") &&
                (documentNumber.length() == 14);
    }

    String stringCleaner(String documentNumber) {
        StringBuilder stringCleaned = new StringBuilder();
        char c;

        for (int i = 0; i < documentNumber.length(); i++) {
            c = documentNumber.charAt(i);

            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
                stringCleaned.append(c);
            }
        }
        return stringCleaned.toString();
    }

    void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
>>>>>>> [C]ObservableCustomer
