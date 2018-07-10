/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Cliente;
import modelo.Endereco;

/**
 *
 * @author Leandro
 */
public interface ClienteDAO {
    
    public boolean CadastrarCliente(Endereco end, Cliente cli);
    public int BuscarIdEndereco();
    public Cliente BuscarCliente(String cnpjBuscado);
    public Endereco BuscarEndereco(Cliente cliente);
}
