package ADA.RA2.jdbc.Telepizza;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MainPizza {

    private static final String url = "jdbc:mysql://localhost:33060/mydb";
    private static final String pass = "admin";
    private static final String user = "root";

    private static final String selectSql = "SELECT COUNT(*) FROM ingredient WHERE name = ?";
    private static final String insertSql = "INSERT INTO ingredient (name, calories) VALUES (?,?)";
    private static final String insertRelSql = "INSERT INTO pizza_has_ingredient (pizza_id, ingredient_id) VALUES (?,?)";

    public static void processPizzaWithIngredients(Map<Integer, Ingredient[]> data){
        try (final Connection conn = DriverManager.getConnection(url,user,pass)){
            conn.setAutoCommit(false);
            try(PreparedStatement selectPr = conn.prepareStatement(selectSql);
                PreparedStatement insertPr = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement insertRelPr = conn.prepareStatement(insertRelSql)) {
                    for (Map.Entry<Integer, Ingredient[]> entry : data.entrySet()) {
                        for (int i = 0; i < entry.getValue().length; i++) {
                            Ingredient[] ingre = entry.getValue();
                            selectPr.setString(1, ingre[i].getName());
                            ResultSet rsSelect = selectPr.executeQuery();

                            int idI = 0;
                            if (rsSelect.next()) { //existe ingrediente
                                idI = rsSelect.getInt(1);
                            }else{
                                insertPr.setString(1, ingre[i].getName());
                                insertPr.setInt(2,ingre[i].getCalories());
                                insertPr.executeUpdate();
                                ResultSet rs = insertPr.getGeneratedKeys();
                                if(rs.next()){
                                    idI = rs.getInt(1);
                                }
                            }
                            insertRelPr.setInt(1,entry.getKey());
                            insertRelPr.setInt(2,idI);
                            insertRelPr.executeUpdate();
                            conn.commit();
                        }
                    }
                }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Map<Integer, Ingredient[]> data = new HashMap<>();
        Ingredient[] ingredientsMedi = {
                new Ingredient("atun",500),
                new Ingredient("tomate",100),
                new Ingredient("queso",700),
                new Ingredient("anchoas",650),
                new Ingredient("aceite",400),
                new Ingredient("aceitunas",450)
        };
        Ingredient[] ingredientsBBQ = {
                new Ingredient("carne vacuno",500),
                new Ingredient("tomate",100),
                new Ingredient("queso",700),
                new Ingredient("cebolla",650),
                new Ingredient("salsa bbq",400),
        };
        data.put(1,ingredientsBBQ);
        data.put(2,ingredientsMedi);
        processPizzaWithIngredients(data);
    }
}
