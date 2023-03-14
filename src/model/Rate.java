package model;

import java.math.BigDecimal;

public class Rate {
    private final BigDecimal rateNumber;

    private final TimePoint timePoint;

    private final RateAmounts rateAmounts;

    private final MortagageResidual mortagageResidual;
    private final MortgageReference mortgageReference;

    public Rate(BigDecimal rateNumber,
                TimePoint timePoint,
                RateAmounts rateAmounts,
                MortagageResidual mortagageResidual,
                MortgageReference mortgageReference) {
        this.rateNumber = rateNumber;
        this.timePoint = timePoint;
        this.rateAmounts = rateAmounts;
        this.mortagageResidual = mortagageResidual;
        this.mortgageReference = mortgageReference;
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

    public MortgageReference getMortgageReference() {
        return mortgageReference;
    }
}
