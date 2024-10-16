package br.ufal.ic.p2.jackut.exceptions;

public class PedidoDoesNotExistsExceptdion extends Exception {
    public PedidoDoesNotExistsExceptdion() {
        super("Nao existe pedido em aberto");
    }
}
