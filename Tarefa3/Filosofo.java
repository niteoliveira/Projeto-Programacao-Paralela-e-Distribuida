import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable{
    
    private final Random random = new Random();
    public String nome;
    public Integer contador=1;

    public final Garfo garfoEsquerda;
    public final Garfo garfoDireita;
    private final Semaphore semaforoMesa;

    public Filosofo(String nome, Garfo garfoEsquerda, Garfo garfoDireita, Semaphore semaforoMesa){
        this.nome = nome;
        this.garfoEsquerda = garfoEsquerda;
        this.garfoDireita = garfoDireita;
        this.semaforoMesa = semaforoMesa;
    }

    @Override
    public void run(){
        while (true) { 
            jantar();
        }
        
    }

    public void pensar(){
        System.out.println("[PENSANDO] " + nome + " esta pensando...");

        try {
            Thread.sleep(this.random.nextLong(1000, 3000));
        } catch (Exception e) {
            
        }
        
    }
    
    public void pegarGarfos(){
        try {
            semaforoMesa.acquire();
            
            System.out.println("[TENTANDO] " + nome + " esta tentando pegar o garfo esquerdo " + garfoEsquerda.nome);

            synchronized (garfoEsquerda) {

                System.out.println("[PEGOU] " + nome + " pegou o garfo " + garfoEsquerda.nome);
                System.out.println("[TENTANDO] " + nome + " esta tentando pegar o garfo direito " + garfoDireita.nome);

                synchronized (garfoDireita) {

                    System.out.println("[PEGOU] " + nome + " pegou o garfo " + garfoDireita.nome);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
        
    public void comer(){
        System.out.println("[COMENDO] " + nome + " esta comendo");
        try {
            Thread.sleep(this.random.nextLong(1000, 3000));
        } catch (Exception e) {
            
        }
    }
    
    public void largarGarfos(){
        System.out.println("[LARGOU] " + nome + " largou os garfos");
        semaforoMesa.release();
    }
    
    public void jantar(){
        pensar();
        pegarGarfos();
        comer();
        largarGarfos();

        System.out.println("[CONCLUIDO] " + nome + " jantou pela " + contador + " vez\n");
        contador+=1;
    }

}
