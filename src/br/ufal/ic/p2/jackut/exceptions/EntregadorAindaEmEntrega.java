package br.ufal.ic.p2.jackut.exceptions;

public class EntregadorAindaEmEntrega extends Exception {
    public EntregadorAindaEmEntrega() {
        super("Entregador ainda em entrega");
    }
}
