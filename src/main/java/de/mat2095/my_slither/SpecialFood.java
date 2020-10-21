package de.mat2095.my_slither;

class SpecialFood {
    final int x;
    final int y;
    private double size;
    private final double rsp;
    private final long spawnTime;
    final String name;
    int id;

    SpecialFood(int x, int y, double size, boolean slowSpawn, String name, int id){
        this.x = x;
        this.y = y;
        this.size =size;
        this.rsp = slowSpawn ? 2: 1;
        spawnTime = System.currentTimeMillis();
        this.name = name;
        this.id = id;
    }
    double getSize() {
        return size;
    }

    double getRadius() {
        double fillRate = rsp * (System.currentTimeMillis() - spawnTime) / 1200;
        if (fillRate >= 1) {
            return size;
        } else {
            return (1 - Math.cos(Math.PI * fillRate)) / 2 * size;
        }
    }
    String getname(){
        return name;
    }

    int getId(){
        return id;
    }


    
}
