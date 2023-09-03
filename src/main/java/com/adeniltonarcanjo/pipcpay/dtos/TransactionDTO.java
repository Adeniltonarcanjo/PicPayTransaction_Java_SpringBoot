package com.adeniltonarcanjo.pipcpay.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, long senderId, Long receiverId) {
}
