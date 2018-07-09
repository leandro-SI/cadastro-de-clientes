/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class DAOBaseJDBC {
    
     protected static Connection conn;
     public static final String SUCESSO_LOGIN = "success";
     public static final String FALHA_LOGIN = "failure";
     
    
    static{
        try{
            // Carrega o driver do HSQLDB e conecta
            Class.forName(Config.NOME_DRIVER);
            conn = DriverManager.getConnection(Config.BD_URL, Config.BD_LOGIN, Config.BD_SENHA);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Classe não encontrada: Erro - " + e);
            System.exit(1);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Falha no acesso ao banco de dados: Erro - " + e);
        }
    }
}
