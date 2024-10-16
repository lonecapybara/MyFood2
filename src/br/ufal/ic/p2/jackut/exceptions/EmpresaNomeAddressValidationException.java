package br.ufal.ic.p2.jackut.exceptions;

public class EmpresaNomeAddressValidationException extends Exception{
    public EmpresaNomeAddressValidationException(){
        super("Proibido cadastrar duas empresas com o mesmo nome e local");

    }
}
