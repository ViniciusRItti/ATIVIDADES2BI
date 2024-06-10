import static org.junit.Assert.*;
import br.unipar.MainUsuario;
import br.unipar.MainProduto;
import br.unipar.MainCliente;
import org.junit.*;
import java.sql.*;
import java.time.LocalDate;

public class MainTest {

    @Before
    public void setup() {
        try (Connection conn = MainUsuario.connection();
             Statement statement = conn.createStatement()) {
            MainUsuario.criartabelaUsuario();
            MainProduto.criartabelaProduto();
            MainCliente.criartabelaCliente();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Setup failed: " + e.getMessage());
        }
    }

    @After
    public void limparDados() {
        try (Connection conn = MainUsuario.connection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("DELETE FROM usuarios");
            statement.executeUpdate("DELETE FROM produto");
            statement.executeUpdate("DELETE FROM cliente");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Data cleanup failed: " + e.getMessage());
        }
    }

    @Test
    public void testCriarTabelaUsuario() {
        MainUsuario.criartabelaUsuario();
        try (Connection conn = MainUsuario.connection();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS count FROM information_schema.tables WHERE table_name = 'usuarios'");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("count"));
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testInserirUsuario() {
        MainUsuario.criartabelaUsuario();
        MainUsuario.inserirUsuario("Usuario", "31598", "Vini", LocalDate.now().toString());
        try (Connection conn = MainUsuario.connection();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS count FROM usuarios WHERE username = 'Usuario'");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("count"));
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testListarTodosUsuarios() {
        MainUsuario.criartabelaUsuario();
        MainUsuario.inserirUsuario("usuario1", "senha1", "User One", "2000-01-01");
        MainUsuario.inserirUsuario("usuario2", "senha2", "User Two", "2000-01-02");
        assertEquals(2, MainUsuario.listarTodosUsuario());
    }

    @Test
    public void testInserirProduto() {
        MainProduto.criartabelaProduto();
        MainProduto.inserirProduto("Pc", 300.00);
        try (Connection conn = MainProduto.connection();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS count FROM produto WHERE descricao = 'Mouse'");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("count"));
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testListarTodosProdutos() {
        MainProduto.criartabelaProduto();
        MainProduto.inserirProduto("Pc", 300.00);
        MainProduto.inserirProduto("Fone", 200.00);
        assertEquals(2, MainProduto.listarTodosProdutos());
    }

    @Test
    public void testInserirCliente() {
        MainCliente.criartabelaCliente();
        MainCliente.inserirCliente("Vinicius","2123213231232");
        try (Connection conn = MainCliente.connection();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS count FROM cliente WHERE nome = 'Teste Cliente'");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("count"));
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testListarTodosClientes() {
        MainCliente.criartabelaCliente();
        MainCliente.inserirCliente("Cliente 1", "111.111.111-11");
        MainCliente.inserirCliente("Cliente 2", "222.222.222-22");
        assertEquals(2, MainCliente.listarTodosClientes());
    }

    @Test
    public void testExcluirTodosClientes() {
        MainCliente.criartabelaCliente();
        MainCliente.inserirCliente("Cliente1", "111.111.111-11");
        MainCliente.inserirCliente("Cliente2", "222.222.222-22");

        assertEquals(2, MainCliente.listarTodosClientes());

        MainCliente.excluirTodosClientes();

        assertEquals(0, MainCliente.listarTodosClientes());
    }

    @Test
    public void testExcluirTodosProdutos() {
        MainProduto.criartabelaProduto();
        MainProduto.inserirProduto("Produto1", 100.00);
        MainProduto.inserirProduto("Produto2", 200.00);

        assertEquals(2, MainProduto.listarTodosProdutos());

        MainProduto.excluirTodosProdutos();

        assertEquals(0, MainProduto.listarTodosProdutos());
    }

    @Test
    public void testExcluirTodosUsuarios() {
        MainUsuario.criartabelaUsuario();
        MainUsuario.inserirUsuario("Usuario1", "senha1", "Nome1", "2000-01-01");
        MainUsuario.inserirUsuario("Usuario2", "senha2", "Nome2", "2000-02-02");

        assertEquals(2, MainUsuario.listarTodosUsuario());

        MainUsuario.excluirTodosUsuarios();

        assertEquals(0, MainUsuario.listarTodosUsuario());
    }
}
