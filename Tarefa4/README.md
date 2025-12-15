# Tarefa 4: Solução com Monitores

## Como Funciona

A classe `Mesa` atua como monitor centralizado que gerencia todos os garfos. Usa `synchronized`, `wait()` e `notifyAll()` para coordenar os filósofos.

**Mecanismo**:
- Filósofo chama `mesa.pegarGarfos(id)`
- Espera (`wait()`) até ambos os garfos estarem disponíveis
- Pega ambos atomicamente
- Come
- Chama `mesa.largarGarfos(id)` e notifica todos (`notifyAll()`)

## Por Que Previne Deadlock

1. **Aquisição Atômica**: Filósofo só pega garfos se AMBOS estiverem disponíveis
2. **Não há hold-and-wait**: Não pega um garfo e espera pelo outro
3. **Gerenciamento Centralizado**: Mesa tem visão global de todos os recursos

## Comparação com Tarefas Anteriores

| Característica | Tarefa 2 | Tarefa 3 | Tarefa 4 |
|---------------|----------|----------|----------|
| Previne Deadlock | ✓ | ✓ | ✓ |
| Implementação | Simples | Média | Média |
| Throughput | Alto | Médio | Médio |
| Fairness | Baixo | Médio | Médio |

**Tarefa 2**: Mais rápida, mas sem garantias de justiça  
**Tarefa 3**: Limita a 4 filósofos, reduz throughput  
**Tarefa 4**: Aquisição atômica, mais sincronização

## Vantagens e Desvantagens

**Vantagens**:
- ✓ Prevenção garantida de deadlock
- ✓ Código centralizado e organizado
- ✓ Fácil de entender e manter

**Desvantagens**:
- ✗ Overhead de sincronização (wait/notifyAll)
- ✗ Monitor pode ser gargalo
- ✗ Throughput menor que Tarefa 2

## Como Executar

```bash
javac *.java
java Main
```

Execute por pelo menos 2 minutos e use Ctrl+C para parar.
