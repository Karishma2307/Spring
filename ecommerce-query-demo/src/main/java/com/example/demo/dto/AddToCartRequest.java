package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddToCartRequest( @NotNull Long customerId,
	      @NotNull Long productId,
	      @Min(1) int quantity) {
	 
}