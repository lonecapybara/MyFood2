package br.ufal.ic.p2.jackut.exceptions;

public class PedidoNaoEstaPronto extends Exception{
    public PedidoNaoEstaPronto() {
        super("Pedido nao esta pronto para entrega");
    }
}
