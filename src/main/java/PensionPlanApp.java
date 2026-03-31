import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Employee;
import entities.PensionPlan;
import utils.Utilities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PensionPlanApp {

    public static void main(String[] args) throws Exception {
        List<Employee> employees = new ArrayList<>();
        PensionPlan pp1 = new PensionPlan(1, LocalDate.of(2023, 1,17), 1000.000);
        PensionPlan pp2 = new PensionPlan(2,  LocalDate.of(2023, 1, 17), 1000.000);
        Employee e1 = new Employee(1, "Daniel", "Agar",  LocalDate.of(2025, 10, 01), 101000, pp1);
        Employee e2 = new Employee(2, "Elisa", "Croft",  LocalDate.of(2017, 12, 01), 102000, pp1);
        Employee e3 = new Employee(3, "Emanuel", "Sanders",  LocalDate.of(2017, 12, 01), 203000, pp1);
        Employee e4 = new Employee(4, "Kevin", "Linsey",  LocalDate.of(2026, 3, 01), 304000, null);
        Employee e5 = new Employee(5, "Peter", "Schrodinger",  LocalDate.of(2017, 12, 01), 105000, null);
        Employee e6 = new Employee(6, "Terry", "Willson",  LocalDate.of(2017, 12, 01), 506000, null);
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);
        employees.add(e6);

       // Utilities.empJsonList(employees);
        Utilities.getQuarterlyUpcomingEnrollees(employees);


    }
}
