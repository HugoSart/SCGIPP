package scgipp.service.validators.DocumentValidator;

import java.util.InputMismatchException;

public class DocumentValidator {

    private static SecurityCalculator securityCalculator = new SecurityCalculator();
    private static ChainValidator chainValidator = new ChainValidator();

    public static boolean isValidCPF(String CPF){
        securityCalculator.setDocumentNumber(CPF);
        chainValidator.setDocumentNumber(CPF);

        if(chainValidator.validCpfChain() && validCPFSecurityDigits()) return true;
        else return false;
    }

    public static boolean isValidCPNJ(String CNPJ){
        securityCalculator.setDocumentNumber(CNPJ);
        chainValidator.setDocumentNumber(CNPJ);

        if(chainValidator.validCnpjChain() && validCNPJSecurityDigits()) return true;
        else return false;
    }

    private static boolean validCPFSecurityDigits() {
        try{
            if(securityCalculator.CpfDigitCalculator(10) && securityCalculator.CpfDigitCalculator(11)) return true;
            else return false;
        }catch (InputMismatchException exception){
            return false;
        }
    }

    private static boolean validCNPJSecurityDigits(){
        try{
            if(securityCalculator.CnpjDigitCalculator(13) && securityCalculator.CnpjDigitCalculator(14)) return true;
            else return false;
        }catch (InputMismatchException exception){
            return false;
        }
    }
}
