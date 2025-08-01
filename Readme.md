# Spring Boot com Apache Kafka e Avro

Este projeto demonstra como integrar **Spring Boot** com **Apache Kafka**, focando em tópicos, partições, chave da mensagem e estratégias para escalabilidade.

---

## 🧩 Conceitos Essenciais

### 🔹 Tópico (`Topic`)
Um canal lógico onde as mensagens são publicadas. Exemplo: `usuarios`.

### 🔹 Partições (`Partitions`)
Cada tópico pode ser dividido em partições, permitindo **paralelismo**. Mensagens com a mesma chave vão para a mesma partição, garantindo ordenação local.

### 🔹 Chave da Mensagem (`Key`)
A chave determina a **partição de destino**. É crucial para garantir **ordenamento** ou para distribuir dados entre partições.

---

## ✅ Benefícios de usar Partições

- Processamento paralelo com múltiplos consumidores.
- Balanceamento de carga automático entre instâncias.
- Escalabilidade horizontal fácil.

---

## 🛠️ Envio de Mensagens com KafkaTemplate

```java
@Autowired
private KafkaTemplate<String, UsuarioDTO> kafkaTemplate;

public void enviarMensagem(UsuarioDTO usuario) {
    kafkaTemplate.send("usuarios", usuario.getCpf(), usuario);
}
