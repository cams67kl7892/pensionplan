package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import entities.Employee;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Utilities {

    private static final int PENSION_YEARS = 1;
    public static void empJsonList(List<Employee> employees) throws JsonProcessingException {
        employees.sort(
                Comparator.comparing(Employee::getYearlySalary).reversed()
                        .thenComparing(Employee::getLastName)
        );
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String stringfieldEmpList = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(employees);
        System.out.println(stringfieldEmpList);
    }

    public static void getQuarterlyUpcomingEnrollees(List<Employee> employees) throws JsonProcessingException {

        LocalDate start = getNextQuarterStart();
        LocalDate end = getNextQuarterEnd();

        employees.stream()
                .filter(e -> e.getPensionPlan() == null)
                .filter(e -> {
                    LocalDate eligibilityDate =
                            e.getEmploymentDate().plusYears(PENSION_YEARS);

                    return !eligibilityDate.isBefore(start)
                            && !eligibilityDate.isAfter(end);
                })
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .collect(Collectors.toList());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String stringfieldEmpList = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(employees);
        System.out.println(stringfieldEmpList);
    }

    private static LocalDate getNextQuarterStart() {
        LocalDate now = LocalDate.now();
        int currentQuarter = (now.getMonthValue() - 1) / 3 + 1;

        int nextQuarter = currentQuarter + 1;
        int year = now.getYear();

        if (nextQuarter > 4) {
            nextQuarter = 1;
            year++;
        }

        int startMonth = (nextQuarter - 1) * 3 + 1;
        return LocalDate.of(year, startMonth, 1);
    }

    // 📅 Next Quarter End
    private static LocalDate getNextQuarterEnd() {
        return getNextQuarterStart().plusMonths(3).minusDays(1);
    }
}
