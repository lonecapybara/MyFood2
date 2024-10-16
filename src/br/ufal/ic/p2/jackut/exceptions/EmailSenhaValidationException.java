package br.ufal.ic.p2.jackut.exceptions;

public class EmailSenhaValidationException extends Exception{
    public EmailSenhaValidationException(){
        super("Login ou senha invalidos");
    }
}
