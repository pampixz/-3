import java.util.LinkedList;

public class HashTable<K, V> {
    // вложенный класс для хранения пар ключ-значение
    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    // поля хэш таблицы
    private LinkedList<Entry<K, V>>[] table;  // массив списков для хранения пар
    private int size;  // количество элементов в таблице
    private static final int DEFAULT_CAPACITY = 16;  // начальный размер массива

    // конструктор
    public HashTable() {
        table = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    // метод хэширования
    private int hash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    // метод для добавления пары ключ-значение
    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);  // обновляем значение, если ключ уже существует
                return;
            }
        }

        table[index].add(new Entry<>(key, value));  // добавляем новую пару
        size++;
    }

    // метод для получения значения по ключу
    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;  // возвращаем null, если ключ не найден
    }

    // метод для удаления пары по ключу
    public void remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    table[index].remove(entry);
                    size--;
                    return;
                }
            }
        }
    }

    // метод для получения количества элементов в таблице
    public int size() {
        return size;
    }

    // метод для проверки, пуста ли таблица
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();

        // добавление элементов
        hashTable.put("apple", 5);
        hashTable.put("banana", 3);
        hashTable.put("orange", 7);
        hashTable.put("pear", 2);

        // получение элементов
        System.out.println("apple: " + hashTable.get("apple"));  // 5
        System.out.println("banana: " + hashTable.get("banana"));  // 3
        System.out.println("pear: " + hashTable.get("pear"));  // 2
        System.out.println("orange: " + hashTable.get("orange"));  // 7

        //удаление элемента
        hashTable.remove("banana");
        System.out.println("banana: " + hashTable.get("banana"));  // null

        // пуроверка размера и пустоты
        System.out.println("Size: " + hashTable.size());  // 3
        System.out.println("Is empty: " + hashTable.isEmpty());  // false
    }
}