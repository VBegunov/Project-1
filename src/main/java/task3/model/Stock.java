package task3.model;

class Stock {
    private static int products = 1000;

    static int getProducts() {
        return products;
    }

    static synchronized int buyProducts(int numberOfProducts){
        if(numberOfProducts < products){
            products = products - numberOfProducts;
            return numberOfProducts;
        } else {
            numberOfProducts = products;
            products = 0;
            return numberOfProducts;
        }
    }
}
