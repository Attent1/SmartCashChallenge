package br.com.fiap.SmartCash.Usuario.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public class CPFOrCNPJValidator implements ConstraintValidator<CPFOrCNPJ, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {        
        if (value == null || value.isEmpty()) {
            return true;
        }

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        if (cpfValidator.isValid(value, null)) {
            return true;
        }

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null); 
        return cnpjValidator.isValid(value, null);
    }
}
