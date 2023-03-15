package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecreasingAmountCalculationServiceImpl implements DecreasingAmountCalculationService{
    private static final BigDecimal YEAR = BigDecimal.valueOf(12);

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal residualAmount = inputData.getAmount();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateDecreasingCapitalAmount(residualAmount,inputData.getMonthsDuration());
        BigDecimal rateAmount = capitalAmount.add(interestAmount);
        return new RateAmounts(rateAmount, interestAmount , capitalAmount, overpayment);
    }
@Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal residualAmount = previousRate.getMortagageResidual().getAmount();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateDecreasingCapitalAmount(inputData.getAmount() ,inputData.getMonthsDuration());
        BigDecimal rateAmount = capitalAmount.add(interestAmount);
        return new RateAmounts(rateAmount, interestAmount , capitalAmount,overpayment );

    }
    public BigDecimal calculateInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
        return residualAmount.multiply(interestPercent).divide(YEAR, 50, RoundingMode.HALF_UP);
    }


    private BigDecimal calculateDecreasingCapitalAmount(BigDecimal amount, BigDecimal monthsDuration) {
        return amount.divide(monthsDuration, 50, RoundingMode.HALF_UP);
    }



}
