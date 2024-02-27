package Repository;

import Domain.Piesa;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RadioDBRepository extends MemoryRepository<Piesa> implements IDbRepository<Piesa> {
    private String JDBC_URL = "jdbc:sqlite:identifier.sqlite";

    private Connection connection;

    public RadioDBRepository(){
        openConnection();
        createTable();
        //InitTable();
    }

    public void openConnection() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(JDBC_URL);

        try {
            if (connection == null || connection.isClosed()){
                connection = ds.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void closeConnection(){
        if(connection!=null){
            try{
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createTable()
    {
        try(final Statement stmt = connection.createStatement()){
            stmt.execute("CREATE TABLE IF NOT EXISTS piese(id int, Formatie varchar(400), Titlu varchar(400),GenMuzical varchar(400), durata varchar(400));");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void InitTable(){
        List<Piesa> piese =  new ArrayList<>();
        piese.add(new Piesa(1,"The Weeknd","Mornings","Blues", "2:46"));
        piese.add(new Piesa(2,"The Weeknd","I was never there","Blues", "2:53"));
        piese.add(new Piesa(3,"The Weeknd","Blindings Light","Blues", "2:46"));
        piese.add(new Piesa(4,"Andra","Inevitabil va fi bine", "Pop","3:04"));



        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO piese values (?,?,?,?,?);")){
            for(Piesa p:piese){
                stmt.setInt(1,p.getId());
                stmt.setString(2,p.getFormatie());
                stmt.setString(3,p.getTitlu());
                stmt.setString(4,p.getGenMuzical());
                stmt.setString(5,p.getDurata());
                stmt.executeUpdate();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Piesa> getAll(){
        ArrayList<Piesa> piese= new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * from piese;")){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Piesa pie = new Piesa(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5));
                piese.add(pie);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return piese;
    }

//    public void add(Youtuber yt){
//
//        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO youtubers values (?,?,?);")){
//            stmt.setInt(1,yt.getId());
//            stmt.setString(2,yt.getName());
//            stmt.setInt(3,yt.getSubscribers());
//            stmt.executeUpdate();
//
//        } catch(SQLException e){
//            e.printStackTrace();
//        }
//    }
}
