package pl.javastart.streamstask;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleTest {

    StreamsTask streamsTask = new StreamsTask();

    Collection<User> users = List.of(
            (new User(1L, "Robert", 20)),
            (new User(2L, "Julian", 40)),
            (new User(3L, "Ada", 22)),
            (new User(4L, "Klara", 35)),
            (new User(5L, "Alex", 13))
    );

    List<Expense> expenses = List.of(
            (new Expense(1L, "Kapelusz", new BigDecimal("49.99"), ExpenseType.WEAR)),
            (new Expense(2L, "Burger", new BigDecimal("39.99"), ExpenseType.FOOD)),
            (new Expense(3L, "Koktajl Owocowy", new BigDecimal("19.79"), ExpenseType.FOOD)),
            (new Expense(4L, "Okulary", new BigDecimal("67.49"), ExpenseType.WEAR)),
            (new Expense(5L, "Hamulce", new BigDecimal("200"), ExpenseType.CAR))
    );

    @Test
    public void shouldReturnWomen() {
        //given
        //when
        Collection<User> women = streamsTask.findWomen(users);
        //then
        Collection<User> correctNames = new ArrayList<>();
        correctNames.add(new User(3L, "Ada", 22));
        correctNames.add(new User(4L, "Klara", 35));
        assertEquals(correctNames, women);
    }

    @Test
    public void shouldFindAverageMenAge() {
        //given
        //when
        Double averageMenAge = streamsTask.averageMenAge(users);
        //then
        Collection<User> correctMenList = new ArrayList<>();
        correctMenList.add(new User(1L, "Robert", 20));
        correctMenList.add(new User(2L, "Julian", 40));
        correctMenList.add(new User(5L, "Tomek", 13));
        double sum = 0;
        for (User user : correctMenList) {
            sum = sum + user.getAge();
        }
        double averageAge = sum/correctMenList.size();
        assertEquals(averageAge, averageMenAge);
    }

    @Test
    public void shouldGroupExpensesByUserId() {
        //given
        Map<Long, List<Expense>> groupedExpenses = Map.of(1L,
                List.of(new Expense(1L, "Kapelusz", new BigDecimal("49.99"), ExpenseType.WEAR)),
                2L, List.of(new Expense(2L, "Burger", new BigDecimal("39.99"), ExpenseType.FOOD)),
                3L, List.of(new Expense(3L, "Koktajl Owocowy", new BigDecimal("19.79"), ExpenseType.FOOD)),
                4L, List.of(new Expense(4L, "Okulary", new BigDecimal("67.49"), ExpenseType.WEAR)),
                5L, List.of(new Expense(5L, "Hamulce", new BigDecimal("200"), ExpenseType.CAR))
        );
        //when
        Map<Long, List<Expense>> correctGroupedList = streamsTask.groupExpensesByUserId(users, expenses);
        //then
        assertEquals(correctGroupedList, groupedExpenses);
    }

    @Test
    public void shouldGroupExpensesByUser() {
        //given
        Map<User, List<Expense>> groupedExpensesList = new HashMap<>();
        groupedExpensesList.put(new User(4L, "Klara", 35),
                List.of(new Expense(4L, "Okulary", new BigDecimal("67.49"), ExpenseType.WEAR)));
        groupedExpensesList.put(new User(2L, "Julian", 40),
                List.of(new Expense(2L, "Burger", new BigDecimal("39.99"), ExpenseType.FOOD)));
        groupedExpensesList.put(new User(3L, "Ada", 22),
                List.of(new Expense(3L, "Koktajl Owocowy", new BigDecimal("19.79"), ExpenseType.FOOD)));
        groupedExpensesList.put(new User(5L, "Alex", 13),
                List.of(new Expense(5L, "Hamulce", new BigDecimal("200"), ExpenseType.CAR)));
        groupedExpensesList.put(new User(1L, "Robert", 20),
                List.of(new Expense(1L, "Kapelusz", new BigDecimal("49.99"), ExpenseType.WEAR)));
        //when
        Map<User, List<Expense>> correctGroupedList = streamsTask.groupExpensesByUser(users, expenses);
        //then
        assertEquals(correctGroupedList, groupedExpensesList);
    }
}
