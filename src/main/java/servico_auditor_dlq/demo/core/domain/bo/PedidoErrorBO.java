package servico_auditor_dlq.demo.core.domain.bo;

import java.time.LocalDate;
import java.util.List;

public class PedidoErrorBO {
    
    private String zipCode;
    private int customerId;
    private List<OrderItemBO> orderItems;
    private String origin;
    private LocalDate occurredAt;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemBO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemBO> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public LocalDate getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(LocalDate occurredAt) {
        this.occurredAt = occurredAt;
    }

    public int getQuantidadeTotalProdutos() {
        return orderItems.stream()
                .mapToInt(OrderItemBO::getAmount)
                .sum();
    }

    public String definirSeveridade() {

        int total = getQuantidadeTotalProdutos();

        if (total > 100) {
            return "High";
        }

        if (total >= 50) {
            return "Medium";
        }

        return "Low";
    }
}
