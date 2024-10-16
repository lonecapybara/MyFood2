package br.ufal.ic.p2.jackut.exceptions;

public class UserNotDonoException extends Exception{
    public UserNotDonoException(){
        super("Usuario nao pode criar uma empresa");
    }
}
