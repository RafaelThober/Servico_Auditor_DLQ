package dlq.servico_auditor_dlq.infrastructure.adapters.in.messaging.DLQ.mapper;


import java.time.Instant;

import org.springframework.stereotype.Component;

import dlq.servico_auditor_dlq.core.domain.bo.OrderItemBO;
import dlq.servico_auditor_dlq.core.domain.bo.PedidoErrorBO;
import dlq.servico_auditor_dlq.infrastructure.adapters.in.messaging.DLQ.dto.OrderItemDTO;
import dlq.servico_auditor_dlq.infrastructure.adapters.in.messaging.DLQ.dto.PedidoDTO;

@Component
public class PedidoMapper {

    public static PedidoErrorBO toBO(PedidoDTO dto) {

        PedidoErrorBO bo = new PedidoErrorBO();

        bo.setZipCode(dto.getZipCode());
        bo.setCustomerId(dto.getCustomerId());
        bo.setOrigin(dto.getOrigin());
        bo.setOccurredAt(Instant.parse(dto.getOccurredAt()));

        bo.setOrderItems(
            dto.getOrderItems()
                .stream()
                .map(PedidoMapper::toOrderItemBO)
                .toList()
        );

        return bo;
    }

    private static OrderItemBO toOrderItemBO(OrderItemDTO dto) {

        OrderItemBO bo = new OrderItemBO();

        bo.setSku(dto.getSku());
        bo.setAmount(dto.getAmount());

        return bo;
    }
}
