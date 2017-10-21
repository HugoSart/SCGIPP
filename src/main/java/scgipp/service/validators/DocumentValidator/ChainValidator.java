package scgipp.service.validators.DocumentValidator;

public class ChainValidator {

    private String documentNumber;

    protected ChainValidator() {}

    protected boolean validCpfChain(){
        if (documentNumber.equals("00000000000") || documentNumber.equals("11111111111") ||
                documentNumber.equals("22222222222") || documentNumber.equals("33333333333") ||
                documentNumber.equals("44444444444") || documentNumber.equals("55555555555") ||
                documentNumber.equals("66666666666") || documentNumber.equals("77777777777") ||
                documentNumber.equals("88888888888") || documentNumber.equals("99999999999") ||
                (documentNumber.length() != 11)) return(false);
        else return true;
    }

    protected boolean validCnpjChain(){
        if (documentNumber.equals("00000000000000") || documentNumber.equals("11111111111111") ||
                documentNumber.equals("22222222222222") || documentNumber.equals("33333333333333") ||
                documentNumber.equals("44444444444444") || documentNumber.equals("55555555555555") ||
                documentNumber.equals("66666666666666") || documentNumber.equals("77777777777777") ||
                documentNumber.equals("88888888888888") || documentNumber.equals("99999999999999") ||
                (documentNumber.length() != 14)) return false;
        else return true;
    }

    protected void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
