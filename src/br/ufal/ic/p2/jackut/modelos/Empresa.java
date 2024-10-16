package br.ufal.ic.p2.jackut.modelos;

import java.util.ArrayList;

public abstract class Empresa{
    private int id;
    private String name;
    private String address;
    private String tipoEmpresa;
    private int idDono;
    private ArrayList<UsuarioEntregador> entregadoresList;
    public Empresa(int idDono,String tipoEmpresa,int id,String name,String address){
        this.id = id;
        this.name = name;
        this.address = address;
        this.tipoEmpresa = tipoEmpresa;
        this.idDono = idDono;
        this.entregadoresList = new ArrayList<>();
    }
    public int getIdDono(){
        return this.idDono;
    }
    public int getId(){
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getAddress() {
        return this.address;
    }
    public String getTipoEmpresa() {
        return this.tipoEmpresa;
    }
    public void addEntregador(UsuarioEntregador entregador){
        entregadoresList.add(entregador);
    }
    public String entregadores(){
        StringBuilder emailsFormatados = new StringBuilder("{[");
        for (int i = 0; i < entregadoresList.size(); i++) {
            emailsFormatados.append(entregadoresList.get(i).getEmail());
            if (i < entregadoresList.size() - 1) {
                emailsFormatados.append(", ");
            }
        }
        emailsFormatados.append("]}");
        return emailsFormatados.toString();
    }


}
