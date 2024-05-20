import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import br.unipar.SobreCarga;

public class SobreCargaTest {

    @Test
    public void testDividirInt() {
        SobreCarga sobreCarga = new SobreCarga();
        int resultado = sobreCarga.calc(10, 2);
        assertEquals(5, resultado);
    }

    @Test
    public void testDividirDouble() {
        SobreCarga sobreCarga = new SobreCarga();
        double resultado = sobreCarga.calc(10.0, 2.0);
        assertEquals(12.0, resultado, 0.000001);
    }
}
