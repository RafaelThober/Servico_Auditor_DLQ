package dlq.servico_auditor_dlq.application.ports.in;

import com.fasterxml.jackson.core.JsonProcessingException;

import dlq.servico_auditor_dlq.core.domain.bo.PedidoErrorBO;

public interface PedidoErrorServicePort {
  
  PedidoErrorBO processarPedidoError(PedidoErrorBO pedidoErrorBO) throws JsonProcessingException;
}