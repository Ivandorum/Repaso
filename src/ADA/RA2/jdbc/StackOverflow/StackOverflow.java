package ADA.RA2.jdbc.StackOverflow;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class StackOverflow {

    private static final String url = "jdbc:mysql://localhost:33060/mydb";
    private static final String user = "root";
    private static final String pass = "admin";

    private static final double QUESTION_POINTS = 52.6;
    private static final double ANSWER_POINTS = 103.4;

    private static final String insertSql = "INSERT INTO comment (description, type, created_at, author_id) VALUES (?,?,?,?)";
    private static final String updateSql = "UPDATE author SET points = points + ? WHERE id = ?";
    private static final String selectSql = "SELECT a.name, COUNT(*) FROM comment c JOIN author a ON c.author_id=a.id WHERE c.author_id = ?";

    public static void processNewCommentsByAuthor(Comment[] comments, int idAuthor){
        try(final Connection connection = DriverManager.getConnection(url,user,pass)){
            try(PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
                PreparedStatement update = connection.prepareStatement(updateSql);
                PreparedStatement select = connection.prepareStatement(selectSql)){
                connection.setAutoCommit(false);
                double totalpoints = 0;
                for (int i = 0; i < comments.length; i++) {
                    Comment comment = comments[i];
                    preparedStatement.setString(1,comment.getDescripcion());
                    preparedStatement.setInt(2,comment.getType());
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(comment.getCreatedAt()));
                    preparedStatement.setInt(4,idAuthor);
                    preparedStatement.executeUpdate();
                    double pointsToAdd = 0;
                    if(comment.getType() == 1){
                        pointsToAdd = QUESTION_POINTS;
                    } else if (comment.getType() == 2){
                        pointsToAdd = ANSWER_POINTS;
                    }
                    totalpoints+=pointsToAdd;
                    update.setDouble(1,pointsToAdd);
                    update.setInt(2,idAuthor);
                    update.executeUpdate();
                    connection.commit();
                }
                connection.setAutoCommit(true);
                select.setInt(1,idAuthor);
                ResultSet rs = select.executeQuery();
                if (rs.next()){
                    System.out.println("El autor " + rs.getString("name") + " ha sumado " + totalpoints + " puntos a su ranking y tiene un total de " + rs.getInt(2) + " comentarios");
                }
            }catch (SQLException e){
            connection.rollback();
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos");
        }
    }

    public static void main(String[] args) {
        Comment[] comments = new Comment[2];
        comments[0] = new Comment("Como estan gente",1, LocalDateTime.now());
        comments[1] = new Comment("Me gusta mucho la fruta",2, LocalDateTime.now());
        processNewCommentsByAuthor(comments,1);
    }
}
