package br.ufal.ic.p2.jackut.Sistema;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.modelos.*;
import br.ufal.ic.p2.jackut.modelos.Restaurante;
import br.ufal.ic.p2.jackut.modelos.Empresa;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Sistema {
    int contadorProdutoId = 1;
    int contadorId = 1;
    int contadorIdEmpresa = 1;
    int contadorPedido = 1;
    int contadorEntrega = 1;
    String pedidoEstado = "aberto";
    Map<String, Usuario> usuariosPorEmail = new HashMap<>();
    Map<Integer, Usuario> usuariosPorId = new HashMap<>();
    Map<Integer, Usuario> donosPorId = new HashMap<>();
    Map<Integer, UsuarioEntregador> entregadoresPorId = new HashMap<>();

    Map<Integer, List<Empresa>> empresasPorDono = new HashMap<>();
    Map<Integer, Empresa> empresasPorId = new HashMap<>();
    Map<Integer, Restaurante> restaurantesPorId = new HashMap<>();
    Map<Integer, Mercado> mercadosPorId = new HashMap<>();
    Map<Integer, Farmacia> farmaciasPorId = new HashMap<>();
    Map<String, Empresa> empresasPorNome = new HashMap<>();


    Map<Integer, Produto> produtoPorId = new HashMap<>();


    Map<Integer, Pedidos> pedidosPorCliente = new HashMap<>();
    Map<Integer, Pedidos> pedidosPorNumero = new HashMap<>();

    Map<Integer, Entrega> entregasPorId = new HashMap<>();



    public void zerarSistema() {
        contadorId = 1;
        contadorIdEmpresa = 1;
        contadorProdutoId = 1;
        contadorPedido = 1;
        contadorEntrega = 1;
        usuariosPorEmail.clear();
        usuariosPorId.clear();
        donosPorId.clear();
        empresasPorDono.clear();
        empresasPorId.clear();
        produtoPorId.clear();
        pedidosPorCliente.clear();
        pedidosPorNumero.clear();
        restaurantesPorId.clear();
        mercadosPorId.clear();
        farmaciasPorId.clear();
        entregadoresPorId.clear();
        entregasPorId.clear();
        pedidoEstado = "aberto";
        empresasPorNome.clear();
    }
// CRIA UM USUÁRIO DO TIPO CLIENTE.
    public void criarUsuario(String name, String email, String senha, String address) throws NameValidationException, EmailValidationException, SenhaValidationException, AdressValidationException, EmailFormatValidationException, EmailExistsValidation {
        validarData(name, email, senha, address);
        UsuarioCliente cliente = new UsuarioCliente(contadorId, name, email, senha, address);
        if (!usuariosPorEmail.containsKey(email)) {
            usuariosPorEmail.put(email, cliente);
            usuariosPorId.put(contadorId, cliente);
            contadorId++;
        } else {
            throw new EmailExistsValidation();
        }
    }
// CRIA UM USUÁRIO DO TIPO DONO.
    public void criarUsuario(String name, String email, String senha, String address, String cpf) throws NameValidationException, EmailValidationException, SenhaValidationException, AdressValidationException, EmailFormatValidationException, CpfValidationException, EmailExistsValidation {

        validarData(name, email, senha, address);
        validarCpf(cpf);
        if (!usuariosPorEmail.containsKey(email)) {
            UsuarioDono dono = new UsuarioDono(contadorId, name, email, senha, address, cpf);
            usuariosPorEmail.put(email, dono);
            usuariosPorId.put(contadorId, dono);
            donosPorId.put(contadorId, dono);
            contadorId++;
        } else {
            throw new EmailExistsValidation();
        }
    }
// CRIA UM USUÁRIO DO TIPO ENTREGADOR.
    public void criarUsuario(String name, String email, String senha, String address, String veiculo,String placa) throws NameValidationException, EmailValidationException, SenhaValidationException, AdressValidationException, EmailFormatValidationException, EmailExistsValidation, VeiculoValidation, PlacaValidation {
        validarData(name, email, senha, address);
        if(veiculo == null || veiculo.isEmpty()){
            throw new VeiculoValidation();
        }
        if (placa == null || placa.isEmpty()){
            throw new PlacaValidation();
        }
        for (Map.Entry<Integer, UsuarioEntregador> entry : entregadoresPorId.entrySet()) {
            UsuarioEntregador entregador1 = entry.getValue();
            if (entregador1.getPlaca().equals(placa)) {
                throw new PlacaValidation();
            }
        }

        UsuarioEntregador entregador = new UsuarioEntregador(contadorId,name,email,senha,address,veiculo,placa);
        if (!usuariosPorEmail.containsKey(email)) {
            usuariosPorEmail.put(email, entregador);
            usuariosPorId.put(contadorId, entregador);
            entregadoresPorId.put(contadorId,entregador);
            contadorId++;
        }
        else{
            throw new EmailExistsValidation();
        }

    }

    public String getAtributoUsuario(int id, String atributo) throws UserRegistrationValidation {
        //System.out.println(id);
        if (usuariosPorId.containsKey(id)) {
            Usuario usuario = usuariosPorId.get(id);
            if (atributo.equals("cpf")) {
                return ((UsuarioDono) usuario).getCpf();
            }
            UsuarioEntregador entregador = entregadoresPorId.get(id);
            switch (atributo) {
                case "nome":
                    return usuario.getName();
                case "email":
                    return usuario.getEmail();
                case "senha":
                    return usuario.getSenha();
                case "endereco":
                    return usuario.getAddress();
                case "veiculo":
                    return entregador.getVeiculo();
                case "placa":
                    return entregador.getPlaca();
                default:
                    throw new UserRegistrationValidation();
            }
        } else {
            throw new UserRegistrationValidation();
        }
    }

    public int login(String email, String senha) throws LoginValidationException, EmailSenhaValidationException {
        Usuario usuario = usuariosPorEmail.get(email);
        if (email == null || email.isEmpty() || !email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$") || senha == null || senha.trim().isEmpty()) {
            throw new EmailSenhaValidationException();
        }
        if (usuario == null) {
            throw new LoginValidationException();
        } else if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
            return usuario.getId();
        } else {
            throw new EmailSenhaValidationException();
        }
    }
    public void cadastrarEntregador(int empresa, int entregador) throws EntregadorValidation {
        if(entregadoresPorId.get(entregador) == null){
            throw new EntregadorValidation();
        }
        else{
            Empresa empresa1 = empresasPorId.get(empresa);
            UsuarioEntregador userEntregador = entregadoresPorId.get(entregador);
            empresa1.addEntregador(userEntregador);
            userEntregador.addEmpresa(empresa1);
        }
    }
    public String getEntregadores(int empresa){
        Empresa empresa1 = empresasPorId.get(empresa);
        return empresa1.entregadores();
    }
    public String getEmpresas(int entregador) throws EntregadorValidation {
        if(entregadoresPorId.get(entregador) == null){
            throw new EntregadorValidation();
        }
        UsuarioEntregador usuarioEntregador = entregadoresPorId.get(entregador);
        return usuarioEntregador.getEmpresas();
    }
// Cria empresa do tipo Restaurante
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String address, String tipoCozinha) throws UserNotDonoException, EmpresaNomeAddressValidationException, EmpresaNomeValidationException, NameValidationException {
        validarEmpresa(dono,nome,address);
        Restaurante restaurante = new Restaurante(dono,tipoEmpresa, contadorIdEmpresa, nome, address, tipoCozinha);
        empresasPorId.put(contadorIdEmpresa, restaurante);
        if(empresasPorDono.get(dono) == null){
            empresasPorDono.put(dono,new ArrayList<>());
        }
        else{
            empresasPorDono.get(dono).add(restaurante);
        }
        empresasPorNome.put(nome, restaurante);
        restaurantesPorId.put(contadorIdEmpresa,restaurante);

        contadorIdEmpresa++;
        return contadorIdEmpresa - 1;
    }
// Cria empresa do tipo Mercado
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String address,String abre,String fecha,String tipoMercado) throws UserNotDonoException, EmpresaNomeAddressValidationException, EmpresaNomeValidationException, TimeFormatInvalid, InvalidTime, TipoEmpresaInvald, NameValidation, AdressEmpresaValidation, invalidHour, InvalidTipoMercadoValidation, NameValidationException {
        validarMercado(tipoEmpresa,nome, address,abre,fecha,tipoMercado);
        validarEmpresa(dono,nome,address);
        Mercado mercado = new Mercado(dono,tipoEmpresa,contadorIdEmpresa,nome,address,abre,fecha,tipoMercado);
        empresasPorId.put(contadorIdEmpresa, mercado);
        if(empresasPorDono.get(dono) == null){
            empresasPorDono.put(dono,new ArrayList<>());
        }
        else{
            empresasPorDono.get(dono).add(mercado);
        }
        empresasPorNome.put(nome, mercado);
        mercadosPorId.put(contadorIdEmpresa,mercado);
        contadorIdEmpresa ++;
        return contadorIdEmpresa - 1;
    }
// Cria empresa do tipo Farmácia
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String address, Boolean aberto24Horas, int numeroFuncionarios ) throws UserNotDonoException, EmpresaNomeAddressValidationException, EmpresaNomeValidationException, NameValidationException, TipoEmpresaValidation {
        if(nome == null){
            throw new NameValidationException();
        }
        if (tipoEmpresa == null){
            throw new  TipoEmpresaValidation();
        }
        if(address == null){
            throw new EmpresaAdressValidation();
        }
        validarEmpresa(dono,nome,address);
        Farmacia farmacia = new Farmacia(dono,tipoEmpresa,contadorIdEmpresa,nome,address,aberto24Horas,numeroFuncionarios);
        empresasPorId.put(contadorIdEmpresa, farmacia);
        if(empresasPorDono.get(dono) == null){
            empresasPorDono.put(dono,new ArrayList<>());
        }
        else{
            empresasPorDono.get(dono).add(farmacia);
        }
        empresasPorNome.put(nome, farmacia);
        farmaciasPorId.put(contadorIdEmpresa,farmacia);

        contadorIdEmpresa++;
        return contadorIdEmpresa -1;
    }
    public void alterarFuncionamento(int mercado, String abre, String fecha) throws TimeFormatInvalid, invalidHour, InvalidTime, InvalidMercado {
        if( mercadosPorId.get(mercado) == null){
            throw new InvalidMercado();
        }
        validarHora(abre,fecha);
        Mercado mercados = mercadosPorId.get(mercado);
        mercados.setAbre(abre);
        mercados.setFecha(fecha);

    }
    public String getEmpresasDoUsuario(int idDono) throws UserRegistrationValidation, UserNotDonoException {
        if (!donosPorId.containsKey(idDono)) {
            throw new UserNotDonoException();
        }
        StringBuilder resultado = new StringBuilder("{[");
        List<String> empresasDoDono = new ArrayList<>();

        for (Map.Entry<Integer, Empresa> entry : empresasPorId.entrySet()) {
            Empresa empresa = entry.getValue();
            if (empresa.getIdDono() == idDono) {
                empresasDoDono.add("[" + empresa.getName() + ", " + empresa.getAddress() + "]");
            }
        }
        resultado.append(String.join(", ", empresasDoDono));
        resultado.append("]}");

        return resultado.toString();
    }

    public String getAtributoEmpresa (int empresa, String atributo) throws AtributoValidationException, EmpresaRegistrationValidation {
        if(!empresasPorId.containsKey(empresa)){
            throw new EmpresaRegistrationValidation();
        }
        if(atributo == null || atributo.isEmpty()){
            throw new AtributoValidationException();
        }
        Empresa empresas = empresasPorId.get(empresa);
        if(atributo.equals("dono")){
            for (Map.Entry<Integer, Usuario> entry : donosPorId.entrySet()) {
                Usuario usuario = entry.getValue();
                if(empresas.getIdDono() == usuario.getId()){
                    return usuario.getName();
                }
            }
        }
        Restaurante restaurante = restaurantesPorId.get(empresa);
        Mercado mercado = mercadosPorId.get(empresa);
        Farmacia farmacia = farmaciasPorId.get(empresa);
        switch (atributo){
            case "nome":
                return empresas.getName();
            case "endereco":
                return empresas.getAddress();
            case "tipoCozinha":
                return restaurante.getTipoCozinha();
            case "abre":
                return mercado.getAbre();
            case "fecha":
                return mercado.getFecha();
            case "tipoMercado":
                return mercado.getTipoMercado();
            case "aberto24Horas":
                return farmacia.getAberto24Horas();
            case "numeroFuncionarios":
                return farmacia.getNumeroFuncionarios();
            default:
                throw new AtributoValidationException();
        }
    }

    public int getIdEmpresa (int idDono, String nome, int indice) throws UserRegistrationValidation, NameValidationException, IndiceValidationException, IndiceLenghtValidationException, EmpresaNameExistsValidationException {
        if(nome == null || nome.isEmpty()){
            throw new NameValidationException();
        }
        if(indice < 0 ){
            throw new IndiceValidationException();
        }
        if(!empresasPorNome.containsKey(nome)){
            throw new EmpresaNameExistsValidationException();
        }
        int index = 0;
        int index2 = 0;
        int retorno = 0;

        for (Map.Entry<Integer, Empresa> entry : empresasPorId.entrySet()) {
            Empresa empresa = entry.getValue();
            if(empresa.getIdDono() == idDono && empresa.getName().equals(nome)){
                index++;
            }
        }

        if(indice >= index){
            throw new IndiceLenghtValidationException();
        }

        for (Map.Entry<Integer, Empresa> entry : empresasPorId.entrySet()) {
            Empresa empresa = entry.getValue();
            if(empresa.getIdDono() == idDono && empresa.getName().equals(nome)){
                if(index2 == indice){
                    retorno = empresa.getId();
                    break;
                }
                index2++;
            }
        }
        return retorno;
    }

    public int criarProduto(int empresa, String nome, float valor, String categoria) throws NameValidationException, CategoriaValidationException, ValorValidationException, ProdutoNomeValidationException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NameValidationException();
        }
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new CategoriaValidationException();
        }
        if(valor <= 0 ){
            throw new ValorValidationException();
        }
        for (Map.Entry<Integer, Produto> entry : produtoPorId.entrySet()) {
            Produto produto = entry.getValue();
            if(produto.getNome().equals(nome) && produto.getIdEmpresa() == empresa){
                throw new ProdutoNomeValidationException();
            }
        }
        Produto produto = new Produto(contadorProdutoId,empresa,nome,valor,categoria);
        produtoPorId.put(contadorProdutoId,produto);
        contadorProdutoId++;

        return contadorProdutoId - 1;
    }

    public void editarProduto(int produto, String nome, float valor, String categoria) throws NameValidationException, CategoriaValidationException, ValorValidationException, ProdutoRegistrationValidation {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NameValidationException();
        }
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new CategoriaValidationException();
        }
        if(valor <= 0 ){
            throw new ValorValidationException();
        }
        if(!produtoPorId.containsKey(produto)){
            throw new ProdutoRegistrationValidation();
        }
        Produto produtoExistente = produtoPorId.get(produto);

        produtoExistente.setNome(nome);
        produtoExistente.setValor(valor);
        produtoExistente.setCategoria(categoria);

        produtoPorId.put(produto, produtoExistente);
    }

    public String getProduto(String  nome, int empresa, String atributo) throws ProductAtributoValidationException, ProductNotFoundException {
        if(atributo == null || atributo.isEmpty()){
            throw new ProductAtributoValidationException();
        }
        if (!atributo.equals("categoria") && !atributo.equals("valor") && !atributo.equals("empresa")) {
            throw new ProductAtributoValidationException();
        }
        String resultado = "";

        for (Map.Entry<Integer, Produto> entry : produtoPorId.entrySet()) {
            Produto produto = entry.getValue();
            if(atributo.equals("categoria")){
                if(produto.getNome().equals(nome) && produto.getIdEmpresa() == empresa){
                    return produto.getCategoria();
                }
            }
            if(atributo.equals("valor")){
                if(produto.getNome().equals(nome) && produto.getIdEmpresa() == empresa){
                    return  String.format(Locale.US, "%.2f", produto.getValor());
                }
            }
            if(atributo.equals("empresa")){
                if(produto.getNome().equals(nome) && produto.getIdEmpresa() == empresa){
                    for (Map.Entry<Integer, Empresa> entrada : empresasPorId.entrySet()) {
                        Empresa empresas = entrada.getValue();
                        if(empresas.getId() == produto.getIdEmpresa()){
                            return empresas.getName();
                        }
                    }
                }
            }
        }
        throw new ProductNotFoundException();
    }

    public String listarProdutos(int empresa) throws EmpresaNotFoundException {
        if(!empresasPorId.containsKey(empresa)){
            throw new EmpresaNotFoundException();
        }
        int index = 0;
        int i = 0;
        StringBuilder produtoslista = new StringBuilder();
        for (Map.Entry<Integer, Produto> entry : produtoPorId.entrySet()) {
            Produto produto = entry.getValue();
            if(produto.getIdEmpresa() == empresa){
                index++;
            }
        }
        produtoslista.append("{[");
        for (Map.Entry<Integer, Produto> entry : produtoPorId.entrySet()) {
            Produto produto = entry.getValue();
            if(produto.getIdEmpresa() == empresa){
                i++;
                produtoslista.append(produto.getNome());
            }
            if(i < index && index != 1){
                produtoslista.append(", ");
            }
        }
        produtoslista.append("]}");
        return produtoslista.toString();
    }
    public int criarPedido(int cliente, int empresa) throws DonoPedidoRegistrationException, PedidoEstadoValidationException, PedidoFechadoException {
        if(donosPorId.containsKey(cliente)){
            throw new DonoPedidoRegistrationException();

        }
        for (Map.Entry<Integer, Pedidos> entry : pedidosPorCliente.entrySet()) {
            Pedidos pedidos = entry.getValue();
            if (pedidos.getClienteId() == cliente && pedidos.getEmpresaId() == empresa && pedidos.getEstado().equals("aberto")) {
                throw new PedidoEstadoValidationException();
            }

        }

        String nomeEmpresa = "";
        String nomeCliente = "";
        for (Map.Entry<Integer, Empresa> entry : empresasPorId.entrySet()) {
            Empresa empresas = entry.getValue();
            if(empresas.getId() == empresa){
                nomeEmpresa = empresas.getName();
            }
        }
        for (Map.Entry<Integer, Usuario> entry : usuariosPorId.entrySet()) {
            Usuario usuario = entry.getValue();
            if(usuario.getId() == cliente){
                nomeCliente = usuario.getName();
            }
        }
        Pedidos pedidos = new Pedidos(contadorPedido,nomeCliente,nomeEmpresa,pedidoEstado,cliente,empresa);
        pedidosPorCliente.put(cliente,pedidos);
        pedidosPorNumero.put(contadorPedido,pedidos);
        contadorPedido++;
        return contadorPedido-1;
    }
    public void adicionarProduto(int numero, int produto) throws PedidoDoesNotExistsExceptdion, ProdutoEmpresaValidationException, PedidoFechadoException {
        if(!pedidosPorNumero.containsKey(numero)){
            throw new PedidoDoesNotExistsExceptdion();
        }
        Pedidos pedido = pedidosPorNumero.get(numero);
        Produto produtoBase = produtoPorId.get(produto);
        if(!pedido.getEstado().equals("aberto")){
            throw new PedidoFechadoException();
        }
        if (produtoBase.getIdEmpresa() != pedido.getEmpresaId()) {
            throw new ProdutoEmpresaValidationException();
        }
        pedido.adicionarProduto(produtoBase);
    }
    public String getPedidos(int  numero, String atributo) throws Exception {
        if(atributo == null || atributo.isEmpty()){
            throw new AtributoValidationException();
        }
        if(!atributo.equals("cliente") && !atributo.equals("empresa") && !atributo.equals("estado") && !atributo.equals("produtos") && !atributo.equals("valor")){
            throw new AtributoExistsValidationException();
        }

        Pedidos pedido = pedidosPorNumero.get(numero);

        Usuario usuario = usuariosPorId.get(pedido.getClienteId());
        Empresa empresa = empresasPorId.get(pedido.getEmpresaId());
        if(atributo.equals("cliente")){
            return usuario.getName();
        }
        if(atributo.equals("empresa")){
            return empresa.getName();
        }
        if(atributo.equals("estado")){
            return pedido.getEstado();
        }
        if(atributo.equals("valor")){
            return String.format(Locale.US, "%.2f", pedido.getValor());
        }
        if(atributo.equals("produtos")){
            return pedido.getListaDeProdutos();
        }
        return "";
    }
    public void fecharPedido(int numero) throws PedidoNotFoundException {
        if(!pedidosPorNumero.containsKey(numero)){
            throw new PedidoNotFoundException();
        }
        Pedidos pedido = pedidosPorNumero.get(numero);
        pedido.setEstado("preparando");
    }
    public void removerProduto(int pedido,String produto) throws ProdutoValidationException, PedidoRemoverValidationException, ProductNotFoundException {
        if(produto == null || produto.isEmpty()){
            throw new ProdutoValidationException();
        }
        Pedidos pedidoData = pedidosPorNumero.get(pedido);
        if(pedidoData.getEstado().equals("preparando")){
            throw new PedidoRemoverValidationException();
        }
        if(!pedidoData.removerProduto(produto)){
            throw new ProductNotFoundException();

        }
    }
    public int getNumeroPedido(int cliente, int empresa, int indice) {
        int index = 0;
        int pedidoId = 0;

        for (Map.Entry<Integer, Pedidos> entry : pedidosPorNumero.entrySet()) {
            Pedidos pedido = entry.getValue();

            if (pedido.getClienteId() == cliente && pedido.getEmpresaId() == empresa) {
                if (index == indice){
                    pedidoId  = pedido.getNumeroPedido();
                    break;
                }
                index++;
            }
        }
        return pedidoId;
    }
    public void liberarPedido(int numero) throws PedidoJaLiberado, PedidoPreparandoValidation {

        Pedidos pedido = pedidosPorNumero.get(numero);
        if(pedido.getEstado().equals("pronto")){
            throw new PedidoJaLiberado();
        }
        else if(!pedido.getEstado().equals("preparando")){
            throw new PedidoPreparandoValidation();
        }
        pedido.setEstado("pronto");
    }
    public int obterPedido(int entregador) throws EntregadorValidation, EntregadorEmpresaValidation, PedidoNaoExiste {

        if(entregadoresPorId.get(entregador) == null){
            throw new EntregadorValidation();
        }
        UsuarioEntregador userEntregador = entregadoresPorId.get(entregador);
        ArrayList<Empresa> empresasList = userEntregador.getEmpresasList();
        ArrayList<Pedidos> pedidosList = new ArrayList<>();
        if(empresasList.isEmpty()){
            throw new EntregadorEmpresaValidation();
        }
        int pedidosParaEntrega = 0;
        for (Map.Entry<Integer, Pedidos> entry : pedidosPorNumero.entrySet()) {
            Pedidos pedido = entry.getValue();
            if(pedido.getEstado().equals("pronto")){
                pedidosParaEntrega++;
            }
            for( Empresa empresa : empresasList){
                if(empresa.getId() == pedido.getEmpresaId()){
                    if(pedido.getEstado().equals("pronto")){
                        pedidosList.add(pedido);
                    }

                }
            }
        }
        ArrayList<Integer> farmaciasList = new ArrayList<>();
        ArrayList<Integer> pedidosidList = new ArrayList<>();


        for (Pedidos pedido : pedidosList) {
            if (farmaciasPorId.containsKey(pedido.getEmpresaId())) {
                farmaciasList.add(pedido.getNumeroPedido());
            }
            pedidosidList.add(pedido.getNumeroPedido());
        }
        if(pedidosParaEntrega == 0){
            throw new PedidoNaoExiste();
        }
        if(farmaciasList.isEmpty()){
            return  Collections.min(pedidosidList);
        }
        else{
            return Collections.min(farmaciasList);

        }
    }
    public int criarEntrega(int pedido, int entregador, String destino) throws EntregadorNotValid, PedidoNaoEstaPronto, EntregadorAindaEmEntrega {
        Pedidos pedido1 = pedidosPorNumero.get(pedido);
        if(pedido1.getEstado().equals("entregando")){
            throw new EntregadorAindaEmEntrega();
        }
        if(!pedido1.getEstado().equals("pronto")){
            throw new PedidoNaoEstaPronto();
        }
        if(entregadoresPorId.get(entregador) == null){
            throw new EntregadorNotValid();
        }
        if (destino != null) {
            Entrega entrega = new Entrega(contadorEntrega,pedido1.getCliente(),pedido1.getEmpresa(),pedido,entregador,destino,pedido1.getListaDeProdutos());
            entregasPorId.put(contadorEntrega,entrega);
            pedido1.setEstado("entregando");
            contadorEntrega++;
        }
        else{
            Usuario usuario = usuariosPorId.get(pedido1.getClienteId());
            Entrega entrega = new Entrega(contadorEntrega,pedido1.getCliente(),pedido1.getEmpresa(),pedido,entregador,usuario.getAddress(),pedido1.getListaDeProdutos());
            entregasPorId.put(contadorEntrega,entrega);
            pedido1.setEstado("entregando");
            contadorEntrega++;
        }
        return contadorEntrega-1;
    }
    public String getEntrega(int id, String atributo) throws AtributoValidationException, AtributoExistsValidationException {
        if(atributo == null || atributo.isEmpty()){
            throw new AtributoValidationException();
        }
        Entrega entrega = entregasPorId.get(id);
        if(atributo.equals("entregador")){
            UsuarioEntregador entregador = entregadoresPorId.get(entrega.getEntregadorId());
            return entregador.getName();
        }
        switch (atributo){
            case "destino":
                return entrega.getDestino();
            case "cliente":
                return entrega.getCliente();
            case "empresa":
                return  entrega.getEmpresa();
            case "pedido":
                return String.valueOf(entrega.getPedidoId());
            case "produtos":
                return entrega.getProdutos();
            default:
                throw new AtributoExistsValidationException();
        }
    }
    public int getIdEntrega(int pedido) throws NaoExisteEntregaComEsseId {
        for (Map.Entry<Integer, Entrega> entry : entregasPorId.entrySet()) {
            Entrega entrega = entry.getValue();
            if(entrega.getPedidoId() == pedido){
                return entrega.getEntregaId();
            }
        }
        throw new NaoExisteEntregaComEsseId();
    }
    public void entregar(int entrega) throws EntregaNotFound {
        if(!entregasPorId.containsKey(entrega)){
            throw new EntregaNotFound();
        }
        else{
            Entrega entrega1 = entregasPorId.get(entrega);
            Pedidos pedido = pedidosPorNumero.get(entrega1.getPedidoId());
            pedido.setEstado("entregue");
        }

    }


    public void validarData(String name, String email, String senha, String address) throws NameValidationException, EmailValidationException, SenhaValidationException, AdressValidationException, EmailFormatValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new NameValidationException();
        }
        if (email == null ||email.isEmpty()) {
            throw new  EmailValidationException();
        }
        if (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new  EmailValidationException();
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new SenhaValidationException();
        }
        if (address == null || address.trim().isEmpty()) {
            throw new AdressValidationException() ;
        }
    }
    public void validarCpf(String cpf) throws CpfValidationException {
        if (cpf == null || cpf.length()!= 14) {
            throw new CpfValidationException();
        }
    }
    public void validarEmpresa(int dono, String nome, String address) throws UserNotDonoException, EmpresaNomeValidationException, EmpresaNomeAddressValidationException, NameValidationException {
        if (!donosPorId.containsKey(dono)) {
            throw new UserNotDonoException();
        }
        for (Map.Entry<Integer, Empresa> entry : empresasPorId.entrySet()) {

            Empresa empresa = entry.getValue();

            if(empresa.getIdDono() != dono && empresa.getName().equals(nome)){
                throw new EmpresaNomeValidationException();
            }

            if(empresa.getIdDono() == dono && empresa.getName().equals(nome) && empresa.getAddress().equals(address)){
                throw new EmpresaNomeAddressValidationException();
            }
        }

    }
    public void validarMercado(String tipoEmpresa,String nome, String address ,String abre, String fecha,String tipoMercado) throws TimeFormatInvalid, InvalidTime, TipoEmpresaInvald, NameValidation, AdressEmpresaValidation, invalidHour, InvalidTipoMercadoValidation {
        if(tipoEmpresa == null || !tipoEmpresa.equals("mercado") ){
            throw new TipoEmpresaInvald();
        }
        if(nome == null){
            throw new NameValidation();
        }
        if(address == null){
            throw new AdressEmpresaValidation();
        }

        if(tipoMercado == null){
            throw new InvalidTipoMercadoValidation();
        }
        validarHora(abre,fecha);
    }
    public void validarHora(String abre, String fecha) throws invalidHour, TimeFormatInvalid, InvalidTime {
        if(abre == null || fecha == null){
            throw new invalidHour();
        }

        if(abre.length() <= 4 || fecha.length() <= 4){
            throw new TimeFormatInvalid();
        }
        if(abre.length() > 5 || fecha.length() > 5){
            throw new TimeFormatInvalid();
        }
        String hora = abre.substring(0,2);
        String horaf = fecha.substring(0,2);
        Integer intFecha = Integer.valueOf(horaf);
        Integer intAbre = Integer.valueOf(hora);
        if (intAbre > 23 || intFecha > 23){
            throw new InvalidTime();
        }
        if(intAbre > intFecha){
            throw new InvalidTime();
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        try{
            LocalTime.parse(abre, formato);
        }
        catch (DateTimeParseException e){
            throw new TimeFormatInvalid();
        }
        try{
            LocalTime.parse(fecha, formato);
        }
        catch (DateTimeParseException e){
            throw new TimeFormatInvalid();
        }

    }
}
