import java.util.Random;

public class Filosofo implements Runnable{
    
    private final Random random = new Random();
    public String nome;
    public Integer contador=1;

    public final Garfo garfoEsquerda;
    public final Garfo garfoDireita;

    public Filosofo(String nome, Garfo garfoEsquerda, Garfo garfoDireita){
        this.nome = nome;
        this.garfoEsquerda = garfoEsquerda;
        this.garfoDireita = garfoDireita;
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
        System.out.println("[TENTANDO] " + nome + " esta tentando pegar o garfo esquerdo " + garfoEsquerda.nome);

        synchronized (garfoEsquerda) {

            System.out.println("[PEGOU] " + nome + " pegou o garfo " + garfoEsquerda.nome);
            System.out.println("[TENTANDO] " + nome + " esta tentando pegar o garfo direito " + garfoDireita.nome);

            synchronized (garfoDireita) {

                System.out.println("[PEGOU] " + nome + " pegou o garfo " + garfoDireita.nome);
            }
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