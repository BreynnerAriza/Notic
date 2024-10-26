package org.example.api;

import org.example.domain.Fighter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FighterInMemoryDAO implements IFighterDAO {

    private final List<Fighter> fighters;

    public FighterInMemoryDAO() {
        Random random = new Random();
        this.fighters = List.of(
                new Fighter("Goku", random.nextInt(101) + 20, random.nextInt(101), random.nextInt(101)),
                new Fighter("Vegeta", random.nextInt(101) + 20, random.nextInt(101), random.nextInt(101)),
                new Fighter("Piccolo", random.nextInt(101) + 20, random.nextInt(101), random.nextInt(101)),
                new Fighter("Gohan", random.nextInt(101) + 20, random.nextInt(101), random.nextInt(101)),
                new Fighter("Krillin", random.nextInt(101) + 20, random.nextInt(101), random.nextInt(101)),
                new Fighter("Yamcha", random.nextInt(101) + 20, random.nextInt(101), random.nextInt(101)),
                new Fighter("Tien", random.nextInt(101) + 20, random.nextInt(101), random.nextInt(101)),
                new Fighter("Trunks", random.nextInt(101) + 20, random.nextInt(101), random.nextInt(101)),
                new Fighter("Frieza", random.nextInt(101) + 20, random.nextInt(101), random.nextInt(101)),
                new Fighter("Cell", random.nextInt(101) + 20, random.nextInt(101), random.nextInt(101))
        );
    }

    @Override
    public List<Fighter> listAllFighter() {
        return new ArrayList<>(fighters);
    }

}
