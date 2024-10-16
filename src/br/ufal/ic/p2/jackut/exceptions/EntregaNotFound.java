package br.ufal.ic.p2.jackut.exceptions;

public class EntregaNotFound extends Exception {
    public EntregaNotFound() {
        super("Nao existe nada para ser entregue com esse id");
    }
}
