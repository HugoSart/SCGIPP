<<<<<<< HEAD
package scgipp.service.validators.DocumentValidator;

import java.util.InputMismatchException;

class SecurityCalculator {

    private String documentNumber;

    SecurityCalculator(){}

    boolean CpfDigitCalculator(int securityDigitPosition) throws InputMismatchException {
        int num, sum, rest, weight;
        sum = 0;
        weight = securityDigitPosition;

        for(int i = 0; i < securityDigitPosition-1; i++){
            num = (int)(documentNumber.charAt(i) - 48);
            sum = sum + (num * weight);
            weight--;
        }

        rest = 11 - (sum % 11);

        if((rest == 10) || (rest == 11)) return documentNumber.charAt(securityDigitPosition-1) == '0';
        else return documentNumber.charAt(securityDigitPosition-1) == (char)(rest + 48);
    }

    boolean CnpjDigitCalculator(int securityDigitPosition) throws InputMismatchException {
        int num, sum, rest, weight;
        sum = 0;
        weight = 2;

        for (int i = securityDigitPosition-2; i >= 0; i--) {
            num = (int)(documentNumber.charAt(i) - 48);
            sum = sum + (num * weight);
            weight++;

            if(weight == 10) weight = 2;
        }

        rest = sum % 11;
        if(rest == 0 || rest == 1) return documentNumber.charAt(securityDigitPosition-1) == '0';
        else return documentNumber.charAt(securityDigitPosition-1) == (char)((11 - rest) + 48);
    }

    void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
=======
package scgipp.service.validators.DocumentValidator;

import java.util.InputMismatchException;

class SecurityCalculator {

    private String documentNumber;

    SecurityCalculator(){}

    boolean CpfDigitCalculator(int securityDigitPosition) throws InputMismatchException {
        int num, sum, rest, weight;
        sum = 0;
        weight = securityDigitPosition;

        for(int i = 0; i < securityDigitPosition-1; i++){
            num = (int)(documentNumber.charAt(i) - 48);
            sum = sum + (num * weight);
            weight--;
        }

        rest = 11 - (sum % 11);

        if((rest == 10) || (rest == 11)) return documentNumber.charAt(securityDigitPosition-1) == '0';
        else return documentNumber.charAt(securityDigitPosition-1) == (char)(rest + 48);
    }

    boolean CnpjDigitCalculator(int securityDigitPosition) throws InputMismatchException {
        int num, sum, rest, weight;
        sum = 0;
        weight = 2;

        for (int i = securityDigitPosition-2; i >= 0; i--) {
            num = (int)(documentNumber.charAt(i) - 48);
            sum = sum + (num * weight);
            weight++;

            if(weight == 10) weight = 2;
        }

        rest = sum % 11;
        if(rest == 0 || rest == 1) return documentNumber.charAt(securityDigitPosition-1) == '0';
        else return documentNumber.charAt(securityDigitPosition-1) == (char)((11 - rest) + 48);
    }

    void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
>>>>>>> [C]ObservableCustomer
