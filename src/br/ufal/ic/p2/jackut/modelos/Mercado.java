package br.ufal.ic.p2.jackut.modelos;

public class Mercado extends Empresa {
    private String abre;
    private String fecha;
    private String tipoMercado;

    public Mercado(int idDono,String tipoEmpresa,int id,String name,String address,String abre,String fecha,String tipoMercado ){
        super(idDono,tipoEmpresa,id,name,address);
        this.abre = abre;
        this.fecha = fecha;
        this.tipoMercado = tipoMercado;
    }
    public String getAbre(){
        return this.abre;
    }
    public String getFecha(){
        return this.fecha;
    }
    public String getTipoMercado(){
        return this.tipoMercado;
    }
    public void setAbre(String valor){
        this.abre = valor;
    }
    public void setFecha(String valor){
        this.fecha = valor;
    }
}
