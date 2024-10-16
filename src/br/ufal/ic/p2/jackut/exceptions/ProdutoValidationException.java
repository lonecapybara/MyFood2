package br.ufal.ic.p2.jackut.exceptions;

public class ProdutoValidationException extends Exception {
    public ProdutoValidationException() {
        super("Produto invalido");
    }
}
