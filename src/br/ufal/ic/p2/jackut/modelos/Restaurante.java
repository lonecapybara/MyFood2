package br.ufal.ic.p2.jackut.modelos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Restaurante extends Empresa  {
    private String tipoCozinha;
    public Restaurante(int idDono,String tipoEmpresa,int id,String name,String address,String tipoCozinha){
        super(idDono,tipoEmpresa,id,name,address);
        this.tipoCozinha = tipoCozinha;
    }
    public String getTipoCozinha() {
        return this.tipoCozinha;
    }
}

