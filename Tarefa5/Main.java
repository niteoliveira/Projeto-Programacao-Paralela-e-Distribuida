import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        List<Garfo> garfos = new ArrayList<>();
        List<Filosofo> filosofos = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        
        int numFilosofos = 5;
        Mesa mesa = new Mesa(numFilosofos);
        Metricas metricas = new Metricas(numFilosofos);

        for (int i=0;i<5;i++) {
            garfos.add(new Garfo("G"+i));
        }
        
        for (int j = 0; j < 5; j++) {
            filosofos.add(new Filosofo(j, "F" + j, garfos.get(j), garfos.get((j + 1) % 5), mesa, metricas));
        }

        for (int k=0; k < 5; k++) {
            threads.add(new Thread(filosofos.get(k)));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        new Thread(() -> {
            try {
                Thread.sleep(300000);
                metricas.imprimirRelatorio();
                System.exit(0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
