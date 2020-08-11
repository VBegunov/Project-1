package task3.model;

import java.util.concurrent.Phaser;

public class Buyer extends Thread {
    private int countBuys;
    private int countProducts;
    private Phaser phaser;

    public Buyer(Phaser phaser) {
        this.phaser = phaser;
        this.start();
    }

    private int getCountBuys() {
        return countBuys;
    }

    private int getCountProducts() {
        return countProducts;
    }

    @Override
    public void run() {
        while (Stock.getProducts() > 0) {
            int products = (int) (Math.random() * 10) + 1;
            countProducts += Stock.buyProducts(products);
            countBuys++;
            phaser.arriveAndAwaitAdvance();
        }
    }

    @Override
    public String toString() {
        return getName() + " совершил " + getCountBuys() + " покупок, и купил - " + getCountProducts() + " товаров";
    }
}
