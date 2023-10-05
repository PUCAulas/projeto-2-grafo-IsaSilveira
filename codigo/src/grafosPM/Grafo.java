package grafosPM;
import java.util.*;

public class Grafo {

    private List<Cidade> cidades;

    public Grafo() {
        cidades = new ArrayList<>();
    }

    public void adicionarCidade(String nome) {
        cidades.add(new Cidade(nome));
    }

    public void adicionarAresta(String origem, String destino, int distancia) {
        Cidade cidadeOrigem = encontrarCidade(origem);
        Cidade cidadeDestino = encontrarCidade(destino);

        if (cidadeOrigem != null && cidadeDestino != null) {
            cidadeOrigem.adicionarAresta(cidadeDestino, distancia);
            cidadeDestino.adicionarAresta(cidadeOrigem, distancia);
        }
    }

    public int obterDistancia(String origem, String destino) {
        Cidade cidadeOrigem = encontrarCidade(origem);
        Cidade cidadeDestino = encontrarCidade(destino);

        if (cidadeOrigem != null && cidadeDestino != null) {
            return cidadeOrigem.obterDistanciaPara(cidadeDestino);
        }
        return -1; // Retorne -1 se não houver conexão direta
    }

    public List<String> obterVertices() {
        List<String> nomesCidades = new ArrayList<>();
        for (Cidade cidade : cidades) {
            nomesCidades.add(cidade.getNome());
        }
        return nomesCidades;
    }

    private Cidade encontrarCidade(String nome) {
        for (Cidade cidade : cidades) {
            if (cidade.getNome().equals(nome)) {
                return cidade;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // Adicionando as cidades ao grafo
        grafo.adicionarCidade("Cidade do Cabo");
        grafo.adicionarCidade("Joanesburgo");
        grafo.adicionarCidade("Nairobi");
        grafo.adicionarCidade("Paris");
        grafo.adicionarCidade("Londres");
        grafo.adicionarCidade("Amsterdã");
        grafo.adicionarCidade("Berlim");
        grafo.adicionarCidade("Tóquio");
        grafo.adicionarCidade("Pequim");
        grafo.adicionarCidade("Mumbai");
        grafo.adicionarCidade("Bangcoc");

        // Adicionando as arestas ao grafo 
        grafo.adicionarAresta("Cidade do Cabo", "Joanesburgo", 1270);
        grafo.adicionarAresta("Cidade do Cabo", "Nairobi", 3900);
        grafo.adicionarAresta("Cidade do Cabo", "Paris", 8900);
        grafo.adicionarAresta("Joanesburgo", "Nairobi", 4700);
        grafo.adicionarAresta("Joanesburgo", "Mumbai", 6500);
        grafo.adicionarAresta("Nairobi", "Mumbai", 4300);
        grafo.adicionarAresta("Paris", "Londres", 340);
        grafo.adicionarAresta("Paris", "Berlim", 1050);
        grafo.adicionarAresta("Londres", "Amsterdã", 320);
        grafo.adicionarAresta("Londres", "Cidade do Cabo", 8900);
        grafo.adicionarAresta("Amsterdã", "Paris", 430);
        grafo.adicionarAresta("Amsterdã", "Berlim", 620);
        grafo.adicionarAresta("Berlim", "Paris", 1050);
        grafo.adicionarAresta("Tóquio", "Pequim", 2400);
        grafo.adicionarAresta("Tóquio", "Mumbai", 6800);
        grafo.adicionarAresta("Pequim", "Mumbai", 3700);
        grafo.adicionarAresta("Pequim", "Bangcoc", 2700);
        grafo.adicionarAresta("Mumbai", "Nairobi", 4300);
        grafo.adicionarAresta("Bangcoc", "Joanesburgo", 8800);

        // Exemplo de como obter a distância entre duas cidades
        int distanciaParisLondres = grafo.obterDistancia("Paris", "Londres");
        if (distanciaParisLondres != -1) {
            System.out.println("A distância entre Paris e Londres é: " + distanciaParisLondres + " km");
        } else {
            System.out.println("Não há uma conexão direta entre Paris e Londres.");
        }

        // Exemplo de como obter a lista de cidades no grafo
        List<String> cidades = grafo.obterVertices();
        System.out.println("Cidades no grafo: " + cidades);
    }
}