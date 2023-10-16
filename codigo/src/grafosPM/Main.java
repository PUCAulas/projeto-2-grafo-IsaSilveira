package grafosPM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
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
        
        Scanner entrada = new Scanner(System.in);
        System.out.println("Bem vindo à Soluções em Grafos. Digite um numero para selecionar a ação desejada:");
        System.out.println("1 - Verificar se existe uma estrada de qualquer cidade para qualquer cidade ");
        System.out.println("2 - Identificar cidades inacessíveis");
        System.out.println("3 - Recomendação de visitação");
        System.out.println("4 - Recomendação de rota para passageiro");
        
        int numero = entrada.nextInt();
         switch (numero) {
           case 1:
               // (a) Verificar se existe uma estrada de qualquer cidade para qualquer cidade
               boolean existeEstradaDeQualquerParaQualquer = grafo.existeEstradaDeQualquerParaQualquer();
               System.out.println("Existe estrada de qualquer cidade para qualquer cidade: " + existeEstradaDeQualquerParaQualquer);
             break;
           case 2:
        	// (b) Identificar cidades inacessíveis
               List<String> cidadesInacessiveis = grafo.identificarCidadesInacessiveis();
               System.out.println("Cidades inacessíveis: " + cidadesInacessiveis);
             break;
           case 3:
        	// (c) Recomendação de visitação
               List<String> recomendacaoVisita = grafo.recomendarVisitaEmTodasCidades();
               System.out.println("Recomendação de visitação em todas as cidades: " + recomendacaoVisita);
             break;
           case 4:
        	// (d) Recomendação de rota para passageiro
               
        	   List<String> rotaAproximada = grafo.encontrarRotaAproximada();
        	   System.out.println("Rota Aproximada: " + rotaAproximada);
             break;
           default:
             System.out.println("O número escolhido é inválido! Digite um número entre 1 a 4.");
         }
        
        

        

        

        
    }
}
