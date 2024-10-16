package br.ufal.ic.p2.jackut.exceptions;

public class ProductAtributoValidationException extends Exception {
    public ProductAtributoValidationException() {
        super("Atributo nao existe");
    }
}
