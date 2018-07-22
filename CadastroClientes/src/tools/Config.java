/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author Leandro
 */
public interface Config {
    
    public static final String NOME_DRIVER = "org.firebirdsql.jdbc.FBDriver";
    public static final String BD_URL = "jdbc:firebirdsql:localhost/3050:C:/Sistema de Cadastro/bd/DBCLICIA.FDB"; // #DEFINIR A BASE DE DADOS E MODIFICAR
    public static final String BD_LOGIN = "sysdba";
    public static final String BD_SENHA = "masterkey";
}
