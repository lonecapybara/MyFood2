package br.ufal.ic.p2.jackut.exceptions;

public class EmpresaNameExistsValidationException extends Exception {
    public EmpresaNameExistsValidationException(){
        super("Nao existe empresa com esse nome");
    }
}
