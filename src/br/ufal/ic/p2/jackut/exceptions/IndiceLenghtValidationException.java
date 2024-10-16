package br.ufal.ic.p2.jackut.exceptions;

public class IndiceLenghtValidationException extends Exception {
    public IndiceLenghtValidationException(){
        super("Indice maior que o esperado");
    }
}
