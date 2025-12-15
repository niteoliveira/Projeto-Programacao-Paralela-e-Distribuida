import java.util.Random;

public class Filosofo implements Runnable{
    
    private final Random random = new Random();
    public String nome;
    private int id;
    public Integer contador=1;

    public final Garfo garfoEsquerda;
    public final Garfo garfoDireita;
    private final Mesa mesa;
    private final Metricas metricas;

    public Filosofo(int id, String nome, Garfo garfoEsquerda, Garfo garfoDireita, Mesa mesa, Metricas metricas){
        this.id = id;
        this.nome = nome;
        this.garfoEsquerda = garfoEsquerda;
        this.garfoDireita = garfoDireita;
        this.mesa = mesa;
        this.metricas = metricas;
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
            long tempoInicio = System.currentTimeMillis();
            metricas.registrarTentativa(id);
            
            System.out.println("[TENTANDO] " + nome + " esta tentando pegar o garfo esquerdo " + garfoEsquerda.nome);
            
            mesa.pegarGarfos(id);
            
            long tempoEspera = System.currentTimeMillis() - tempoInicio;
            metricas.registrarTempoEspera(id, tempoEspera);
            
            System.out.println("[PEGOU] " + nome + " pegou o garfo " + garfoEsquerda.nome);
            System.out.println("[TENTANDO] " + nome + " esta tentando pegar o garfo direito " + garfoDireita.nome);
            System.out.println("[PEGOU] " + nome + " pegou o garfo " + garfoDireita.nome);
            
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
        mesa.largarGarfos(id);
        System.out.println("[LARGOU] " + nome + " largou os garfos");
    }
    
    public void jantar(){
        pensar();
        pegarGarfos();
        comer();
        largarGarfos();

        metricas.registrarRefeicao(id);
        System.out.println("[CONCLUIDO] " + nome + " jantou pela " + contador + " vez\n");
        contador+=1;
    }

}
