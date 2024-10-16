package br.ufal.ic.p2.jackut.exceptions;

public class SenhaValidationException extends Exception {
    public SenhaValidationException() {
        super("Senha invalido");
    }
}
