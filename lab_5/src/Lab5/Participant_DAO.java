package Lab5;
import Lab3.Participant;
import Lab3.RegionalDep;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Participant_DAO implements DAO <Participant> {
    Connection connect;

   @Override
    public RegionalDep create(Participant entity){
       try{
           if(connect==null) {

               connect = getConnection();
           }
           String sql="INSERT INTO Participant (name, region)VALUES (?, ? )";
           PreparedStatement preparedStatement=connect.prepareStatement(sql);
           preparedStatement.setString(1,entity.getName());
           preparedStatement.setString(2,entity.getRegion());
           preparedStatement.execute();

           ResultSet rs = preparedStatement.getGeneratedKeys();
          rs.next();
           int key = rs.getInt(1);
           entity.setId(key);
           System.out.println(key);
               }catch (SQLException throwables){
                   throwables.printStackTrace();
               }

       return entity;
           }


    @Override
    public boolean update(Participant entity) {
       //UPDATE Participant SET name = 'name', region = 'region' WHERE id = 'id'  ;
        boolean result=false;
        try{
            if(connect==null){
                connect=getConnection();
            }
            String sql="UPDATE Participant SET name = ?, region = ? WHERE id = ? ";
            PreparedStatement preparedStatement=connect.prepareStatement(sql);
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setString(2,entity.getRegion());
            preparedStatement.setInt(3,entity.getId());

            result=preparedStatement.execute();

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result=false;
       //DELETE FROM Participant WHERE   id = 'id';
        try{
            if(connect==null){
                connect=getConnection();
            }
            String sql="DELETE FROM Participant WHERE   id = ? ";
            PreparedStatement preparedStatement=connect.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            result=preparedStatement.execute();

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Participant> find(Participant entity) {
       //SELECT * FROM Participant where id=1;
         List<Participant> result=new ArrayList<>();
        try{
            if(connect==null){
                connect=getConnection();
            }
            String sql=" SELECT * FROM Participant where id=? ";
            PreparedStatement preparedStatement=connect.prepareStatement(sql);
            preparedStatement.setInt(1,entity.getId());

            ResultSet resultSet =preparedStatement.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt(1);
                String name= resultSet.getString(2);
                String region = resultSet.getString(3);
                Participant tempparticipant = new Participant(name,region);
                tempparticipant.setId(id);
                result.add(tempparticipant);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Participant> findAll() {
        List<Participant> result=new ArrayList<>();
        try{
            if(connect==null){
                connect=getConnection();
            }
            String sql=" SELECT * FROM Participant  ";
            Statement statement=connect.createStatement();

            ResultSet resultSet =statement.executeQuery(sql);
            while(resultSet.next()){
                int id=resultSet.getInt(1);
                String name= resultSet.getString(2);
                String region = resultSet.getString(3);
                Participant tempparticipant = new Participant(name,region);
                tempparticipant.setId(id);
                result.add(tempparticipant);
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return result;

       
    }
}
