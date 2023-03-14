package service;

import model.InputData;
import model.MortgageReference;
import model.Rate;

public interface ReferenceCalculationService {

    MortgageReference calculate(InputData inputData);
    // MortgageReference calculate(InputData inputData, Rate previousRate);

}
