package br.ufal.ic.p2.jackut.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(){
        super("Produto nao encontrado");
    }
}
