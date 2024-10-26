package org.example.logic;

import org.example.domain.Fighter;

import java.util.Random;

public class Fight {

    private static final Random random = new Random();

    private Fight(){}

    public static Fighter getWinner(Fighter fighterOne,  Fighter fighterTwo) {

        Fighter primeAttack = defineFirstAttack(fighterOne, fighterTwo);
        Fighter secondAttacker = primeAttack.equals(fighterOne) ? fighterTwo : fighterOne;

        do{
            confrontation(primeAttack, secondAttacker);
        }while(fighterOne.isAlive() && fighterTwo.isAlive());

        return fighterOne.isAlive() ? fighterOne : fighterTwo;
    }

    public static void confrontation(Fighter primeAttacker, Fighter secondAttacker){
        primeAttacker.attack(secondAttacker);
        if(secondAttacker.isAlive()) {
            System.out.println();
            secondAttacker.attack(primeAttacker);
        }
        System.out.println();
    }

    public static Fighter defineFirstAttack(Fighter fighterOne, Fighter fighterTwo) {
        if(fighterOne.getSpeed() > fighterTwo.getSpeed()) {
            return fighterOne;
        }else if(fighterOne.getSpeed() < fighterTwo.getSpeed()) {
            return fighterTwo;
        }else{
            return random.nextBoolean() ? fighterOne : fighterTwo;
        }
    }

}
