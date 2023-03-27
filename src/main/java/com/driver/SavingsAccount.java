package com.driver;

public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        // minimum balance is 0 by default
        super(name,balance,0.0);
        this.rate= rate;
        this.maxWithdrawalLimit=maxWithdrawalLimit;
    }
    @Override
    public void withdraw(double amount) throws Exception {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance

        if(amount>maxWithdrawalLimit) throw new Exception("Maximum Withdraw Limit Exceed");
        else if(super.getBalance()-amount < 0) throw  new Exception("Insufficient Balance");
        else {
            setBalance(super.getBalance()-amount);
        }
    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount
        double interest = (super.getBalance()*years*rate)/100;

        return super.getBalance()+interest;
    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year

        double bal = super.getBalance();

        double ci = bal*(Math.pow(1+(rate/times),(times*years)));

        return  ci;
    }

}
