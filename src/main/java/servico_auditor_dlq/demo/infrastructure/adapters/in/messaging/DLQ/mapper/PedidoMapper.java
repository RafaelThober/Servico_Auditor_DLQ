package servico_auditor_dlq.demo.infrastructure.adapters.in.messaging.DLQ.mapper;

import org.springframework.stereotype.Component;

import servico_auditor_dlq.demo.core.domain.bo.OrderItemBO;
import servico_auditor_dlq.demo.core.domain.bo.PedidoErrorBO;
import servico_auditor_dlq.demo.infrastructure.adapters.in.messaging.DLQ.dto.OrderItemDTO;
import servico_auditor_dlq.demo.infrastructure.adapters.in.messaging.DLQ.dto.PedidoDTO;

@Component
public class PedidoMapper {

    public PedidoErrorBO toBO(PedidoDTO dto) {

        PedidoErrorBO bo = new PedidoErrorBO();

        bo.setZipCode(dto.getZipCode());
        bo.setCustomerId(dto.getCustomerId());
        bo.setOrigin(dto.getOrigin());
        bo.setOccurredAt(dto.getOccurredAt());

        bo.setOrderItems(
            dto.getOrderItems()
                .stream()
                .map(this::toOrderItemBO)
                .toList()
        );

        return bo;
    }

    private OrderItemBO toOrderItemBO(OrderItemDTO dto) {

        OrderItemBO bo = new OrderItemBO();

        bo.setSku(dto.getSku());
        bo.setAmount(dto.getAmount());

        return bo;
    }
}