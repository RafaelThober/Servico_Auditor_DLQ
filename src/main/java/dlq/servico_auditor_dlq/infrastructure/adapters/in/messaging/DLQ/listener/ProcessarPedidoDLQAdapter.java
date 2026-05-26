package dlq.servico_auditor_dlq.infrastructure.adapters.in.messaging.DLQ.listener;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import dlq.servico_auditor_dlq.application.ports.in.PedidoErrorServicePort;
import dlq.servico_auditor_dlq.core.domain.bo.PedidoErrorBO;
import dlq.servico_auditor_dlq.infrastructure.adapters.in.messaging.DLQ.dto.PedidoDTO;
import dlq.servico_auditor_dlq.infrastructure.adapters.in.messaging.DLQ.mapper.PedidoMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;

@Component
public class ProcessarPedidoDLQAdapter {

  private final PedidoErrorServicePort pedidoErrorService;

  public ProcessarPedidoDLQAdapter(PedidoErrorServicePort pedidoErrorService) {
    this.pedidoErrorService = pedidoErrorService;
  }

  @SqsListener("${queue.order-events}")
  public void listen(PedidoDTO pedidoDTO) throws JsonProcessingException {
    System.out.println("Mensagem recebida");
    PedidoErrorBO pedidoErrorBO = PedidoMapper.toBO(pedidoDTO);

    pedidoErrorService.processarPedidoError(pedidoErrorBO);

  }
}
