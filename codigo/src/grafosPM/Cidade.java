package grafosPM;

import java.util.ArrayList;
import java.util.List;

class Cidade {
    private String nome;
    private List<Aresta> arestas;

    public Cidade(String nome) {
        this.nome = nome;
        this.arestas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarAresta(Cidade destino, int distancia) {
        arestas.add(new Aresta(destino, distancia));
    }

    public int obterDistanciaPara(Cidade destino) {
        for (Aresta aresta : arestas) {
            if (aresta.getDestino() == destino) {
                return aresta.getDistancia();
            }
        }
        return -1; // Retorne -1 se não houver conexão direta
    }
}