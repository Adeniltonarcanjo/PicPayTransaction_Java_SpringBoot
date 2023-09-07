package com.adeniltonarcanjo.pipcpay.dtos;

import com.adeniltonarcanjo.pipcpay.domain.user.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName,String document, String email,String password, BigDecimal balance, UserType userType) {
}
