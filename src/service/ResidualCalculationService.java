package service;

import model.InputData;
import model.MortagageResidual;
import model.Rate;
import model.RateAmounts;

public interface ResidualCalculationService {
    MortagageResidual calculate(RateAmounts rateAmounts, InputData inputData);

    MortagageResidual calculate(RateAmounts rateAmounts, Rate previousRate);
}
