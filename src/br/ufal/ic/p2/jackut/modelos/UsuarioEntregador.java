package br.ufal.ic.p2.jackut.modelos;

import java.util.ArrayList;

public class UsuarioEntregador extends Usuario{
    private String placa;
    private String veiculo;
    private ArrayList<Empresa> empresasList;
    public UsuarioEntregador(int id, String name, String email, String senha, String address, String veiculo, String placa) {
        super(id, name, email, senha, address);
        this.veiculo = veiculo;
        this.placa = placa;
        this.empresasList = new ArrayList<>();
    }

    public String getPlaca() {
        return placa;
    }
    public String getVeiculo() {
        return veiculo;
    }
// Empresas que o entregador está ligado.
    public void addEmpresa(Empresa empresa){
        empresasList.add(empresa);
    }

    public ArrayList<Empresa> getEmpresasList() {
        return empresasList;
    }
    public String getEmpresas(){
        StringBuilder emailsFormatados = new StringBuilder("{[");
        for (int i = 0; i < empresasList.size(); i++) {
            emailsFormatados.append("[").append(empresasList.get(i).getName()).append(", ").append(empresasList.get(i).getAddress()).append("]");
            if (i < empresasList.size() - 1) {
                emailsFormatados.append(", ");
            }
        }
        emailsFormatados.append("]}");
        return emailsFormatados.toString();
    }
}
