package com.techelevator.tenmo.service;

import java.math.BigDecimal;

public interface TransferServiceDao {
boolean amountGreaterThanZero (BigDecimal amount);

boolean amountLessThanBalance (BigDecimal amount, BigDecimal balance);
}
