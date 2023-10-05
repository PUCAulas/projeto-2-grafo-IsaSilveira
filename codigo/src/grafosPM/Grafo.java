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

    
}