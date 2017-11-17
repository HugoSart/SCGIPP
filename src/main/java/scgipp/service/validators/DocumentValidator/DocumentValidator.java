package scgipp.service.validators.DocumentValidator;

import java.util.InputMismatchException;

public class DocumentValidator {

    private static SecurityCalculator securityCalculator = new SecurityCalculator();
    private static ChainValidator chainValidator = new ChainValidator();

    public static boolean isValidCPF(String CPF){
        String cpfCleaned = chainValidator.stringCleaner(CPF);
        securityCalculator.setDocumentNumber(cpfCleaned);
        chainValidator.setDocumentNumber(cpfCleaned);

        if(chainValidator.validCpfChain() && validCPFSecurityDigits()) return true;
        else return false;
    }

    public static boolean isValidCPNJ(String CNPJ){
        String cnpjCleaned = chainValidator.stringCleaner(CNPJ);
        securityCalculator.setDocumentNumber(cnpjCleaned);
        chainValidator.setDocumentNumber(cnpjCleaned);

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
