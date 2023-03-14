package service;

import model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Ref;
import java.util.LinkedList;
import java.util.List;

public class RateCalculationServiceImppl implements RateCalculationService {

    private static BigDecimal toCompare;
    private final TimePointService timePointService;
    private final AmountCalculationService amountCalculationService;
    private final OverpaymentCalculationService overpaymentCalculationService;
    private final ResidualCalculationService residualCalculationService;
    private final ReferenceCalculationService referenceCalculationService;

    public RateCalculationServiceImppl(
            TimePointService timePointService,
            AmountCalculationService amountCalculationService,
            OverpaymentCalculationService overpaymentCalculationService,
            ResidualCalculationService residualCalculationService,
            ReferenceCalculationService referenceCalculationService
    ) {
        this.timePointService = timePointService;
        this.amountCalculationService = amountCalculationService;
        this.overpaymentCalculationService = overpaymentCalculationService;
        this.residualCalculationService = residualCalculationService;
        this.referenceCalculationService = referenceCalculationService;
    }

    @Override
    public List<Rate> calculate(InputData inputData) {
        List<Rate> rates = new LinkedList<>();
        BigDecimal rateNumber = BigDecimal.ONE;

        Rate firstRate = calculateRate(rateNumber, inputData);

        rates.add(firstRate);

        Rate previousRate = firstRate;

        for (BigDecimal index = rateNumber.add(BigDecimal.ONE); index.compareTo(inputData.getMonthsDuration()) <= 0; index = index.add(BigDecimal.ONE)){
            Rate nextRate = calculateRate(index, inputData, previousRate);
            rates.add(nextRate);
            previousRate= nextRate;
            if (mortgageFinished(nextRate)) break;
        }

        return rates;
    }

    private static boolean mortgageFinished(Rate nextRate) {
        toCompare = nextRate.getMortagageResidual().getAmount().setScale(0, RoundingMode.HALF_UP);
        return BigDecimal.ZERO.equals(toCompare);
    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData) {
        TimePoint timePoint = timePointService.calculate(rateNumber,inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountCalculationService.calculate(inputData, overpayment);
        MortagageResidual mortagageResidual = residualCalculationService.calculate(rateAmounts, inputData);
        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData);


        return new Rate(rateNumber, timePoint, rateAmounts, mortagageResidual, mortgageReference);
    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData, Rate previousRate) {
        TimePoint timePoint = timePointService.calculate(rateNumber,inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountCalculationService.calculate(inputData, overpayment, previousRate);
        MortagageResidual mortagageResidual = residualCalculationService.calculate(rateAmounts, previousRate);
        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData);

        return new Rate(rateNumber, timePoint, rateAmounts, mortagageResidual, mortgageReference);
    }
}
