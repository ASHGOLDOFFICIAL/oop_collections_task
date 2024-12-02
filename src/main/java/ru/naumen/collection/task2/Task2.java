package ru.naumen.collection.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно реализовать метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 *
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2
{

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях
     */
    public static List<User> findDuplicates(Collection<User> collA,
                                            Collection<User> collB) {
        // Предотвращаем возможные NPE.
        if (collA == null || collB == null) {
            return List.of();
        }

        /*
         Методы add и contains у HashSet работают за константу,
         если хэш-функция не будет иметь коллизий. Может содержать
         null, что важно, так как null может быть дупликатом.
        */
        final Set<User> duplicates = new HashSet<>();

        /*
         Учитывая выбранные структуры имеем m итераций, где m -- число элементов
         в collA. Проверка на наличие элемента в collB зависит от предоставленной
         коллекции. Добавление в множество работает за O(1).
         Использую forEach вместо range-based for, так как коллекция может
         предоставлять какие-то оптимизации в этом методе.
        */
        collA.forEach(user -> {
            if (collB.contains(user)) {
                duplicates.add(user);
            }
        });

        /*
         Копируем все элементы в ArrayList и возвращаем. Альтернативным вариантом было
         использование List::copyOf, но он не принимает null в качестве элемента, а
         изначальные коллекции могли содержать их.
        */
        return new ArrayList<>(duplicates);
    }

    public static void main(String[] args) {
        final List<User> collA = new ArrayList<>();
        collA.add(null);
        final Set<User> collB = new HashSet<>();
        collB.add(null);

        System.out.println(findDuplicates(collA, collB));
    }
}
