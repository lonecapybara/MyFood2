package br.ufal.ic.p2.jackut.exceptions;

public class EntregadorEmpresaValidation extends Exception {
    public EntregadorEmpresaValidation() {
        super("Entregador nao estar em nenhuma empresa.");
    }
}
