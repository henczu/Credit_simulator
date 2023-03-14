package service;

import model.InputData;
import model.Rate;
import model.Summary;

import java.util.List;

public interface PrintingService {

    String INTEREST_SUM = "SUMA ODSETEK ";
    String OVERPAYMENT_PROVISION = "PROWIZJA ZA NADPŁATY: ";
    String LOSTS_SUM = " SUMA STRAT: ";
    String OVERPAYMENT_REDUCE_RATE = "NADPLATA, ZMNIEJSZENIE RATY: ";
    String OVERPAYMENT_REDUCE_PERIOD = "NADPLATA, ZMNIEJSZENIE OKRESU: ";
    String OVERPAYMENT_FREQUENCY = "SCHEMAT DOKONYWANIA NADPLAT: ";
    String RATE_NUMBER = "NR: ";
    String YEAR = "ROK: " ;
    String MONTH = "MIESIAC: ";
    String DATE = "DATA: ";
    String MONTHS = "MIESIECY: ";
    String RATE = "RATA: ";
    String INTEREST = "ODSETKI: ";
    String CAPITAL = "KAPITAL: ";
    String OVERPAYMENT = "NADPLATA: ";
    String LEFT_AMOUNT = "POZOSTALA KWOTA: ";
    String LEFT_MONTHS = "POZOSTALO MIESIECY: ";
    String MORTGAGE_AMOUNT = "KWOTA KREDYTU: ";
    String MORTGAGE_PERIOD = "OKRES KREDYTOWANIA: ";





    String CURRENCY = " ZL ";
    String NEW_LINE = "\n";
    String PERCENT = "% ";

    void printInputDataInfo(final InputData inputData);

    void printRates(List<Rate> rates);

    void printSummary(Summary summary);

}
