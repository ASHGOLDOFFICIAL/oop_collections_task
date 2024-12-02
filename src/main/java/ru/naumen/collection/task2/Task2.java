package ru.naumen.collection.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
         Переносим все элементы второй коллекции в Set, чтобы операция
         contains выполнялась за O(1). Перенос в элементов в текущей реализации
         выполняется за O(n), где n -- размер коллекции, но эта сложность не
         гарантирована в документации и может измениться в последующих версиях.
        */
        final Set<User> collBSet = new HashSet<>(collB);

        /*
         LinkedList позволяет делать вставку в конец за O(1).
         Других операций над списком не выполняется.
        */
        final List<User> duplicates = new LinkedList<>();

        // Использую Set для избежания дубликатов внутри списка дубликатов.
        final Set<User> presentInDuplicates = new HashSet<>();

        /*
         Делаем m итераций -> O(m). Не использую метод forEach, чтобы чуть более
         точно гарантировать названую сложность.
        */
        for (final User user : collA) {
            if (!presentInDuplicates.contains(user) && collBSet.contains(user)) {  // O(1) + O(1) -> O(1)
                duplicates.add(user);           // O(1)
                presentInDuplicates.add(user);  // O(1)
            }
        }

        /*
         Итоговая сложность: O(n) + O(m) -> O(n+m), либо O(max(n, m)), то есть линейная.
         Если убрать использования presentInDuplicates,
         то формальная сложность не изменится.
        */
        return duplicates;
    }
}
