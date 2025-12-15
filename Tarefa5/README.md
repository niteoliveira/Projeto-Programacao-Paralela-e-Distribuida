# Tarefa 5: Análise Comparativa com Coleta de Métricas

## Como Funciona

Esta implementação usa o monitor da Tarefa 4 (Mesa) e adiciona um sistema de coleta de métricas através da classe `Metricas`.

**Métricas Coletadas**:
- Número de refeições por filósofo
- Número de tentativas de pegar garfos
- Tempo médio de espera
- Desvio padrão e coeficiente de variação

**Execução**:
- Roda automaticamente por 5 minutos
- Imprime relatório completo ao final
- Encerra automaticamente

## Métricas Implementadas

1. **Refeições**: Contador atômico incrementado a cada refeição completa
2. **Tentativas**: Incrementado cada vez que tenta pegar garfos
3. **Tempo de Espera**: Medido desde a tentativa até conseguir ambos os garfos
4. **Coeficiente de Variação**: Mede a distribuição justa entre filósofos

## Como Executar

```bash
javac *.java
java Main
```

O programa executará por exatamente 5 minutos e mostrará o relatório final.

## Exemplo de Saída

```
========================================
RELATORIO DE METRICAS - 300 segundos
========================================
F0:
  Refeicoes: 42
  Tentativas: 42
  Tempo medio de espera: 125.50 ms
...

--- ESTATISTICAS GERAIS ---
Total de refeicoes: 210
Media por filosofo: 42.00
Minimo: 40
Maximo: 44
Desvio padrao: 1.41
Coeficiente de variacao: 3.36%
========================================
```

Consulte o arquivo [RELATORIO.md](../RELATORIO.md) para análise comparativa completa.
