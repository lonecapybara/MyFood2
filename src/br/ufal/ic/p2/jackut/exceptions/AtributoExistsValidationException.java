package br.ufal.ic.p2.jackut.exceptions;

public class AtributoExistsValidationException extends Exception {
    public AtributoExistsValidationException() {
        super("Atributo nao existe");
    }
}
