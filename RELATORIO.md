# Relatório Comparativo - Jantar dos Filósofos

## Introdução

O **Problema do Jantar dos Filósofos**, proposto por Dijkstra em 1965, é um problema clássico de sincronização em sistemas concorrentes. Cinco filósofos sentam-se em uma mesa redonda, cada um precisando de dois garfos (um à esquerda e outro à direita) para comer. O desafio é coordenar o acesso aos garfos compartilhados evitando **deadlock** (travamento) e **starvation** (inanição).

Este problema ilustra os desafios fundamentais da programação concorrente:
- **Exclusão mútua**: Apenas um processo pode usar um recurso por vez
- **Sincronização**: Coordenação entre processos concorrentes
- **Prevenção de deadlock**: Evitar ciclos de espera
- **Fairness**: Garantir acesso justo aos recursos

## Metodologia

### Implementações Testadas

Foram implementadas e testadas três soluções diferentes:

1. **Tarefa 2 - Quebra de Simetria**: Um filósofo pega os garfos em ordem inversa
2. **Tarefa 3 - Semáforos**: Limita a 4 filósofos simultâneos usando `Semaphore`
3. **Tarefa 4 - Monitores**: Aquisição atômica de garfos com `synchronized` e `wait()`/`notifyAll()`

### Ambiente de Testes

- **Linguagem**: Java
- **Plataforma**: [Especifique: Windows/Linux/Mac]
- **Processador**: [Especifique]
- **Número de Filósofos**: 5
- **Número de Garfos**: 5
- **Tempo de Pensamento**: 1-3 segundos (aleatório)
- **Tempo de Comer**: 1-3 segundos (aleatório)
- **Duração dos Testes**: 5 minutos por solução

### Métricas Coletadas

Para cada solução, foram coletadas as seguintes métricas:

1. **Número total de refeições** por filósofo
2. **Tempo médio de espera** entre tentativa e aquisição dos garfos
3. **Distribuição de justiça**: Coeficiente de variação (CV) das refeições
4. **Throughput total**: Total de refeições completadas

## Resultados

### Tarefa 2 - Quebra de Simetria

```
Filosofo | Refeições | % do Total
---------|-----------|------------
F0       | 72        | 19.35%
F1       | 75        | 20.16%
F2       | 74        | 19.89%
F3       | 77        | 20.70%
F4       | 74        | 19.89%
---------|-----------|------------
TOTAL    | 372       | 100%

Média: 74.40
Desvio Padrão: 1.62
Coeficiente de Variação: 2.18%
```

### Tarefa 3 - Semáforos

```
Filosofo | Refeições | % do Total
---------|-----------|------------
F0       | 76        | 19.84%
F1       | 76        | 19.84%
F2       | 77        | 20.10%
F3       | 75        | 19.58%
F4       | 79        | 20.63%
---------|-----------|------------
TOTAL    | 383       | 100%

Média: 76.60
Desvio Padrão: 1.36
Coeficiente de Variação: 1.77%
```

### Tarefa 4 - Monitores

```% do Total
---------|-----------|------------
F0       | 56        | 19.86%
F1       | 57        | 20.21%
F2       | 55        | 19.50%
F3       | 57        | 20.21%
F4       | 57        | 20.21%
---------|-----------|------------
TOTAL    | 282       | 100%

Média: 56.40
Desvio Padrão: 0.80
Coeficiente de Variação: 1.42
Coeficiente de Variação: [PREENCHER]%
```

### Comparação Geral

| Métrica | Tarefa 2 | Tarefa 3 | Tarefa 4 |
|---------|----------|--372 | **383** 🏆 | 282 |
| **Média por Filósofo** | 74.40 | **76.60** 🏆 | 56.40 |
| **Desvio Padrão** | 1.62 | 1.36 | **0.80** 🏆 |
| **Coef. Variação (%)** | 2.18% | 1.77% | **1.42%** 🏆 |
| **Throughput Relativo** | 97% | 100% | 74%
| **Tempo Médio Espera (ms)** | [PREENCHER] | [PREENCHER] | [PREENCHER] |

## Análise

### 1. Prevenção de Deadlock

| Solução | Previne Deadlock? | Como? |
|---------|------------------|-------|
| **Tarefa 2** | ✓ Sim | Quebra a simetria - um filósofo pega garfos em ordem diferente, impossibilitando ciclo de espera circular |
| **Tarefa 3** | ✓ Sim | Limita a 4 filósofos ativos - sempre há pelo menos 1 garfo disponível |
| **Tarefa 4** | ✓ Sim | Aquisição atômica - só pega ambos os garfos se disponíveis simultaneamente |

**Conclusão**: Todas as três soluções previnem deadlock efetivamente, mas por mecanismos diferentes.

### 2. Prevenção de Starvation

| Solução | Previne Starvation? | Observações |
|---------|---------------------|-------------|
| **Tarefa 2** | ✗ Não garante | Aleatoriedade pode causar desbalanceamento. CV esperado: 15-30% |
| **Tarefa 3** | ~ Parcial | Semáforo FIFO ajuda, mas aleatoriedade ainda impacta. CV esperado: 10-20% |
| **Tarefa 4** | ✓ Melhor | `wait()` com notifyAll oferece distribuição mais justa. CV esperado: 5-15% |

**Conclusão**: Monitor (Tarefa 4) oferece melhor fairness, seguido por Semáforos (Tarefa 3) e Quebra de Simetria (Tarefa 2).

### 3. Performance / Throughput

**Análise Teórica**:

- **Tarefa 2**: Máximo throughput teórico - todos os 5 filósofos podem competir pelos garfos
- **Tarefa 3**: ~80% do throughput da Tarefa 2 - sempre 1 filósofo bloqueado pelo semáforo
- **Tarefa 4**: Variável - depende da contenção no monitor
Observados** (surpreendentes!):
- **Tarefa 3 teve o MAIOR throughput** (383 refeições) - contra-intuitivo!
- Tarefa 2 teve throughput levemente menor (372 refeições, -3%)
- **Tarefa 4 teve throughput significativamente menor** (282 refeições, -26%)
- O overhead do monitor com `wait()/notifyAll()` impactou fortemente a performance
- Tarefa 4 pode variar dependendo da implementação da JVM

### 4. Complexidade de Implementação

| Aspecto | Tarefa 2 | Tarefa 3 | Tarefa 4 |
|---------|----------|----------|----------|
| **Linhas de código** | ~80 | ~100 | ~120 |
| **Conceitos usados** | synchronized básico | Semaphore + synchronized | Monitor, wait/notify |
| **Facilidade de entender** | ★★★★★ | ★★★★☆ | ★★★☆☆ |
| **Facilidade de manter** | ★★★★★ | ★★★★☆ | ★★★☆☆ |
| **Propensão a bugs** | Baixa | Média | Média-Alta |

**Conclusão**: Tarefa 2 é mais simples e direta. Tarefa 4 é mais complexa mas oferece melhor controle.

### 5. Uso de Recursos

| Recurso | Tarefa 2 | Tarefa 3 | Tarefa 4 |
|---------|----------|----------|----------|
| **Threads bloqueadas** | Variável | Mínimo 1 | Variável |
| **Overhead de sincronização** | Baixo | Médio | Alto |
| **Context switches** | Médio | Alto | Muito Alto |
| **Uso de memória** | Baixo | Baixo | Médio |

**Análise**:
- Tarefa 2 tem menor overhead - apenas locks nos objetos Garfo
- Tarefa 3 adiciona overhead do Semaphore mas é previsível
- Tarefa 4 tem mais overhead devido a `wait()/notifyAll()` frequentes

### 6. Análise do Coeficiente de Variação

O **Coeficiente de Variação (CV)** mede a justiça da distribuição:
- **CV < 10%**: Distribuição muito justa
- **CV 10-20%**: Distribuição razoável
- **CV > 20%**: Distribuição desigual (possível starvation)
 Obtidos**:
- **Tarefa 2**: CV = 2.18% - Distribuição excelente, apenas 5 refeições de diferença entre min/max
- **Tarefa 3**: CV = 1.77% - Distribuição excelente, apenas 4 refeições de diferença
- **Tarefa 4**: CV = 1.42% - **Melhor distribuição**, apenas 2 refeições de diferença

**Conclusão**: Todas as três soluções apresentaram distribuição **muito justa** (CV < 3%), com a Tarefa 4 sendo ligeiramente superior em fairness.
- Tarefa 4: CV = [PREENCHER]% - [Análise]

## Conclusão

### Ranking por Critério
3 (383) | Tarefa 2 (372) | Tarefa 4 (282) |
| **Fairness** | Tarefa 4 (1.42%) | Tarefa 3 (1.77%) | Tarefa 2 (2.18%) |
| **Simplicidade** | Tarefa 2 | Tarefa 3 | Tarefa 4 |
| **Baixo Overhead** | Tarefa 2 | Tarefa 3 | Tarefa 4 |
| **Distribuição Justa4 | Tarefa 3 | Tarefa 2 |
| **Simplicidade** | Tarefa 2 | Tarefa 3 | Tarefa 4 |
| **Baixo Overhead** | Tarefa 2 | Tarefa 3 | Tarefa 4 |
| **Prevenção Starvation** | Tarefa 4 | Tarefa 3 | Tarefa 2 |

### Recomendações por Cenário

#### Cenário 1: Sistema de Alto Throughput
**Exemplo**: Servidor web processando requisições

**Solução Recomendada**: **Tarefa 2 (Quebra de Simetria)**

**Justificativa**:
- Máximo throughput possível
- Overhead mínimo
- Fairness não é crítica (requisições são independentes)
- Simplicidade facilita manutenção

#### Cenário 2: Sistema Multi-Usuário com SLA
**Exemplo**: Sistema bancário, aplicação com garantias de tempo de resposta

**Solução Recomendada**: **Tarefa 3 (Semáforos)**

**Justificativa**:
- Bom balanço entre throughput e fairness
- Comportamento previsível (sempre 4 ativos)
- Implementação robusta e confiável
- Overhead aceitável

#### Cenário 3: Sistema Crítico com Requisitos de Fairness
**Exemplo**: Escalonador de processos, sistema de tempo compartilhado

**Solução Recomendada**: **Tarefa 4 (Monitores)**

**Justificativa**:
- Melhor distribuição entre participantes
- Prevenção efetiva de starvation
- Controle fino sobre sincronização
- Fairness é prioridade sobre throughput

#### Cenário 4: Sistema Educacional/Prototipagem
**Solução Recomendada**: **Tarefa 2 (Quebra de Simetria)**

**Justificativa**:
- Mais fácil de entender e ensinar
- Código mais simples
- Menos propensa a bugs
- Boa introdução aos conceitos

### Considerações Finais

Não existe uma solução "melhor" universal - a escolha depende dos requisitos:

- **Priorize Tarefa 2** quando: throughput é crítico, simplicidade importa, fairness não é requisito
- **Priorize Tarefa 3** quando: precisa de comportamento previsível, quer balanço entre simplicidade e fairness
- **Priorize Tarefa 4** quando: fairness é essencial, pode aceitar overhead adicional, precisa de controle fino

Todas as três soluções previnem deadlock efetivamente. A diferença principal está no trade-off entre **performance** (Tarefa 2), **previsibilidade** (Tarefa 3) e **fairness** (Tarefa 4).

### Lições Aprendidas

1. **Deadlock pode ser prevenido de várias formas**: quebra de simetria, limitação de recursos, ou aquisição atômica
2. **Fairness tem custo**: soluções mais justas geralmente têm overhead maior
3. **Simplicidade tem valor**: código mais simples é mais fácil de manter e menos propenso a bugs
4. **Context matters**: a solução ideal depende totalmente do contexto de uso
## Observações Finais dos Testes

Os resultados práticos revelaram insights importantes:

1. **Semáforos surpreenderam**: A Tarefa 3 teve o melhor throughput (383), contrariando a expectativa teórica de que limitar a 4 filósofos reduziria performance. Possíveis razões:
   - Menos contenção nos locks individuais dos garfos
   - Melhor distribuição de acesso aos recursos
   - JVM otimizou melhor o padrão de acesso previsível

2. **Monitores pagaram o preço**: A Tarefa 4 teve 26% menos throughput devido ao overhead de `wait()`/`notifyAll()`. Cada liberação de garfo acorda TODOS os filósofos, gerando muito context switching.

3. **Fairness vs Performance**: A correlação esperada se confirmou - melhor fairness (Tarefa 4) veio com custo de performance. Porém, todas as três soluções tiveram CV < 3%, mostrando que fairness aceitável é possível sem grande perda.

4. **Aleatoriedade importa**: Com tempos aleatórios de 1-3s para pensar/comer, pequenas variações podem ter grande impacto nos resultados.

---

**Data do Relatório**: Dezembro 15, 2025  
**Duração dos Testes**: 5 minutos por solução  
**Java Version**: JDK 17+

**Executado por**: Leonardo de Oliveira 

