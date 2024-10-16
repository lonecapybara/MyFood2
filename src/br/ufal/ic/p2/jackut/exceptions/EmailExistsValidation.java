package br.ufal.ic.p2.jackut.exceptions;

public class EmailExistsValidation extends Exception{
    public EmailExistsValidation(){
        super("Conta com esse email ja existe");
    }
}
