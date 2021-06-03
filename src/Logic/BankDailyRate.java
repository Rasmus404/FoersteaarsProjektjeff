package Logic;

import Datalayer.InterestRate;

//Victor
public class BankDailyRate {

    public double getDailyRate(){
        return InterestRate.i().todaysRate();
    }
}
