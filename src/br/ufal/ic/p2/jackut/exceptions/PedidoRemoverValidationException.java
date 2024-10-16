package br.ufal.ic.p2.jackut.exceptions;

public class PedidoRemoverValidationException extends Exception {
    public PedidoRemoverValidationException(){
        super("Nao e possivel remover produtos de um pedido fechado");
    }
}
