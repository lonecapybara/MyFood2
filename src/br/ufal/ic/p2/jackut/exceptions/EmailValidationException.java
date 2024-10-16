package br.ufal.ic.p2.jackut.exceptions;

public class EmailValidationException extends Exception {
    public EmailValidationException(){
        super("Email invalido");
    }
}
