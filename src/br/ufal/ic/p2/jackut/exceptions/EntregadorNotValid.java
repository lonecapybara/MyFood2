package br.ufal.ic.p2.jackut.exceptions;

public class EntregadorNotValid extends Exception {
    public EntregadorNotValid() {
        super("Nao e um entregador valido");
    }
}
