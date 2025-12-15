import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Metricas {
    private final int numFilosofos;
    private final AtomicInteger[] refeicoesPorFilosofo;
    private final AtomicLong[] tempoEsperaPorFilosofo;
    private final AtomicInteger[] tentativasPorFilosofo;
    private final long tempoInicio;

    public Metricas(int numFilosofos) {
        this.numFilosofos = numFilosofos;
        this.refeicoesPorFilosofo = new AtomicInteger[numFilosofos];
        this.tempoEsperaPorFilosofo = new AtomicLong[numFilosofos];
        this.tentativasPorFilosofo = new AtomicInteger[numFilosofos];
        this.tempoInicio = System.currentTimeMillis();

        for (int i = 0; i < numFilosofos; i++) {
            refeicoesPorFilosofo[i] = new AtomicInteger(0);
            tempoEsperaPorFilosofo[i] = new AtomicLong(0);
            tentativasPorFilosofo[i] = new AtomicInteger(0);
        }
    }

    public void registrarRefeicao(int idFilosofo) {
        refeicoesPorFilosofo[idFilosofo].incrementAndGet();
    }

    public void registrarTentativa(int idFilosofo) {
        tentativasPorFilosofo[idFilosofo].incrementAndGet();
    }

    public void registrarTempoEspera(int idFilosofo, long tempoEspera) {
        tempoEsperaPorFilosofo[idFilosofo].addAndGet(tempoEspera);
    }

    public void imprimirRelatorio() {
        long tempoTotal = (System.currentTimeMillis() - tempoInicio) / 1000;
        
        System.out.println("\n========================================");
        System.out.println("RELATORIO DE METRICAS - " + tempoTotal + " segundos");
        System.out.println("========================================");

        int totalRefeicoes = 0;
        int minRefeicoes = Integer.MAX_VALUE;
        int maxRefeicoes = 0;
        double somaRefeicoes = 0;

        for (int i = 0; i < numFilosofos; i++) {
            int refeicoes = refeicoesPorFilosofo[i].get();
            int tentativas = tentativasPorFilosofo[i].get();
            long tempoEspera = tempoEsperaPorFilosofo[i].get();
            double tempoMedio = tentativas > 0 ? (double) tempoEspera / tentativas : 0;

            System.out.println("F" + i + ":");
            System.out.println("  Refeicoes: " + refeicoes);
            System.out.println("  Tentativas: " + tentativas);
            System.out.println("  Tempo medio de espera: " + String.format("%.2f", tempoMedio) + " ms");

            totalRefeicoes += refeicoes;
            somaRefeicoes += refeicoes;
            if (refeicoes < minRefeicoes) minRefeicoes = refeicoes;
            if (refeicoes > maxRefeicoes) maxRefeicoes = refeicoes;
        }

        double media = somaRefeicoes / numFilosofos;
        
        double somaQuadrados = 0;
        for (int i = 0; i < numFilosofos; i++) {
            double diff = refeicoesPorFilosofo[i].get() - media;
            somaQuadrados += diff * diff;
        }
        double desvioPadrao = Math.sqrt(somaQuadrados / numFilosofos);
        double coeficienteVariacao = (desvioPadrao / media) * 100;

        System.out.println("\n--- ESTATISTICAS GERAIS ---");
        System.out.println("Total de refeicoes: " + totalRefeicoes);
        System.out.println("Media por filosofo: " + String.format("%.2f", media));
        System.out.println("Minimo: " + minRefeicoes);
        System.out.println("Maximo: " + maxRefeicoes);
        System.out.println("Desvio padrao: " + String.format("%.2f", desvioPadrao));
        System.out.println("Coeficiente de variacao: " + String.format("%.2f", coeficienteVariacao) + "%");
        System.out.println("========================================\n");
    }
}
