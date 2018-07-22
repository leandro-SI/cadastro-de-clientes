/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import aplicacao.Cadastrar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Endereco;
import tools.DAOBaseJDBC;

/**
 *
 * @author Leandro
 */
public class ClienteDAOJDBC extends DAOBaseJDBC implements ClienteDAO{
    
    
    public boolean CadastrarCliente(Endereco end, Cliente cli){
        
        boolean validacao = false;
        
        String sqlEndereco = "INSERT INTO endereco(logradouro, bairro, cidade, cep) VALUES "
                + "(?, ?, ?, ?)";
        
        String sqlCliente = "INSERT INTO cliente(nome, razaoSocial, cnpj, telefone, celular, email, Endereco_idEndereco) "
        + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        
        try{
            
            PreparedStatement stmt = conn.prepareStatement(sqlEndereco);
            stmt.setString(1, end.getLogradouro());
            stmt.setString(2, end.getBairro());
            stmt.setString(3, end.getCidade());
            stmt.setString(4, end.getCep());
            stmt.executeUpdate();
            //stmt.close();
            
            stmt = conn.prepareStatement(sqlCliente);
            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getRazaoSocial());
            stmt.setString(3, cli.getCnpj());
            stmt.setString(4, cli.getTelefone());
            stmt.setString(5, cli.getCelular());
            stmt.setString(6, cli.getEmail());
            stmt.setInt(7, BuscarIdEndereco());
            stmt.executeUpdate();
            stmt.close();
            
            validacao = true;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + e.getMessage());
        }
        
        
        return validacao;
    }
    
    
    public int BuscarIdEndereco(){
        
        int numero = 0;
        
        String sql = "SELECT idEndereco FROM endereco ORDER BY idEndereco DESC LIMIT 1";
        String sql_aux = "SELECT idEndereco FROM endereco WHERE idEndereco = (select max(idEndereco) from endereco)";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql_aux);
            
            ResultSet resultado = stmt.executeQuery();
            
            if(resultado.next()){
                
                numero = resultado.getInt("idEndereco");
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + e.getMessage());
        }
        
        return numero;
    }
    
    public Cliente BuscarCliente(String cnpjBuscado){
        
        Cliente clienteProcurado = null;
        
        String sql = "SELECT * FROM cliente c INNER JOIN endereco e "
                + "ON c.Endereco_idEndereco = e.idEndereco WHERE c.cnpj = ?";
        
        try{
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cnpjBuscado);
            ResultSet resultado = stmt.executeQuery();
            
            if(resultado.next()){
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(resultado.getInt("idEndereco"));
                endereco.setLogradouro(resultado.getString("logradouro"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setCep(resultado.getString("cep"));
                
                Cliente cliente = new Cliente();
                
                cliente.setIdCliente(resultado.getInt("idCliente"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setRazaoSocial(resultado.getString("razaoSocial"));
                cliente.setCnpj(resultado.getString("cnpj"));
                cliente.setTelefone(resultado.getString("telefone"));
                cliente.setCelular(resultado.getString("celular"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setEndereco(endereco);
                
                clienteProcurado = cliente;
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + e.getMessage());
        }
        
        return clienteProcurado;
        
    }
    
    public Endereco BuscarEndereco(Cliente cliente){
        
        Endereco enderecoProcurado = null;
        String sql = "SELECT * FROM endereco WHERE idEndereco = ?";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdCliente());
            ResultSet resultado = stmt.executeQuery();
            
            if(resultado.next()){
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(resultado.getInt("idEndereco"));
                endereco.setLogradouro(resultado.getString("logradouro"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setCep(resultado.getString("cep"));
                
                enderecoProcurado = endereco;
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + e.getMessage());
        }
        
        return enderecoProcurado;
    }

   
}
