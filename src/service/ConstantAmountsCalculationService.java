package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

public interface ConstantAmountsCalculationService {


    RateAmounts calculate(InputData inputData, Overpayment overpayment);

    RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate);
}
