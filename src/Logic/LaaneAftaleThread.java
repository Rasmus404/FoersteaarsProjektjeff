package Logic;

import Presentation.LaaneaftaleSkaerm;

import java.util.function.IntToDoubleFunction;

public class LaaneAftaleThread extends Thread{

    LaaneaftaleSkaerm laaneaftaleSkaerm;

    public LaaneAftaleThread(LaaneaftaleSkaerm laaneaftaleSkaerm){
        this.laaneaftaleSkaerm = laaneaftaleSkaerm;
    }

    public void run() {
        while(true) {
            while(laaneaftaleSkaerm.carCheck()) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
            laaneaftaleSkaerm.updateCarValue();
            while(laaneaftaleSkaerm.fieldCheck()){

                try{
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
            laaneaftaleSkaerm.updateValues();

        }
    }
}
