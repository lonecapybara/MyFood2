package br.ufal.ic.p2.jackut.modelos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Usuario {
    private int id;
    private String name;
    private String email;
    private String senha;
    private String address;

    Usuario(int id,String name, String email, String senha, String address){
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.address = address;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getEmail() {
        return this.email;
    }
    public String getSenha() {
        return this.senha;
    }
    public String getAddress() {

        return this.address;
    }
}
