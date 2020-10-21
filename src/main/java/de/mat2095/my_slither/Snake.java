package de.mat2095.my_slither;

import java.util.Deque;
import java.util.stream.Stream;


class Snake {

    final int DEFAULT_MAXIMUM_LIVES = 3;

    final int id;
    final String name;
    double x, y;
    int dir;
    double wang, ang;
    double sp, tsp;
    private double fam;
    final Deque<SnakeBodyPart> body;
    private final MySlitherModel model;
    /** Number lives this snake has */
    private int lives;
    boolean hasDied, hasReallyDied;
 
    Snake(int id, String name, double x, double y, double wang, double ang, double sp, double fam, Deque<SnakeBodyPart> body, MySlitherModel model) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.dir = 0;
        this.wang = wang;
        this.ang = ang;
        this.sp = sp;
        tsp = 0;
        this.fam = fam;
        this.body = body;
        this.model = model;
        
        this.lives = DEFAULT_MAXIMUM_LIVES;
        this.hasDied = false;
        this.hasReallyDied = false;
    }

    private double getSc() {
        return Math.min(6, 1 + (body.size() - 2) / 106.0);
    }

    double getScang() {
        return 0.13 + 0.87 * Math.pow((7 - getSc()) / 6, 2);
    }

    double getSpang() {
        return Math.min(sp / model.spangdv, 1);
    }

    private double getFsp() {
        return model.nsp1 + model.nsp2 * getSc();
    }

    boolean isBoosting() {
        return tsp > getFsp();
    }

    double getFam() {
        return fam;
    }

    void setFam(double fam) {
        this.fam = fam;
    }

    /**
     * Informs of whether this snake may re-spawn after being killed.
     * 
     * @return true only if this Snake has at least a life yet
     */
    boolean canRespawn() {
        return this.lives > 0 && hasDied;
    }

    /**
     * Sets the state of this Snake from playing to dead
     */
    void die() {
        this.hasDied = true;
        this.lives --;
    }

    void respawn() {
        // nb of body part to remove
        if (this.lives < 0) {
            return;
        }
        int nbToRemove = body.size()/10;
        for (int i = 0; i < nbToRemove; i++) {
            body.pollLast();
        }
        // The game starts again
        hasDied = false;
    }

}
