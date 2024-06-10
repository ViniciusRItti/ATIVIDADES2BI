package br.unipar;
import java.sql.*;

public class MainProduto {

    private static final String url = "jdbc:postgresql://localhost:5432/Exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";


    public static void main(String[] args) {
        criartabelaProduto();
        inserirProduto("PC", 3000);
        listarTodosProdutos();
        excluirTodosProdutos();
    }

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void criartabelaProduto() {
        String sql = "CREATE TABLE IF NOT EXISTS produto (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "descricao VARCHAR(255) NOT NULL," +
                "preco DECIMAL(10, 2) NOT NULL" +
                ");";
        try (Connection conn = connection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void inserirProduto(String descricao, double preco) {
        try {
            Connection conn = connection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO produto (descricao, preco) "
                            + "VALUES (?,?)"
            );
            preparedStatement.setString(1, descricao);
            preparedStatement.setDouble(2, preco);
            preparedStatement.executeUpdate();
            System.out.println("PRODUTO INSERIDO");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int listarTodosProdutos() {
        int i = 0;
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            System.out.println("Listando produtos");
            ResultSet result = statement.executeQuery("SELECT * FROM produto");
            while (result.next()) {
                System.out.println(result.getInt("id_produto"));
                System.out.println(result.getString("descricao"));
                System.out.println(result.getDouble("Preço"));
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public static void excluirTodosProdutos() {
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            int rowsAffected = statement.executeUpdate("DELETE FROM produto");
            System.out.println(rowsAffected + " produto(s) excluído(s) com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}