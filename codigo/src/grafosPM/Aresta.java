package grafosPM;

class Aresta {
    private Cidade destino;
    private int distancia;

    public Aresta(Cidade destino, int distancia) {
        this.destino = destino;
        this.distancia = distancia;
    }

    public Cidade getDestino() {
        return destino;
    }

    public int getDistancia() {
        return distancia;
    }
}