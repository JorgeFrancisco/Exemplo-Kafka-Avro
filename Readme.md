# Spring Boot com Apache Kafka e Avro

Este projeto demonstra como integrar **Spring Boot** com **Apache Kafka e Avro**, focando no envio de um payload leve, e de rápido processamento.

---

🔹 O que é Avro?

Apache Avro é:

Um formato compacto, rápido e binário para serializar dados.

Usado para definir estruturas de dados (schemas).

Totalmente integrado com o Apache Kafka, Apache Spark, Apache Flink, Hadoop, entre outros.

Independente de linguagem: os dados Avro podem ser lidos por aplicações escritas em Java, Python, C++, etc.

🔹 Principais Características

| Característica                | Descrição                                                                     |
| ----------------------------- | ----------------------------------------------------------------------------- |
| **Schema-based**              | Usa um arquivo JSON para definir o esquema (campos, tipos, etc).              |
| **Self-describing**           | Os arquivos Avro geralmente incluem o esquema junto com os dados.             |
| **Compacto e eficiente**      | Serializa em binário, ideal para tráfego e armazenamento.                     |
| **Compatibilidade de versão** | Permite evolução de schemas (adição/remover campos) sem quebrar consumidores. |
| **Leve**                      | Não exige uma estrutura pesada como XML ou JSON.                              |

🔹 Onde o Avro é usado?

Kafka: para definir mensagens padronizadas e compactas com uso do Schema Registry.

Hadoop: como formato de armazenamento (em vez de JSON/CSV).

Data Lakes: leitura e escrita eficiente de grandes volumes de dados.

ETLs e pipelines de dados: comunicação entre microserviços.

🔹 Avro vs Outros Formatos

| Formato  | Tipo    | Vantagem                    | Desvantagem                     |
| -------- | ------- | --------------------------- | ------------------------------- |
| JSON     | Texto   | Fácil de ler                | Muito maior em tamanho          |
| XML      | Texto   | Estruturado, mas pesado     | Verboso e lento                 |
| Protobuf | Binário | Compacto, rápido            | Precisa do schema externo       |
| **Avro** | Binário | Compacto, inclui o schema   | Difícil de ler manualmente      |
| Parquet  | Colunar | Ideal para leitura seletiva | Mais pesado para escrita rápida |

✅ Quando o schema é necessário?

Em uma aplicação Spring Boot com Kafka e Avro:

O Avro exige o schema para:

Serializar um objeto Java para binário Avro.

Desserializar binário Avro para objeto Java.

O schema pode ser:

Incluído no próprio arquivo/mensagem (self-contained).

Ou armazenado separadamente em um Schema Registry (como o do Confluent).

📊 Benchmark típico (médio estimado)

| Ação                        | Avro (tempo) | Protobuf (tempo) |
| --------------------------- | ------------ | ---------------- |
| Serializar 1.000 objetos    | \~15 ms      | \~8 ms           |
| Desserializar 1.000 objetos | \~12 ms      | \~6 ms           |
| Tamanho do payload          | \~1.5x maior | menor            |

🧠 Qual escolher?

| Caso de uso                              | Melhor escolha |
| ---------------------------------------- | -------------- |
| Kafka + Schema Registry                  | ✅ **Avro**     |
| Microserviços que exigem performance     | ✅ **Protobuf** |
| Comunicação com dispositivos IoT         | ✅ **Protobuf** |
| Facilidade de schema + evolução dinâmica | ✅ **Avro**     |
| Integração com Spark, Hive, Hadoop       | ✅ **Avro**     |

✅ Principais vantagens do fluxo: JSON → Avro → Kafka

| Etapa             | Por quê usar                                                 |
| ----------------- | ------------------------------------------------------------ |
| **JSON na API**   | Porque é o formato mais comum e compatível com clientes REST |
| **Avro no Kafka** | Porque é binário, compacto, schema-based e eficiente         |

🔍 Vantagens práticas ao usar Avro no Kafka

✅ 1. Redução de Tamanho (Payload)
JSON é verbooso (chaves e valores em texto).

Avro é binário e muito mais compacto (economia de até 50–80% no payload).

Ideal para enviar milhares de registros com menos uso de rede e disco.

➡️ Ganho direto em latência e throughput do Kafka.

✅ 2. Performance de Serialização/Desserialização
Avro é muito mais rápido que JSON em (de)serialização, especialmente em grande volume.

Isso reduz o custo de CPU e melhora o desempenho dos consumidores.

✅ 3. Schema Evolvível
Avro tem suporte nativo à evolução de schema (adição, remoção, alteração de campos).

Kafka com Avro e Schema Registry permite manter compatibilidade entre versões sem quebrar consumidores.

➡️ Facilita evolução da API e dos dados no pipeline, com menos medo de "quebrar tudo".

✅ 4. Validação automática com Schema Registry
Se você usa Avro com o Confluent Schema Registry, os dados só são aceitos no Kafka se seguirem o schema definido.

Isso previne erro de contrato entre produtor e consumidor.

✅ 5. Compatibilidade multi-linguagem
Você pode produzir em Java e consumir em Python, Node, Go... sem reinventar a roda.

Basta compartilhar o schema Avro.

✅ 6. Interoperabilidade com ferramentas de Big Data
Avro é compatível com Apache Hive, Spark, Flink, Kafka Connect, Hadoop etc.

Isso permite usar os mesmos dados para analytics, batch e tempo real.

🚫 Desvantagens a considerar

| Desvantagem                           | Mitigação possível                                         |
| ------------------------------------- | ---------------------------------------------------------- |
| Avro é binário → não legível fácil    | Use ferramentas como Confluent UI ou avro-tools para debug |
| Schema Registry pode ser novo p/ time | Pode começar com schema embutido no início                 |
| JSON para Avro exige mapeamento       | Use MapStruct, conversores automáticos                     |

🧠 Conclusão
Você combina o melhor dos dois mundos:

JSON: fácil de integrar com frontends e clientes REST.

Avro: rápido, compacto, seguro e preparado para evolução.

📌 Sim, vale muito a pena se sua API recebe grandes volumes ou você planeja escalar com Kafka, microserviços ou data pipelines

📊 Benchmark: JSON vs Avro com 10.000 registros

| Métrica                   | JSON                          | Avro                        |
| ------------------------- | ----------------------------- | --------------------------- |
| **Tamanho do payload**    | \~1.8 MB                      | \~800 KB (\~55% menor)      |
| **Tempo de serialização** | \~50–60 ms (com `json.dumps`) | \~20–30 ms (com `fastavro`) |
| **Formato**               | Texto legível                 | Binário (não legível)       |
| **Validação de schema**   | ❌ Não                         | ✅ Sim (com schema registry) |

---

## 🛠️ Há na pasta resources o docker compose, para executar o Kafka e o RedPanda localmente. RedPanda é uma interface gráfica para manipulçao dos tópicos. 
Há também outro arquivo, de operações dentro do container para manipular os tópicos. E mais outro arquivo para operações no Schema Registry

---

### Para gerar os artefatos do avsc e do mapper, etc:
mvn clean generate-resources generate-sources compile
