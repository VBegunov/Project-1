package task3;

import task3.model.Buyer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numberOfBuyers = Integer.parseInt(args[0]);
        List<Buyer> buyers = new ArrayList<>();
        Phaser phaser = new Phaser(numberOfBuyers);
        for (int i = 0; i < numberOfBuyers; i++) {
            buyers.add(new Buyer(phaser));
        }
        for(Buyer buyer: buyers){
            buyer.join();
            System.out.println(buyer.getInfo());
        }
    }
}
