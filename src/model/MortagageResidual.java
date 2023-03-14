package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MortagageResidual {

    private final BigDecimal amount;
    private final BigDecimal duration;

    public MortagageResidual(BigDecimal amount, BigDecimal duration) {
        this.amount = amount;
        this.duration = duration;
    }

    public BigDecimal getAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDuration() {
        return duration;
    }
}
