package br.ufal.ic.p2.jackut.exceptions;

public class InvalidMercado extends Exception {
    public InvalidMercado() {
        super("Nao e um mercado valido");
    }
}
