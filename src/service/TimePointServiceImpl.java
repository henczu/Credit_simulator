package service;

import model.InputData;
import model.TimePoint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class TimePointServiceImpl implements TimePointService{
    private static final BigDecimal YEAR =  BigDecimal.valueOf(12);
    @Override
    public TimePoint calculate(BigDecimal rateNumber, InputData inputData) {
        LocalDate date = inputData.getRepaymentStartDate();
        BigDecimal year = calculateYear(rateNumber);
        BigDecimal month = calculateMonth(rateNumber);

        return new TimePoint(date,year,month);
    }

    private BigDecimal calculateYear(final BigDecimal rateNumber){

        return  rateNumber.divide(YEAR, RoundingMode.HALF_UP);
    }
    private BigDecimal calculateMonth(final BigDecimal rateNumber){
        return rateNumber.remainder(YEAR)


    }

}
