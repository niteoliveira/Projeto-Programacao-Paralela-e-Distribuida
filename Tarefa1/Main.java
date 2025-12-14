
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        List<Garfo> garfos = new ArrayList<>();
        List<Filosofo> filosofos = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();


        for (int i=0;i<5;i++) {
            garfos.add(new Garfo("G"+i));
        }
        

        
        for (int j = 0; j < 5; j++) {
            filosofos.add(new Filosofo("F" + j, garfos.get(j), garfos.get((j + 1) % 5)));
        }

        for (int k=0; k < 5; k++) {
            threads.add(new Thread(filosofos.get(k)));
        }
        
        threads.get(0).start();
        threads.get(1).start();
        threads.get(2).start();
        threads.get(3).start();
        threads.get(4).start();
        
        

        
    }
}