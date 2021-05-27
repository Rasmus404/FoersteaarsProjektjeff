package Logic;

import Datalayer.InterestRate;

public class bankDailyRate {

    public double getDailyRate(){
        return InterestRate.i().todaysRate();
    }
}
