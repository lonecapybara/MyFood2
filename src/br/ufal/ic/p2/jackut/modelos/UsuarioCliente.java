package br.ufal.ic.p2.jackut.modelos;

public class UsuarioCliente extends Usuario{
    String address;
    public UsuarioCliente(int id,String name,String email, String senha, String address){
        super(id,name,email,senha,address);
    }
}
