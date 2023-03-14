package service;

import model.InputData;
import model.MortagageResidual;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;

public class ResidualCalculationServiceImpl implements ResidualCalculationService {




    @Override
    public MortagageResidual calculate(RateAmounts rateAmounts, InputData inputData) {
        BigDecimal residualAmount = calculateResidualAmount(rateAmounts, inputData.getAmount());
        BigDecimal residualDuration=inputData.getMonthsDuration().subtract(BigDecimal.ONE);



        return new MortagageResidual(residualAmount, residualDuration);
    }

    @Override
    public MortagageResidual calculate(RateAmounts rateAmounts, Rate previousRate) {
        MortagageResidual residual = previousRate.getMortagageResidual();
        BigDecimal residualAmount = calculateResidualAmount(rateAmounts, residual.getAmount());
        BigDecimal residualDuration =  residual.getDuration().subtract(BigDecimal.ONE);



        return new MortagageResidual(residualAmount, residualDuration);
    }

    private static BigDecimal calculateResidualAmount(RateAmounts rateAmounts, BigDecimal amount) {
        return amount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
