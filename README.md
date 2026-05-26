# Serviço auditor DLQ

O serviço auditor DLQ é responsável por consumir as mensagens que não conseguiram ser processadas pela fila principal e acabaram sendo enviadas para a Dead Letter Queue.

A ideia principal do serviço é evitar que essas mensagens fiquem paradas na DLQ até expirarem. Por isso, ele consome essas mensagens e salva suas informações em um banco de dados, permitindo que os erros sejam analisados depois.

As informações registradas são o payload original da mensagem, a data e hora do erro, o status, o nome da fila e a severidade do problema. Essa severidade é definida por uma regra de negócio baseada na quantidade total de produtos presentes na mensagem.

Dessa forma, o serviço funciona como uma auditoria das mensagens que falharam, facilitando a identificação e análise dos problemas ocorridos no processamento.

# Arquitetura hexagonal

A arquitetura escolhida para o projeto foi a arquitetura hexagonal. Essa escolha faz sentido porque o serviço trabalha diretamente com recursos externos, como a fila da AWS SQS e o banco de dados.

Com essa arquitetura, a regra de negócio fica separada das tecnologias usadas para entrada e saída de dados. Ou seja, a lógica principal do sistema não fica presa ao SQS, ao banco ou a qualquer detalhe específico de infraestrutura.

A organização do projeto segue essa ideia: o Listener fica responsável por consumir a mensagem da DLQ, os mappers fazem a conversão entre DTO, BO e Entity, a camada de negócio define a severidade do erro e, por fim, a persistência salva as informações no banco de dados.

Essa separação deixa o projeto mais organizado, mais fácil de manter e também mais simples de testar. Caso seja necessário trocar alguma integração no futuro, como a fila ou o banco, a regra de negócio principal continua praticamente intacta.

Por ser um serviço pequeno, mas com integração externa e regra própria de negócio, a arquitetura hexagonal ajuda a manter cada responsabilidade no seu lugar sem deixar o código acoplado demais.

# Fluxo de funcionamento

Uma mensagem JSON é enviada para a fila principal. Caso o serviço principal não consiga processar essa mensagem após três tentativas, conforme a configuração da AWS, ela é enviada para a DLQ.

A partir disso, o Listener do serviço auditor consome a mensagem da fila DLQ. Essa mensagem é recebida como DTO e depois convertida para BO, onde a regra de negócio é aplicada.

Nessa etapa, o serviço calcula a quantidade total de produtos da mensagem e define a severidade do erro. Caso a quantidade total seja maior que 100, a severidade será HIGH. Caso esteja entre 50 e 100, será MEDIUM. Caso seja menor que 50, será LOW.

Depois da definição da severidade, o BO é convertido para Entity e os dados são persistidos no banco. O registro salvo contém o identificador do erro, o nome da fila, o payload original, a data e hora, o status `PENDING_ANALYSIS` e a severidade calculada.

A mensagem só é removida da DLQ depois que o salvamento no banco é concluído com sucesso, garantindo que nenhuma informação importante seja perdida.