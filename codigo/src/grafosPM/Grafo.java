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
    
    public boolean existeEstradaDeQualquerParaQualquer() {
        for (Cidade origem : cidades) {
            for (Cidade destino : cidades) {
                if (origem != destino) {
                    if (obterDistancia(origem.getNome(), destino.getNome()) == -1) {
                        return false; // Se não houver uma conexão direta, retorna false.
                    }
                }
            }
        }
        return true; // Se todas as cidades estiverem conectadas, retorna true.
    }
    
    public List<String> identificarCidadesInacessiveis() {
        List<String> cidadesInacessiveis = new ArrayList<>();
        
        for (Cidade origem : cidades) {
            boolean acessivel = false;
            for (Cidade destino : cidades) {
                if (origem != destino && obterDistancia(origem.getNome(), destino.getNome()) != -1) {
                    acessivel = true;
                    break;
                }
            }
            if (!acessivel) {
                cidadesInacessiveis.add(origem.getNome());
            }
        }
        
        return cidadesInacessiveis;
    }
    
    public List<String> recomendarVisitaEmTodasCidades() {
        List<String> cidadesVisitadas = new ArrayList<>();
        List<String> recomendacao = new ArrayList<>();
        
        Cidade partida = cidades.get(0); // Começar a partir da primeira cidade.
        
        while (cidadesVisitadas.size() < cidades.size()) {
            cidadesVisitadas.add(partida.getNome());
            recomendacao.add(partida.getNome());
            
            Cidade proximaCidade = null;
            int menorDistancia = Integer.MAX_VALUE;
            
            for (Cidade cidade : cidades) {
                if (!cidadesVisitadas.contains(cidade.getNome())) {
                    int distancia = obterDistancia(partida.getNome(), cidade.getNome());
                    if (distancia != -1 && distancia < menorDistancia) {
                        menorDistancia = distancia;
                        proximaCidade = cidade;
                    }
                }
            }
            
            if (proximaCidade != null) {
                partida = proximaCidade;
            } else {
                // Se não houver próxima cidade acessível, volte para a primeira cidade não visitada.
                for (Cidade cidade : cidades) {
                    if (!cidadesVisitadas.contains(cidade.getNome())) {
                        partida = cidade;
                        break;
                    }
                }
            }
        }
        
        return recomendacao;
    }
    
    public List<String> recomendarRotaParaPassageiro(String partida) {
        List<String> rotaRecomendada = new ArrayList<>();
        if (cidades.isEmpty()) {
            return rotaRecomendada;
        }

        if (!cidades.contains(partida)) {
            System.out.println("Cidade de partida não encontrada.");
            return rotaRecomendada;
        }

        Cidade cidadePartida = encontrarCidade(partida);

        // Clonar as cidades para garantir que não sejam modificadas.
        List<Cidade> cidadesClone = new ArrayList<>(cidades);

        // Encontre uma rota que visite todas as cidades, incluindo a cidade de partida, e retorne a ela.
        rotaRecomendada.add(partida);
        cidadesClone.remove(cidadePartida);

        while (!cidadesClone.isEmpty()) {
            Cidade cidadeAtual = encontrarCidade(rotaRecomendada.get(rotaRecomendada.size() - 1));
            Cidade cidadeMaisProxima = null;
            int distanciaMinima = Integer.MAX_VALUE;

            for (Cidade cidade : cidadesClone) {
                int distancia = cidadeAtual.obterDistanciaPara(cidade);
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    cidadeMaisProxima = cidade;
                }
            }

            if (cidadeMaisProxima != null) {
                rotaRecomendada.add(cidadeMaisProxima.getNome());
                cidadesClone.remove(cidadeMaisProxima);
            }
        }

        // Retorne à cidade de partida.
        rotaRecomendada.add(partida);

        return rotaRecomendada;
    }
    
    public boolean verificarConectividade(String partida) {
        List<String> cidadesVisitadas = new ArrayList<>();
        cidadesVisitadas.add(partida);
        boolean alteracao;
        
        do {
            alteracao = false;
            for (Cidade cidade : cidades) {
                if (!cidadesVisitadas.contains(cidade.getNome())) {
                    for (String cidadeVisitada : cidadesVisitadas) {
                        if (obterDistancia(cidade.getNome(), cidadeVisitada) != -1) {
                            cidadesVisitadas.add(cidade.getNome());
                            alteracao = true;
                            break;
                        }
                    }
                }
            }
        } while (alteracao);
        
        return cidadesVisitadas.size() == cidades.size();
    }
    
    public List<String> encontrarRotaAproximada() {
        List<String> rota = new ArrayList<>();
        Set<Cidade> cidadesNaoVisitadas = new HashSet<>(cidades);

        if (cidades.isEmpty()) {
            return rota;
        }

        // Comece a rota pela primeira cidade (por exemplo, Cidade do Cabo).
        Cidade cidadeAtual = cidades.get(0);
        rota.add(cidadeAtual.getNome());
        cidadesNaoVisitadas.remove(cidadeAtual);

        while (!cidadesNaoVisitadas.isEmpty()) {
            Cidade cidadeMaisProxima = null;
            int distanciaMinima = Integer.MAX_VALUE;

            for (Cidade cidade : cidadesNaoVisitadas) {
                int distancia = cidadeAtual.obterDistanciaPara(cidade);
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    cidadeMaisProxima = cidade;
                }
            }

            if (cidadeMaisProxima != null) {
                rota.add(cidadeMaisProxima.getNome());
                cidadeAtual = cidadeMaisProxima;
                cidadesNaoVisitadas.remove(cidadeAtual);
            }
        }

        // Volte para a cidade de origem para completar o ciclo.
        rota.add(cidades.get(0).getNome());

        return rota;
    }

    
}