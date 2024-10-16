package br.ufal.ic.p2.jackut.exceptions;

public class CpfValidationException extends Exception {
    public CpfValidationException(){
        super("CPF invalido");
    }
}
