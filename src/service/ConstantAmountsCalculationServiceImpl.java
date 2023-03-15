package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConstantAmountsCalculationServiceImpl implements ConstantAmountsCalculationService{
    private static final BigDecimal YEAR = BigDecimal.valueOf(12);
    private BigDecimal residualDuration;

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        BigDecimal q = calculateQ(inputData.getInterestPercent());
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal referenceDuration = inputData.getMonthsDuration();
        BigDecimal referenceAmount = inputData.getAmount();
        BigDecimal residualAmount = inputData.getAmount();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal residualDuration = inputData.getMonthsDuration();
        BigDecimal rateAmount = calculateConstantRateAmount(q, referenceDuration, residualDuration, interestAmount, referenceDuration);
        BigDecimal capitalAmount = calculateConstantCapitalAmount(rateAmount,interestAmount);

        return new RateAmounts(rateAmount, interestAmount , capitalAmount,  overpayment);
    }

@Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
    BigDecimal interestPercent = inputData.getInterestPercent();
    BigDecimal residualAmount = previousRate.getMortagageResidual().getAmount();
    BigDecimal q = calculateQ(interestPercent);

    BigDecimal referenceDuration = inputData.getMonthsDuration();
    BigDecimal referenceAmount = inputData.getAmount();


    BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
    BigDecimal rateAmount = calculateConstantRateAmount(q, referenceAmount, referenceDuration, interestAmount, residualAmount);
        BigDecimal capitalAmount = calculateConstantCapitalAmount(rateAmount,interestAmount);

        return new RateAmounts(rateAmount, interestAmount , capitalAmount,overpayment);
    }
    private BigDecimal calculateQ(BigDecimal interestPercent) {
        return interestPercent.divide(YEAR, 10, RoundingMode.HALF_UP).add(BigDecimal.ONE);
    }
    public BigDecimal calculateInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
        return residualAmount.multiply(interestPercent).divide(YEAR, 50, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateConstantRateAmount(
            BigDecimal q,
            BigDecimal amount,
            BigDecimal monthsDuration,
            BigDecimal interestAmount,
            BigDecimal residualAmount
            ) {
        BigDecimal rateAmount =  amount
                .multiply(q.pow(monthsDuration.intValue()))
                .multiply(q.subtract(BigDecimal.ONE))
                .divide(q.pow(monthsDuration.intValue()).subtract(BigDecimal.ONE), 50, RoundingMode.HALF_UP);

        return compareWithResidual(rateAmount, interestAmount, residualAmount);
    }

    private BigDecimal compareWithResidual(BigDecimal rateAmount, BigDecimal interestAmount, BigDecimal residualAmount) {
        if (rateAmount.subtract(interestAmount).compareTo(residualAmount)>= 0){
            return residualAmount.add(interestAmount);
        }
        return residualAmount;


    }

    private BigDecimal calculateConstantCapitalAmount(BigDecimal rateAmount, BigDecimal interestAmount) {
        return rateAmount.subtract(interestAmount);


    }
}
