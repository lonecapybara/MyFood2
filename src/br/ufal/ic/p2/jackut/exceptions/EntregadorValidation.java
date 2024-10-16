package br.ufal.ic.p2.jackut.exceptions;

public class EntregadorValidation extends Exception {
    public EntregadorValidation() {
        super("Usuario nao e um entregador");
    }
}
