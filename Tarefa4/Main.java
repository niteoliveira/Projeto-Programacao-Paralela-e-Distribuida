import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        List<Garfo> garfos = new ArrayList<>();
        List<Filosofo> filosofos = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        
        int numFilosofos = 5;
        
        // Cria a Mesa (monitor) que gerencia o acesso aos garfos
        Mesa mesa = new Mesa(numFilosofos);

        // Cria os 5 garfos
        for (int i = 0; i < numFilosofos; i++) {
            garfos.add(new Garfo("G" + i));
        }
        
        // Cria os 5 filósofos, cada um com acesso à mesa
        for (int j = 0; j < numFilosofos; j++) {
            filosofos.add(new Filosofo(j, "F" + j, garfos.get(j), 
                                      garfos.get((j + 1) % numFilosofos), mesa));
        }

        // Cria as threads
        for (int k = 0; k < numFilosofos; k++) {
            threads.add(new Thread(filosofos.get(k)));
        }

        // Inicia todas as threads
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
