package task3.model;

class Stock {
    private static int products = 1000;

    static int getProducts() {
        return products;
    }

    static synchronized int buyProducts(int product){
        if(product < products){
            products = products - product;
            return product;
        } else {
            product = products;
            products = 0;
            return product;
        }
    }
}
