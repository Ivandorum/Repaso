package ADA.RA1.json;


import java.util.List;

class Association{
    private List<HeroesTeams> heroesTeams;
}

class Members{
    private String name;
    private int age;
    private List<String> powers;
}
public class HeroesTeams{
    private String squadName;
    private String homeTown;
    private int formed;
    private boolean active;
    private List<Members> members;
}
