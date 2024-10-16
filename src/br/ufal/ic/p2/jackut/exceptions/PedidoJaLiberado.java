package br.ufal.ic.p2.jackut.exceptions;

public class PedidoJaLiberado extends Exception{
    public PedidoJaLiberado() {
        super("Pedido ja liberado");
    }
}
