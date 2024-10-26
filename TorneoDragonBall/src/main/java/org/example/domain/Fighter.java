package org.example.domain;

import lombok.*;

import javax.print.DocFlavor;
import java.util.Random;

@Getter
@EqualsAndHashCode
public class Fighter {

    private static final int DODGE_CHANCE = 20;
    private static final double EFFECTIVE_DEFENSE = 0.1;
    private static final double HEALTH_MAX = 100;

    @Setter
    private double health = HEALTH_MAX;
    private final String name;
    private final int stroke;
    private final int defense;
    private final int speed;

    public Fighter(String name, int stroke, int defense, int speed) {
        this.name = name;
        this.stroke = stroke;
        this.defense = defense;
        this.speed = speed;
    }

    public void attack(Fighter opponent) {
        System.out.println(this.name + " ataca a " + opponent.getName());
        opponent.defendOneself(this);
    }

    public void defendOneself(Fighter opponent) {
        double damageReceived;
        if (!dodge()) {
            damageReceived = calculateDamageReceived(opponent);
            System.out.println(this.name + " recivio " + damageReceived + " de daño");
            takeDamage(damageReceived);
            System.out.println("Vida actual de " + this.name + ": " + this.health);
        }else{
            System.out.println(this.name + " logro esquivar el ataque de " + opponent.getName());
        }
    }

    private void takeDamage(double damage) {
        double newHealth = this.health - damage;
        this.setHealth(
                newHealth >= 0 ? newHealth : 0
        );
    }

    private double calculateDamageReceived(Fighter opponent) {
        return (this.defense > opponent.getStroke()) ?
                opponent.getStroke() * EFFECTIVE_DEFENSE :
                opponent.getStroke() - this.defense;
    }

    private boolean dodge() {
        return (new Random().nextInt(100) + 1) <= DODGE_CHANCE;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void resetHealth() {
        this.health = HEALTH_MAX;
    }

    @Override
    public String toString() {
        return
                "name: " + name +
                        ", health: " + health +
                        ", stroke: " + stroke +
                        ", defense: " + defense +
                        ", speed: " + speed
                ;
    }


}
