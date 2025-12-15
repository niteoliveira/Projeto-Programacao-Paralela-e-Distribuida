public class Mesa {
    private final boolean[] garfosDisponiveis;
    private final int numFilosofos;

    public Mesa(int numFilosofos) {
        this.numFilosofos = numFilosofos;
        this.garfosDisponiveis = new boolean[numFilosofos];
        
        for (int i = 0; i < numFilosofos; i++) {
            garfosDisponiveis[i] = true;
        }
    }

    public synchronized void pegarGarfos(int idFilosofo) throws InterruptedException {
        int garfoEsquerdo = idFilosofo;
        int garfoDireito = (idFilosofo + 1) % numFilosofos;

        while (!garfosDisponiveis[garfoEsquerdo] || !garfosDisponiveis[garfoDireito]) {
            wait();
        }

        garfosDisponiveis[garfoEsquerdo] = false;
        garfosDisponiveis[garfoDireito] = false;
    }

    public synchronized void largarGarfos(int idFilosofo) {
        int garfoEsquerdo = idFilosofo;
        int garfoDireito = (idFilosofo + 1) % numFilosofos;

        garfosDisponiveis[garfoEsquerdo] = true;
        garfosDisponiveis[garfoDireito] = true;

        notifyAll();
    }
}
