import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        List<Garfo> garfos = new ArrayList<>();
        List<Filosofo> filosofos = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        
        int numFilosofos = 5;
        
        Mesa mesa = new Mesa(numFilosofos);

        for (int i = 0; i < numFilosofos; i++) {
            garfos.add(new Garfo("G" + i));
        }
        
        for (int j = 0; j < numFilosofos; j++) {
            filosofos.add(new Filosofo(j, "F" + j, garfos.get(j), 
                                      garfos.get((j + 1) % numFilosofos), mesa));
        }

        for (int k = 0; k < numFilosofos; k++) {
            threads.add(new Thread(filosofos.get(k)));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
