"# Tarefa 2: Solução com Quebra de Simetria

## Como Funciona

Um dos filósofos (F3) pega os garfos em ordem diferente: primeiro o direito, depois o esquerdo. Isso quebra a simetria e previne deadlock.

## Por Que Previne Deadlock

Deadlock requer que todos peguem recursos na mesma ordem. Como um filósofo pega em ordem inversa, não é possível formar um ciclo de espera circular.

## Como Executar

```bash
javac *.java
java Main
```

Execute por pelo menos 2 minutos e observe que não há deadlock."