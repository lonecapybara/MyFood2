package br.ufal.ic.p2.jackut.modelos;

public class Entrega {
    private int id;
    private String cliente;
    private String empresa;
    private int pedido;
    private int entregador;
    private String destino;
    private String produtos;


    public Entrega(int id, String cliente, String empresa,int pedido, int entregador, String destino, String produtos  ){
        this.id = id;
        this.cliente = cliente;
        this.empresa = empresa;
        this.pedido = pedido;
        this.entregador = entregador;
        this.destino = destino;
        this.produtos = produtos;
    }
    public int getEntregaId(){
        return this.id;
    }
    public String getCliente(){
        return this.cliente;
    }
    public String getEmpresa(){
        return this.empresa;
    }
    public int getPedidoId(){
        return this.pedido;
    }
    public int getEntregadorId(){
        return this.entregador;
    }
    public String getDestino() {
        return destino;
    }
    public String getProdutos() {
        return produtos;
    }
}
