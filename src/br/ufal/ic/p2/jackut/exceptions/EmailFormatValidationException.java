package br.ufal.ic.p2.jackut.exceptions;

public class EmailFormatValidationException extends Exception{
    public EmailFormatValidationException(){
        super("Formato de email invalido.");
    }
}
