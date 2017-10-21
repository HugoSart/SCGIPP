package scgipp.service.validators.DocumentValidator;

import java.util.InputMismatchException;

public class DocumentValidator {

    private String documentNumber;
    private SecurityCalculator securityCalculator = new SecurityCalculator();
    private ChainValidator chainValidator = new ChainValidator();

    public boolean isValidCPF(String CPF){
        documentNumber = CPF;
        securityCalculator.setDocumentNumber(documentNumber);
        chainValidator.setDocumentNumber(documentNumber);


        if(chainValidator.validCpfChain() && validCPFSecurityDigits()) return true;
        else return false;
    }

    public boolean isValidCPNJ(String CNPJ){
        documentNumber = CNPJ;
        securityCalculator.setDocumentNumber(documentNumber);
        chainValidator.setDocumentNumber(documentNumber);

        if(chainValidator.validCnpjChain() && validCNPJSecurityDigits()) return true;
        else return false;
    }


    private boolean validCPFSecurityDigits() {
        try{
            if(securityCalculator.CpfDigitCalculator(10) && securityCalculator.CpfDigitCalculator(11)) return true;
            else return false;
        }catch (InputMismatchException exception){
            return false;
        }
    }

    private boolean validCNPJSecurityDigits(){
        try{
            if(securityCalculator.CnpjDigitCalculator(13) && securityCalculator.CnpjDigitCalculator(14)) return true;
            else return false;
        }catch (InputMismatchException exception){
            return false;
        }
    }
}
