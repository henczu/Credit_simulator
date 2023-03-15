package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountCalculationServiceImpl implements AmountCalculationService {

    private final ConstantAmountsCalculationService constantAmountsCalculationService;
    private final DecreasingAmountCalculationService decreasingAmountCalculationService;


    public AmountCalculationServiceImpl(ConstantAmountsCalculationService constantAmountsCalculationService,
                                        DecreasingAmountCalculationService decreasingAmountCalculationService) {
        this.constantAmountsCalculationService = constantAmountsCalculationService;
        this.decreasingAmountCalculationService = decreasingAmountCalculationService;
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculate(inputData, overpayment);
            case DECREASING:
                return decreasingAmountCalculationService.calculate(inputData, overpayment);
            default:
                throw new MortgageException();
        }
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculate(inputData, overpayment, previousRate);
            case DECREASING:
                return decreasingAmountCalculationService.calculate(inputData, overpayment, previousRate);
            default:
                throw new MortgageException();
        }
    }


}
