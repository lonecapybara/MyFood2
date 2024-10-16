package br.ufal.ic.p2.jackut.exceptions;

public class ProdutoEmpresaValidationException extends Exception {
    public ProdutoEmpresaValidationException() {
        super("O produto nao pertence a essa empresa");
    }
}
