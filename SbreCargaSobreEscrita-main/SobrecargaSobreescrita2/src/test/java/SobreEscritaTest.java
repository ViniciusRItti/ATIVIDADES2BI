
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import br.unipar.SobreEscrita.Moto;
import br.unipar.SobreEscrita.Carro;

public class SobreEscritaTest {

    @Test
    public void testarDescricaoCarro() {
        Carro carro = new Carro();
        String descricaoEsperada = "Marca:Mercedes Cor:Preta Ano: 2020";
        assertEquals(descricaoEsperada, carro.descricao());
    }

    @Test
    public void testarDescricaoMoto() {
        Moto moto = new Moto();
        String descricaoEsperada = "Marca:BMW Cor:Branca e Azul Ano:2023";
        assertEquals(descricaoEsperada, moto.descricao());
    }

    public static void main(String[] args) {


    }
}
