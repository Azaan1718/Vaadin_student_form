package oop;

import java.util.Scanner;

public class atm_exception {
    
    public static void main(String args[]){

        Scanner sc=new Scanner(System.in);

        final int correct_pin=1234;
        int balance=1000;
        int withdraw;
        int pin;

        try{

           
        System.out.println("Enter ATM pin : ");
        pin=sc.nextInt();

        if(pin!=correct_pin){
                throw new InvalidPinException("Invalid PIN");
            }

         System.out.println("Enter withdrawal amount : ");
        withdraw=sc.nextInt();


            

            if(withdraw>balance){
                throw new InsuffiesientBalanceException("Insuffiecient balance.Cannot Withdraw");
            }

            if(withdraw<0){
                throw new InvalidAmountException("Invalid withdrawal amount");
            }

            balance=balance-withdraw;

        }

        catch(InvalidPinException e){
            System.out.println(e.getMessage());
        }

        catch(InsuffiesientBalanceException e){
            System.out.println(e.getMessage());
        }

        catch(InvalidAmountException e){
            System.out.println(e.getMessage());
        }

        finally{
            
                System.out.println("Amount balance in account : "+ balance);
            
        }
    }
}
