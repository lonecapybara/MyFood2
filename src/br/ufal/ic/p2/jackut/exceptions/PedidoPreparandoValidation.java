package br.ufal.ic.p2.jackut.exceptions;

public class PedidoPreparandoValidation extends Exception {
    public PedidoPreparandoValidation() {
        super("Nao e possivel liberar um produto que nao esta sendo preparado");
    }
}
