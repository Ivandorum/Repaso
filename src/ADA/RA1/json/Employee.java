package ADA.RA1.json;

import java.util.List;

public class Employee {
    private String employeeld;
    private PersonalInformation personalInformation;
    private ContacInformation contacInformation;
    private EmploymentDetails employmentDetails;

}
class ContacInformation{
    private String email;
    private List<Phone> phoneNumbers;
}
class Phone{
    private String type;
    private List<Integer> number;
}
class PersonalInformation{
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private List<Addresses> addresses;
}

class Addresses{
    private String home;
    private String street;
    private String city;
    private String postalCode;
    private String country;
}
class EmploymentDetails{
    private String position;
    private String department;
    private String hireDate;
    private Salary salary;
    private List<Project> projects;
}
class Salary {
    private int amount;
    private String currency;
}
class Project{
    private String projectId;
    private String projectName;
    private String startDate;
}