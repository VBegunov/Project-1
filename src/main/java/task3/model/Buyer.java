package task3.model;

import java.util.concurrent.Phaser;

public class Buyer extends Thread {
    private int countBuy;
    private int countProduct;
    private Phaser phaser;

    public Buyer(Phaser phaser) {
        this.phaser = phaser;
        this.start();
    }

    private int getCountBuy() {
        return countBuy;
    }

    private int getCountProduct() {
        return countProduct;
    }

    public String getInfo() {
        return getName() + " совершил " + getCountBuy() + " покупок, и купил - " + getCountProduct() + " товаров";
    }

    @Override
    public void run() {
        while (Stock.getProducts() > 0) {
            phaser.arriveAndAwaitAdvance();
            int products = (int) (Math.random() * 10) + 1;
            countProduct += Stock.buyProducts(products);
            countBuy++;
            phaser.arriveAndAwaitAdvance();
        }
    }
}
