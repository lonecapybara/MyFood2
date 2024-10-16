package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Sistema.Sistema;

import br.ufal.ic.p2.jackut.exceptions.*;
import easyaccept.EasyAccept;

public class Facade {
    Sistema sistema = new Sistema();
// Cria usuário do tipo cliente.
    public void criarUsuario(String nome, String email, String senha, String endereco) throws NameValidationException, EmailValidationException, SenhaValidationException, AdressValidationException, EmailFormatValidationException, EmailExistsValidation {
        sistema.criarUsuario(nome, email, senha, endereco);
    }
// Cria usuário do tipo dono.
    public void criarUsuario(String name, String email, String senha, String endereco, String cpf) throws NameValidationException, EmailValidationException, SenhaValidationException, AdressValidationException, EmailFormatValidationException, CpfValidationException, EmailExistsValidation {
        sistema.criarUsuario(name, email, senha, endereco,cpf);
    }
// Cria usuário do tipo entregador.
    public void criarUsuario(String name, String email, String senha, String address, String veiculo,String placa) throws EmailExistsValidation, NameValidationException, EmailValidationException, SenhaValidationException, AdressValidationException, EmailFormatValidationException, PlacaValidation, VeiculoValidation {
        sistema.criarUsuario(name, email, senha, address,veiculo,placa);
    }
    public void zerarSistema(){
        sistema.zerarSistema();
    }
    public String getAtributoUsuario(int contadorId, String atributo) throws UserRegistrationValidation {
        return sistema.getAtributoUsuario(contadorId,atributo);
    }
    public int login(String email, String senha) throws LoginValidationException, EmailSenhaValidationException {
         return sistema.login(email,senha);
    }
    public void cadastrarEntregador(int empresa, int entregador) throws EntregadorValidation {
        sistema.cadastrarEntregador(empresa,entregador);
    }
    //Cria empresa do tipo Restaurante
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String address, String tipoCozinha) throws UserNotDonoException, EmpresaNomeAddressValidationException, EmpresaNomeValidationException, NameValidationException {
        return sistema.criarEmpresa (tipoEmpresa, dono,nome,address,tipoCozinha);
    }
// Cria empresa do tipo Farmácia
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String address, Boolean aberto24Horas, int numeroFuncionarios ) throws UserNotDonoException, EmpresaNomeAddressValidationException, EmpresaNomeValidationException, NameValidationException, TipoEmpresaValidation {
        return sistema.criarEmpresa(tipoEmpresa,dono,nome,address,aberto24Horas,numeroFuncionarios);
    }

    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String address,String abre,String fecha,String tipoMercado) throws UserNotDonoException, EmpresaNomeAddressValidationException, EmpresaNomeValidationException, TimeFormatInvalid, InvalidTime, TipoEmpresaInvald, NameValidation, AdressEmpresaValidation, invalidHour, InvalidTipoMercadoValidation, NameValidationException {
        return sistema.criarEmpresa(tipoEmpresa,dono,nome,address,abre,fecha,tipoMercado);
    }

    public String getEmpresasDoUsuario(int idDono) throws UserRegistrationValidation, UserNotDonoException {
        return sistema.getEmpresasDoUsuario(idDono);
    }
    public String getEntregadores(int empresa){
        return sistema.getEntregadores(empresa);
    }
    public String getEmpresas(int entregador) throws EntregadorValidation {
        return sistema.getEmpresas(entregador);
    }
    public int getIdEmpresa (int idDono, String nome, int indice) throws UserRegistrationValidation, NameValidationException, IndiceValidationException, IndiceLenghtValidationException, EmpresaNameExistsValidationException {
        return sistema.getIdEmpresa(idDono,nome,indice);
    }
    public String getAtributoEmpresa (int empresa, String atributo) throws AtributoValidationException, EmpresaRegistrationValidation {
        return sistema.getAtributoEmpresa(empresa,atributo);

    }
    public int criarProduto(int empresa, String nome, float valor, String categoria) throws NameValidationException, CategoriaValidationException, ValorValidationException, ProdutoNomeValidationException {
        return sistema.criarProduto(empresa,nome,valor,categoria);

    }
    public void editarProduto(int produto, String nome, float valor, String categoria) throws NameValidationException, CategoriaValidationException, ValorValidationException, ProdutoRegistrationValidation {
        sistema.editarProduto(produto,nome,valor,categoria);
    }
    public String getProduto(String  nome, int empresa, String atributo) throws ProductAtributoValidationException, ProductNotFoundException {
        return sistema.getProduto(nome,empresa,atributo);
    }
    public String listarProdutos(int empresa) throws EmpresaNotFoundException {
        return sistema.listarProdutos(empresa);
    }
    public int criarPedido(int cliente, int empresa) throws DonoPedidoRegistrationException, PedidoEstadoValidationException, PedidoFechadoException {
        return sistema.criarPedido(cliente,empresa);
    }
    public void adicionarProduto(int numero, int produto) throws PedidoDoesNotExistsExceptdion, ProdutoEmpresaValidationException, PedidoFechadoException {
        sistema.adicionarProduto(numero,produto);
    }
    public String getPedidos(int  numero, String atributo) throws Exception {
        return sistema.getPedidos(numero,atributo);
    }
    public void fecharPedido(int numero) throws PedidoNotFoundException {
        sistema.fecharPedido(numero);
    }
    public void removerProduto(int  pedido, String produto) throws ProdutoValidationException, PedidoRemoverValidationException, ProductNotFoundException {
        sistema.removerProduto(pedido,produto);

    }
    public int getNumeroPedido(int cliente, int empresa, int indice){
        return sistema.getNumeroPedido(cliente,empresa,indice);
    }
    public void alterarFuncionamento(int mercado, String abre, String fecha) throws TimeFormatInvalid, invalidHour, InvalidTime, InvalidMercado {
        sistema.alterarFuncionamento(mercado, abre, fecha);
    }
    public void liberarPedido(int numero) throws PedidoJaLiberado, PedidoPreparandoValidation {
        sistema.liberarPedido(numero);
    }
    public int obterPedido(int entregador) throws EntregadorValidation, EntregadorEmpresaValidation, PedidoNaoExiste {
        return sistema.obterPedido(entregador);
    }
    public int criarEntrega(int pedido, int entregador, String destino) throws EntregadorNotValid, PedidoNaoEstaPronto, EntregadorAindaEmEntrega {
        return sistema.criarEntrega(pedido,entregador,destino);
    }
    public String getEntrega(int id,String atributo) throws AtributoValidationException, AtributoExistsValidationException {
        return sistema.getEntrega(id,atributo);
    }
    public int getIdEntrega(int pedido) throws NaoExisteEntregaComEsseId {
        return sistema.getIdEntrega(pedido);
    }
    public void entregar(int entrega) throws EntregaNotFound {
        sistema.entregar(entrega);
    }

    public void encerrarSistema(){
        //sistema.encerrarSistema();
    }

}
