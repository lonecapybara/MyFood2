package br.ufal.ic.p2.jackut.modelos;

public class Farmacia extends Empresa{
    private Boolean aberto24Horas;
    private int numeroFuncionarios;


    public Farmacia(int idDono, String tipoEmpresa, int id, String name, String address, Boolean aberto24Horas, int numeroFuncionarios) {
        super(idDono, tipoEmpresa, id, name, address);
        this.aberto24Horas = aberto24Horas;
        this.numeroFuncionarios = numeroFuncionarios;
    }

    public String getAberto24Horas() {
        return String.valueOf(aberto24Horas);
    }

    public String getNumeroFuncionarios() {
        return String.valueOf(numeroFuncionarios);
    }
}
