package br.unipar;
import java.sql.*;

public class MainCliente {

    private static final String url = "jdbc:postgresql://localhost:5432/Exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";


    public static void main(String[] args) {
        criartabelaCliente();
        inserirCliente("Vinicius","543.331.654-87");
        listarTodosClientes();
        excluirTodosClientes();
    }

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void criartabelaCliente() {
        String sql = "CREATE TABLE IF NOT EXISTS cliente (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(255) NOT NULL," +
                "cpf VARCHAR(14) NOT NULL" +
                ");";
        try (Connection conn = connection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void inserirCliente(String nome, String cpf) {
        try {
            Connection conn = connection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO cliente (nome, cpf) "
                            + "VALUES (?, ?)"
            );
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cpf);
            preparedStatement.executeUpdate();
            System.out.println("CLIENTE INSERIDO COM SUCESSO!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int listarTodosClientes() {
        int i = 0;
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            System.out.println("Listando clientes");
            ResultSet result = statement.executeQuery("SELECT * FROM cliente");
            while (result.next()) {
                System.out.println(result.getInt("id_cliente"));
                System.out.println(result.getString("nome"));
                System.out.println(result.getString("cpf"));
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public static void excluirTodosClientes() {
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
