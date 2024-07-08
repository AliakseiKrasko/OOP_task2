import java.util.LinkedList;
import java.util.Queue;

// Интерфейс для поведения очереди
interface QueueBehaviour {
    void enqueue(Person person); // Помещает человека в очередь
    Person dequeue(); // Удаляет человека из очереди
    boolean isEmpty(); // Проверяет, пуста ли очередь
}

// Интерфейс для поведения магазина
interface MarketBehaviour {
    void acceptOrder(Person person); // Принимает заказ от человека
    void deliverOrder(Person person); // Отдаёт заказ человеку
}

// Класс для представления человека
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "'}";
    }
}

// Класс Market, реализующий интерфейсы QueueBehaviour и MarketBehaviour
class Market implements QueueBehaviour, MarketBehaviour {
    private Queue<Person> queue;
    private Queue<Person> orders;

    public Market() {
        queue = new LinkedList<>();
        orders = new LinkedList<>();
    }

    // Реализация методов интерфейса QueueBehaviour
    @Override
    public void enqueue(Person person) {
        queue.add(person);
        System.out.println(person + " added to the queue.");
    }

    @Override
    public Person dequeue() {
        Person person = queue.poll();
        if (person != null) {
            System.out.println(person + " removed from the queue.");
        }
        return person;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Реализация методов интерфейса MarketBehaviour
    @Override
    public void acceptOrder(Person person) {
        orders.add(person);
        System.out.println("Order accepted from " + person);
    }

    @Override
    public void deliverOrder(Person person) {
        if (orders.remove(person)) {
            System.out.println("Order delivered to " + person);
        } else {
            System.out.println("No order found for " + person);
        }
    }

    // Метод для обновления состояния магазина
    public void update() {
        if (!queue.isEmpty()) {
            Person person = dequeue();
            acceptOrder(person);
            deliverOrder(person);
        } else {
            System.out.println("No customers in queue to process.");
        }
    }

    public static void main(String[] args) {
        Market market = new Market();

        // Добавляем людей в очередь
        Person john = new Person("John");
        Person jane = new Person("Jane");

        market.enqueue(john);
        market.enqueue(jane);

        // Обновляем состояние магазина
        market.update(); // Обработаем Джона
        market.update(); // Обработаем Джейн
        market.update(); // Нет клиентов в очереди для обработки
    }
}
