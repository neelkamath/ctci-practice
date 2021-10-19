import java.util.LinkedList;
import java.util.Optional;

enum AnimalType {CAT, DOG}

/**
 * Question:
 * Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first out"
 * basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter, or they can
 * select whether they would prefer a dog or a cat (and will receive the oldest animal of that type). They cannot select
 * which specific animal they would like. Create the data structures to maintain this system and implement operations
 * such as {@code enqueue}, {@code dequeueAny}, {@code dequeueDog}, and {@code dequeueCat}. You may use the built-in
 * {@link LinkedList} data structure.
 * <p>
 * Solution:
 * Using {@link LinkedList}s.
 */
public final class App1 {
    public static void main(final String[] args) {
        final var shelter = new AnimalShelter();
        shelter.enqueue(AnimalType.CAT, "Cat 1");
        shelter.enqueue(AnimalType.CAT, "Cat 2");
        shelter.enqueue(AnimalType.DOG, "Dog 1");
        shelter.enqueue(AnimalType.CAT, "Cat 3");
        shelter.enqueue(AnimalType.DOG, "Dog 2");
        System.out.printf("dequeueAny(): %s\n", shelter.dequeueAny().get().getName());
        System.out.printf("dequeueDog(): %s\n", shelter.dequeueDog().get().getName());
        System.out.printf("dequeueCat(): %s\n", shelter.dequeueCat().get().getName());
        System.out.printf("dequeueAny(): %s\n", shelter.dequeueAny().get().getName());
        System.out.printf("dequeueDog(): %s\n", shelter.dequeueDog().get().getName());
    }
}

final class AnimalShelter {
    private final LinkedList<Cat> cats = new LinkedList<>();
    private final LinkedList<Dog> dogs = new LinkedList<>();
    private int count = 0;

    void enqueue(final AnimalType type, final String name) {
        final var animal = Animal.create(type, name, ++count);
        switch (type) {
            case CAT -> cats.addLast((Cat) animal);
            case DOG -> dogs.addLast((Dog) animal);
        }
    }

    /**
     * @return The oldest animal, or empty if there are none.
     */
    Optional<Animal> dequeueAny() {
        final var hasNoCats = cats.isEmpty();
        final var hasNoDogs = dogs.isEmpty();
        if (hasNoCats && hasNoDogs) return Optional.empty();
        Animal animal;
        if (hasNoCats || dogs.getFirst().getEntry() < cats.getFirst().getEntry()) {
            animal = dogs.getFirst();
            dogs.removeFirst();
        }
        animal = cats.getFirst();
        cats.removeFirst();
        return Optional.of(animal);
    }

    /**
     * @return The oldest {@link Cat}, or empty if there are none.
     */
    Optional<Cat> dequeueCat() {
        return (Optional<Cat>) (Object) dequeueAnimal(AnimalType.CAT);
    }

    /**
     * @return The oldest {@link Dog}, or empty if there are none.
     */
    Optional<Dog> dequeueDog() {
        return (Optional<Dog>) (Object) dequeueAnimal(AnimalType.DOG);
    }

    /**
     * @return The oldest {@link Animal} of the {@code type}, or empty if there are none.
     */
    private Optional<Animal> dequeueAnimal(final AnimalType type) {
        final var list = switch (type) {
            case CAT -> cats;
            case DOG -> dogs;
        };
        if (list.isEmpty()) return Optional.empty();
        final var animal = list.getFirst();
        list.removeFirst();
        return Optional.of(animal);
    }
}

abstract class Animal {
    private final String name;
    private final int entry;

    Animal(final String name, final int entry) {
        this.name = name;
        this.entry = entry;
    }

    static Animal create(final AnimalType type, final String name, final int entry) {
        return switch (type) {
            case CAT -> new Cat(name, entry);
            case DOG -> new Dog(name, entry);
        };
    }

    String getName() {
        return name;
    }

    int getEntry() {
        return entry;
    }
}

final class Cat extends Animal {
    Cat(final String name, final int entry) {
        super(name, entry);
    }
}

final class Dog extends Animal {
    Dog(final String name, final int entry) {
        super(name, entry);
    }
}
