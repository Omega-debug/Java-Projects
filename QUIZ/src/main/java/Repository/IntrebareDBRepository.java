package Repository;

import Domain.Intrebari;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IntrebareDBRepository extends MemoryRepository<Intrebari> implements IDbRepository<Intrebari> {
    private String JDBC_URL = "jdbc:sqlite:identifier.sqlite";

    private Connection connection;

    public IntrebareDBRepository(){
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
            stmt.execute("CREATE TABLE IF NOT EXISTS intrebarute (id int, text varchar(400),Ra varchar(400),Rb varchar(400),Rc varchar(400),Rcorect varchar(400), age punctaj);");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void InitTable(){
        List<Intrebari> intrebarute =  new ArrayList<>();
        intrebarute.add(new Intrebari(1000,"Din","Alune","Migdale","Caju","Migdale",5));



        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO intrebarute values (?,?,?,?,?,?,?);")){
            for(Intrebari it:intrebarute){
                stmt.setInt(1,it.getId());
                stmt.setString(2,it.getText());
                stmt.setString(3,it.getRaspunsA());
                stmt.setString(4,it.getRaspunsB());
                stmt.setString(5,it.getRaspunsC());
                stmt.setString(6,it.getRaspunsCorect());
                stmt.setInt(7,it.getPunctaj());
                stmt.executeUpdate();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Intrebari> getAll(){
        ArrayList<Intrebari> intrb= new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * from intrebarute;")){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Intrebari in = new Intrebari(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6),rs.getInt(7));
                intrb.add(in);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return intrb;
    }

    public void add(Intrebari it){

        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO intrebarute values (?,?,?,?,?,?,?);")){
            stmt.setInt(1,it.getId());
            stmt.setString(2,it.getText());
            stmt.setString(3,it.getRaspunsA());
            stmt.setString(4,it.getRaspunsB());
            stmt.setString(5,it.getRaspunsC());
            stmt.setString(6,it.getRaspunsCorect());
            stmt.setInt(7,it.getPunctaj());
            stmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
