package br.unipar;

import java.sql.*;

public class MainUsuario {

    private static final String url = "jdbc:postgresql://localhost:5432/Exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";

    public static void main(String[] args) {
        criartabelaUsuario();
        inserirUsuario("taffe", "12345","Fabio","1890-01-01");
        listarTodosUsuario();
        excluirTodosUsuarios();
    }

    public static Connection connection() throws SQLException {
      return DriverManager.getConnection(url, user, password);
    }

    public static void criartabelaUsuario(){
       try {
           Connection conn = connection();

           Statement statement = conn.createStatement();
           String sql = "CREATE TABLE IF NOT EXISTS usuarios( "
                   + " codigo SERIAL PRIMARY KEY,"
                   + " username VARCHAR(50) UNIQUE NOT NULL, "
                   + " password VARCHAR(300) NOT NULL, "
                   + " nome VARCHAR(50) UNIQUE NOT NULL,"
                   + " nascimento DATE )";

           statement.execute(sql);

           System.out.println("TABELA CRIADA COM SUCESSO!");

       } catch (SQLException exception) {
           exception.printStackTrace();
       }
    }

    public static void inserirUsuario(String username, String password, String nome, String dataNascimento) {
        try {
            Connection conn = connection();

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO usuarios (username, password, nome, nascimento) "
                    + "VALUES (?, ?, ?, ?)"
                    );

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nome);
            preparedStatement.setDate(4, java.sql.Date.valueOf(dataNascimento));

            preparedStatement.executeUpdate();

            System.out.println("USUARIO INSERIDO COM SUCESSO!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object listarTodosUsuario(){

        try {
            Connection conn = connection();

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM usuarios");

            while (result.next()) {
                System.out.println(result.getInt("codigo"));
                System.out.println(result.getString("username"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void excluirTodosUsuarios() {
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            int rowsAffected = statement.executeUpdate("DELETE FROM usuarios");
            System.out.println(rowsAffected + " usuário(s) excluído(s) com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}