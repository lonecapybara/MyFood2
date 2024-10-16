package br.ufal.ic.p2.jackut.exceptions;

public class PedidoNotFoundException extends Exception {
    public PedidoNotFoundException() {
        super("Pedido nao encontrado");
    }
}
