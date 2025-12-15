# Jantar dos Filósofos - Projeto de Sincronização

Este projeto implementa diferentes soluções para o clássico **Problema do Jantar dos Filósofos**, demonstrando conceitos fundamentais de programação concorrente, sincronização e prevenção de deadlock.

## Estrutura do Projeto

```
Jantar dos Filósofos/
├── Tarefa1/          # Implementação básica (com deadlock)
│   ├── Filosofo.java
│   ├── Garfo.java
│   ├── Main.java
│   └── README.md
├── Tarefa2/          # Solução: Quebra de Simetria
│   ├── Filosofo.java
│   ├── Garfo.java
│   ├── Main.java
│   └── README.md
├── Tarefa3/          # Solução: Semáforos
│   ├── Filosofo.java
│   ├── Garfo.java
│   ├── Main.java
│   └── README.md
├── Tarefa4/          # Solução: Monitores
│   ├── Filosofo.java
│   ├── Garfo.java
│   ├── Mesa.java
│   ├── Main.java
│   └── README.md
├── Tarefa5/          # Análise Comparativa com Métricas
│   ├── Filosofo.java
│   ├── Garfo.java
│   ├── Mesa.java
│   ├── Metricas.java
│   ├── Main.java
│   └── README.md
├── RELATORIO.md      # Relatório comparativo completo
└── README.md         # Este arquivo
```

## Descrição das Tarefas

### Tarefa 1 - Implementação Básica
Implementação que **pode causar deadlock**. Todos os filósofos tentam pegar o garfo esquerdo primeiro, depois o direito, levando a situações de travamento.

### Tarefa 2 - Quebra de Simetria
Previne deadlock fazendo com que um filósofo pegue os garfos em ordem inversa (direito, depois esquerdo), quebrando o ciclo de espera circular.

### Tarefa 3 - Semáforos
Usa `Semaphore` do Java para limitar a 4 o número de filósofos que podem tentar pegar garfos simultaneamente, garantindo que sempre haja pelo menos 1 garfo disponível.

### Tarefa 4 - Monitores
Implementa uma classe `Mesa` que atua como monitor, gerenciando o acesso aos garfos de forma centralizada usando `synchronized`, `wait()` e `notifyAll()` para aquisição atômica.

### Tarefa 5 - Análise Comparativa
Adiciona sistema de coleta de métricas (refeições, tempo de espera, distribuição) e executa testes de 5 minutos para análise comparativa das soluções.

## Como Compilar e Executar

### Requisitos
- Java JDK 17 ou superior (devido ao uso de `Random.nextLong(min, max)`)

### Compilação

Entre na pasta da tarefa desejada e compile:

```bash
cd "Tarefa1"   # ou Tarefa2, Tarefa3, Tarefa4, Tarefa5
javac *.java
```

### Execução

```bash
java Main
```

**Observações**:
- Tarefas 1-4: Executam indefinidamente. Use `Ctrl+C` para parar.
- Tarefa 5: Executa por exatamente 5 minutos e mostra relatório automaticamente.

### Exemplo de Execução Completa

```bash
# Tarefa 2 - Quebra de Simetria
cd "Tarefa2"
javac *.java
java Main
# Aguarde pelo menos 2 minutos e pressione Ctrl+C

# Tarefa 3 - Semáforos
cd "../Tarefa3"
javac *.java
java Main
# Aguarde pelo menos 2 minutos e pressione Ctrl+C

# Tarefa 4 - Monitores
cd "../Tarefa4"
javac *.java
java Main
# Aguarde pelo menos 2 minutos e pressione Ctrl+C

# Tarefa 5 - Análise (executa 5 minutos automaticamente)
cd "../Tarefa5"
javac *.java
java Main
# Aguarde 5 minutos - o programa encerra e mostra relatório
```

## Executando os Testes para Análise

Para coletar métricas completas conforme a Tarefa 5:

1. **Execute cada solução (Tarefas 2, 3 e 4) por 5 minutos**:
   - Compile e execute cada tarefa
   - Observe e anote o número de refeições de cada filósofo
   - Use Ctrl+C após 5 minutos

2. **Execute a Tarefa 5** que automatiza a coleta:
   - Ela executa por 5 minutos e gera relatório automático
   - Salve os resultados

3. **Preencha o RELATORIO.md** com os dados coletados

## Relatório Comparativo

O relatório comparativo completo está disponível em **[RELATORIO.md](RELATORIO.md)**.

O relatório inclui:
- Introdução ao problema
- Metodologia de testes
- Resultados detalhados com tabelas
- Análise comparativa das três soluções
- Recomendações por cenário de uso
- Conclusões e lições aprendidas

## Conceitos Demonstrados

Este projeto demonstra:

- **Programação Concorrente**: Uso de threads em Java
- **Sincronização**: `synchronized`, `wait()`, `notify()`
- **Semáforos**: Uso de `java.util.concurrent.Semaphore`
- **Monitores**: Padrão de design para sincronização
- **Deadlock**: Como ocorre e como prevenir
- **Starvation**: Conceito e técnicas de prevenção
- **Fairness**: Distribuição justa de recursos
- **Métricas**: Coleta e análise de desempenho

## Métricas Coletadas (Tarefa 5)

A Tarefa 5 coleta automaticamente:
- Número de refeições por filósofo
- Número de tentativas de pegar garfos
- Tempo médio de espera
- Desvio padrão das refeições
- Coeficiente de variação (medida de fairness)

## Comparação Rápida das Soluções

| Solução | Previne Deadlock | Fairness | Throughput | Complexidade |
|---------|-----------------|----------|------------|--------------|
| Tarefa 2 | ✓ | Baixa | Alto | Baixa |
| Tarefa 3 | ✓ | Média | Médio | Média |
| Tarefa 4 | ✓ | Alta | Variável | Alta |

## Autor

[Seu Nome]

## Licença

Este projeto foi desenvolvido para fins educacionais.

## Referências

- Dijkstra, E. W. (1965). "Cooperating Sequential Processes"
- Operating Systems: Three Easy Pieces - Remzi H. Arpaci-Dusseau
- Java Concurrency in Practice - Brian Goetz
