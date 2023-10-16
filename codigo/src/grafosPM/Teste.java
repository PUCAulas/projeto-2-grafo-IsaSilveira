package grafosPM;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Teste {
    private Grafo grafo;

    @Before
    public void setUp() {
        grafo = new Grafo();

        // Adicione cidades e estradas ao grafo para os testes
        grafo.adicionarCidade("Cidade do Cabo");
        grafo.adicionarCidade("Joanesburgo");
        grafo.adicionarCidade("Nairobi");
        grafo.adicionarCidade("Paris");
        grafo.adicionarCidade("Londres");

        grafo.adicionarAresta("Cidade do Cabo", "Joanesburgo", 1270);
        grafo.adicionarAresta("Cidade do Cabo", "Nairobi", 3900);
        grafo.adicionarAresta("Cidade do Cabo", "Paris", 8900);
        grafo.adicionarAresta("Joanesburgo", "Nairobi", 4700);
        grafo.adicionarAresta("Paris", "Londres", 340);
    }

    @Test
    public void testExisteEstradaDeQualquerParaQualquer() {
        assertTrue(grafo.existeEstradaDeQualquerParaQualquer());
    }

    @Test
    public void testIdentificarCidadesInacessiveis() {
        List<String> cidadesInacessiveis = grafo.identificarCidadesInacessiveis();
        assertEquals(0, cidadesInacessiveis.size());
    }

    @Test
    public void testRecomendarVisitaEmTodasCidades() {
        List<String> recomendacaoVisita = grafo.recomendarVisitaEmTodasCidades();
        List<String> esperado = Arrays.asList(
            "Cidade do Cabo", "Joanesburgo", "Nairobi", "Paris", "Londres"
        );
        assertEquals(esperado, recomendacaoVisita);
    }

    @Test
    public void testRecomendarRotaParaPassageiro() {
        List<String> rotaRecomendada = grafo.recomendarRotaParaPassageiro("Cidade do Cabo");
        List<String> esperado = Arrays.asList(
            "Cidade do Cabo", "Joanesburgo", "Nairobi", "Paris", "Londres", "Cidade do Cabo"
        );
        assertEquals(esperado, rotaRecomendada);
    }

    @Test
    public void testVerificarConectividade() {
        assertTrue(grafo.verificarConectividade("Cidade do Cabo"));
        assertFalse(grafo.verificarConectividade("Tóquio"));
    }
}
