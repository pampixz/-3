import java.util.HashMap;

//класс для представления товара
class Product {
    String name; //наименование
    String description; //описание
    int quantity;  //количество
    double price;    //цена

    public Product(String name, String description, int quantity, double price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " " + description + ", Количество: " + quantity + ", Цена: " + price;
    }
}

//класс для управления хэш таблицей
public class ProductHashMap {
    private HashMap<String, Product> productMap;

    public ProductHashMap() {
        productMap = new HashMap<>();
    }

    //добавление метода по артикулу
    public void addProduct(String articleNumber, Product product) {
        productMap.put(articleNumber, product);
    }
    //поиск по артикулу
    public Product getProduct(String articleNumber) {
        return productMap.get(articleNumber);
    }
    //удаление по артикулу
    public void removeProduct(String articleNumber) {
        productMap.remove(articleNumber);
    }

    public static void main(String[] args) {
        ProductHashMap products = new ProductHashMap();

        // добавление товаров
        products.addProduct("7649376", new Product("Ноутбук", "Игровой", 20, 120.500));
        products.addProduct("4398761", new Product("Смартфон", "Apple", 22, 105.499));

        // поиск товара по артикулу
        System.out.println(products.getProduct("7649376"));

        // удаление товара по артикулу
        products.removeProduct("4398761");

        // попытка найти удаленный товар по артикулу
        System.out.println(products.getProduct("4398761")); //null
    }
}