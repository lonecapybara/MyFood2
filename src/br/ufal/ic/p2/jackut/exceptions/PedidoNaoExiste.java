package br.ufal.ic.p2.jackut.exceptions;

public class PedidoNaoExiste extends Exception {
    public PedidoNaoExiste() {
        super("Nao existe pedido para entrega");
    }
}
