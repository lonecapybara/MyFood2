package br.ufal.ic.p2.jackut.modelos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Produto {
    private int id;
    private String nome;
    private float valor;
    private String categoria;
    private int idEmpresa;

    public Produto(int id, int idEmpresa, String nome, float valor, String categoria) {
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
    }
    public int getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}


