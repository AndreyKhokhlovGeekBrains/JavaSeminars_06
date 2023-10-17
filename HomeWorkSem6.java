// Реализуйте структуру телефонной книги с помощью HashMap.
// Программа также должна учитывать, что в во входной структуре будут
// повторяющиеся имена с разными телефонами, их необходимо считать, как
// одного человека с разными телефонами. Вывод должен быть отсортирован по
// убыванию числа телефонов.

import java.util.*;
import java.util.stream.Collectors;

// Если переменную phoneBook и метод addContact сделать static, то при создании
// каждого экземпляра класса HomeWorkSem6 будет создаваться своя отдельная
// телефонная книга phoneBook. В данном случае мы создаем единую phoneBook внутри
// класса HomeWorkSem6.
// С помощью следующей конструкции:
// HashMap<String, ArrayList<Integer>> phoneBook = HomeWorkSem6.getPhoneBook();
// можно обратиться к созданной phoneBook из внешнего класса, но, поскольку addContact
// имеет модификатор доступа private, то добавить контакты из внешнего класса нельзя, только
// внутри данного класса.

public class HomeWorkSem6 {
    private static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

    public static void main(String[] args) {
        String name1 = "Andrey";
        String name2 = "Petr";
        String name3 = "Ivan";

        int number1 = 1234;
        int number2 = 1235;
        int number3 = 1236;
        int number4 = 1237;
        int number5 = 1238;
//        int number6 = 1238;

        addContact(name1, number1);
        addContact(name1, number2);
        addContact(name1, number3);
        addContact(name2, number3);
        addContact(name2, number4);
        addContact(name3, number5);
//        System.out.print(getPhoneBook());
        System.out.print(getPhoneBook2());
    }

    private static void addContact(String name, Integer number){
        phoneBook.computeIfAbsent(name, (key) -> new ArrayList<>()).add(number);
    }

//    public static String getPhoneBook(){
//        HashMap<String, Integer> countNumbers = new HashMap<>();
//        StringBuilder result = new StringBuilder();
//        for (Map.Entry<String, ArrayList<Integer>> entry : phoneBook.entrySet()){
//            countNumbers.put(entry.getKey(), entry.getValue().size());
//        }
//        ArrayList<Integer> tempList = new ArrayList<>(countNumbers.values());
//        tempList.sort(Comparator.reverseOrder());
//        for (int value : tempList) {
//            for (String key : countNumbers.keySet()) {
//                if(countNumbers.get(key) == value) {
//                    result.append(key + ":").append(phoneBook.get(key)).append("\n");
//                }
//            }
//        }
//        return result.toString();
//    }

    public static HashMap<String, ArrayList<Integer>> getPhoneBook2(){
        phoneBook =
                phoneBook.entrySet()
                        .stream()
                        .sorted((entry1, entry2) ->
                                Integer.compare(entry2.getValue().size(), entry1.getValue().size()))
                        .collect(Collectors.toMap(
//                                (entry) -> entry.getKey(),
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                // Using a LinkedHashMap collector we need to ensure that
                                // if there are duplicate keys, we'll keep the value associated
                                // with the first occurrence of the key, effectively ignoring
                                // the subsequent duplicates:
                                (e1, e2) -> e1, // It's specifying the behavior for resolving conflicts when multiple entries have the same key.
//                                () -> new LinkedHashMap<>())); // When you collect the elements into a Map, you need to specify the type of Map to be created.
                                LinkedHashMap::new));
        return phoneBook;
    }
}
