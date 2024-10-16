package br.ufal.ic.p2.jackut.exceptions;

public class DonoPedidoRegistrationException extends Exception {
    public DonoPedidoRegistrationException() {
        super("Dono de empresa nao pode fazer um pedido");
    }
}
