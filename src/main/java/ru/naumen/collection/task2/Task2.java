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

        // Используем Set для избежания дупликатов внутри возвращаемого списка дупликатов.
        // Копируем, чтобы не менять содержимое изначальной коллекции.
        final Set<User> collAList = new HashSet<>(collA);

        // Сложность операции зависит от реализации. В документации для Collection
        // о сложности ничего не сказано.
        collAList.retainAll(collB);

        return new ArrayList<>(collAList);
    }
}
