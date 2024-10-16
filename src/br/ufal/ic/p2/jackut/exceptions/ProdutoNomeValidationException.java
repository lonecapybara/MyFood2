package br.ufal.ic.p2.jackut.exceptions;

public class ProdutoNomeValidationException extends Exception {
    public ProdutoNomeValidationException() {
        super("Ja existe um produto com esse nome para essa empresa");
    }
}
