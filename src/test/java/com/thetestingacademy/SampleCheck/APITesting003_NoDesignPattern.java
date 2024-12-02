package com.thetestingacademy.SampleCheck;

public class APITesting003_NoDesignPattern {
    public void step1(){
        System.out.println("Step 1");
    }

    public void step2(){
        System.out.println("Step 2");
    }

    public void step3(String param1){
        System.out.println("Step 3");
    }

    public static void main(String[] args) {
        APITesting003_NoDesignPattern np = new APITesting003_NoDesignPattern();
        np.step1();
        np.step2();
        np.step3("Pramod");

        //np.step1().step2().step3("pramod");
    }
}
