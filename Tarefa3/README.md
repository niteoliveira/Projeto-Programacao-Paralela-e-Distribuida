# Tarefa 3: Solução com Semáforos

## Como Funciona

Usa um `Semaphore` do Java inicializado com 4 permissões para limitar o número de filósofos que podem tentar pegar garfos simultaneamente.

**Mecanismo**:
- Filósofo chama `semaforoMesa.acquire()` antes de pegar garfos
- Se já houver 4 filósofos, bloqueia até uma vaga abrir
- Pega os garfos (synchronized)
- Come
- Chama `semaforoMesa.release()` após largar os garfos

## Por Que Previne Deadlock

Com 5 filósofos e 5 garfos, deadlock ocorre quando todos pegam um garfo e esperam pelo próximo. Limitando a 4 filósofos na mesa:

- Sempre há pelo menos 1 filósofo fora da mesa
- Logo, sempre há pelo menos 1 garfo disponível
- Impossível ter espera circular completa

## Comparação com a Tarefa 2

**Tarefa 2** (Quebra de Simetria):
- Todos os 5 filósofos podem estar ativos
- Maior throughput
- Overhead mínimo
- Um filósofo pega garfos em ordem diferente

**Tarefa 3** (Semáforos):
- Apenas 4 filósofos ativos por vez
- Throughput um pouco menor
- Overhead do semáforo
- Controle mais explícito

## Vantagens e Desvantagens

**Vantagens**:
- ✓ Prevenção matemática de deadlock
- ✓ Implementação simples
- ✓ Fácil de entender
- ✓ Escalável (N-1 para N filósofos)

**Desvantagens**:
- ✗ Sempre 1 filósofo bloqueado desnecessariamente
- ✗ Throughput reduzido (~20%)
- ✗ Overhead do gerenciamento do semáforo

## Como Executar

```bash
javac *.java
java Main
```

Execute por pelo menos 2 minutos e use Ctrl+C para parar.
