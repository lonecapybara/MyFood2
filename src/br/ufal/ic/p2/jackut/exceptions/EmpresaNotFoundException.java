package br.ufal.ic.p2.jackut.exceptions;

public class EmpresaNotFoundException extends Exception {
    public EmpresaNotFoundException() {
        super("Empresa nao encontrada");
    }
}
