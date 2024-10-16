package br.ufal.ic.p2.jackut.exceptions;

public class NaoExisteEntregaComEsseId extends Exception {
    public NaoExisteEntregaComEsseId() {
        super("Nao existe entrega com esse id");
    }
}
