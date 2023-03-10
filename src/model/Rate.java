package model;

import java.math.BigDecimal;

public class Rate {
    private final BigDecimal rateNumber;

    private final TimePoint timePoint;

    private final RateAmounts rateAmounts;

    private final MortagageResidual mortagageResidual;

    public Rate(BigDecimal rateNumber, TimePoint timePoint, RateAmounts rateAmounts, MortagageResidual mortagageResidual) {
        this.rateNumber = rateNumber;
        this.timePoint = timePoint;
        this.rateAmounts = rateAmounts;
        this.mortagageResidual = mortagageResidual;
    }

    public BigDecimal getRateNumber() {
        return rateNumber;
    }

    public TimePoint getTimePoint() {
        return timePoint;
    }

    public RateAmounts getRateAmounts() {
        return rateAmounts;
    }

    public MortagageResidual getMortagageResidual() {
        return mortagageResidual;
    }
}
