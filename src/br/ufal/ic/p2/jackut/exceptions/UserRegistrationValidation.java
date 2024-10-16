package br.ufal.ic.p2.jackut.exceptions;

public class UserRegistrationValidation extends Exception {
    public UserRegistrationValidation(){
        super("Usuario nao cadastrado.");
    }
}
