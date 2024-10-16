package br.ufal.ic.p2.jackut.modelos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedidos {
    private int numeroPedido;
    private String empresa;
    private String cliente;
    private String estado;
    private float valor = 0;
    private int clienteId;
    private int empresaId;
    private ArrayList<Produto> produtosList;

    public Pedidos(int numeroPedido,String cliente,String empresa,String estado,int clienteId,int empresaId){
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.empresa =empresa;
        this.estado = estado;
        this.clienteId = clienteId;
        this.empresaId = empresaId;
        this.produtosList = new ArrayList<>();
    }
    public int getNumeroPedido() {
        return numeroPedido;
    }
    public String getCliente(){
        return this.cliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getValor() {
        return valor;
    }
    public int getClienteId(){
        return clienteId;
    }
    public int getEmpresaId(){
        return empresaId;
    }
    public void adicionarProduto(Produto produto) {
        produtosList.add(produto);
        valor += produto.getValor();
    }
    public String getListaDeProdutos() {
        StringBuilder produtosFormatados = new StringBuilder("{[");
        for (int i = 0; i < produtosList.size(); i++) {
            produtosFormatados.append(produtosList.get(i).getNome());
            if (i < produtosList.size() - 1) {
                produtosFormatados.append(", ");
            }
        }
        produtosFormatados.append("]}");
        return produtosFormatados.toString();
    }
    public boolean removerProduto(String nomeProduto) {
        for (int i = 0; i < produtosList.size(); i++) {
            Produto produto = produtosList.get(i);
            if (produto.getNome().equals(nomeProduto)) {
                valor -= produto.getValor();
                produtosList.remove(i);
                return true;
            }
        }
        return false;
    }
}

