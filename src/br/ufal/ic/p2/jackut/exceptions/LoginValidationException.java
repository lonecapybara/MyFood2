package br.ufal.ic.p2.jackut.exceptions;

public class LoginValidationException extends Exception{
    public LoginValidationException(){
        super("Usuario nao cadastrado.");
    }
}
