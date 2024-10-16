package br.ufal.ic.p2.jackut.exceptions;

public class EmpresaNomeValidationException extends Exception{
    public EmpresaNomeValidationException(){
        super("Empresa com esse nome ja existe");
    }
}
