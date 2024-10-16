package br.ufal.ic.p2.jackut.exceptions;

public class PedidoEstadoValidationException extends Exception {
    public PedidoEstadoValidationException() {
        super("Nao e permitido ter dois pedidos em aberto para a mesma empresa");
    }
}
