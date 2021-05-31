package Lab5;

import Lab3.RegionalDep;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


    class Regional_DAO implements DAO<RegionalDep> {
        Connection connect;

        @Override
        public RegionalDep create(RegionalDep entity) {

            try{
                if(connect==null) {

                    connect = getConnection();
                }
                String sql="INSERT INTO regionalDep (name, participant)VALUES (?, ? )";
                PreparedStatement preparedStatement=connect.prepareStatement(sql);
                preparedStatement.setString(1,entity.getName());
                preparedStatement.setInt(2,entity.getParticipant());
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
        public boolean update(RegionalDep entity) {

            boolean result=false;
            try{
                if(connect==null){
                    connect=getConnection();
                }
                String sql="UPDATE regionalDep SET name = ?, participant = ? WHERE id = ? ";
                PreparedStatement preparedStatement=connect.prepareStatement(sql);
                preparedStatement.setString(1,entity.getName());
                preparedStatement.setInt(2,entity.getParticipant());
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
            try{
                if(connect==null){
                    connect=getConnection();
                }
                String sql="DELETE FROM regionalDep WHERE   id = ? ";
                PreparedStatement preparedStatement=connect.prepareStatement(sql);
                preparedStatement.setInt(1,id);

                result=preparedStatement.execute();

            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
            return false;
        }

        @Override
        public List<RegionalDep> find(RegionalDep entity) {
            List<RegionalDep> result=new ArrayList<>();
            try{
                if(connect==null){
                    connect=getConnection();
                }
                String sql=" SELECT * FROM regionalDep where id=? ";
                PreparedStatement preparedStatement=connect.prepareStatement(sql);
                preparedStatement.setInt(1,entity.getId());

                ResultSet resultSet =preparedStatement.executeQuery();
                while(resultSet.next()){
                    int id=resultSet.getInt(1);
                    String name= resultSet.getString(2);
                    Integer participant= resultSet.getInt(3);
                    RegionalDep tempRegionalDep = new RegionalDep(name,participant);
                    tempRegionalDep.setId(id);
                    result.add(tempRegionalDep);
                }

            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
            return result;
        }

        @Override
        public List<RegionalDep> findAll() {
            List<RegionalDep> result=new ArrayList<>();
            try{
                if(connect==null){
                    connect=getConnection();
                }
                String sql=" SELECT * FROM regionalDep  ";
                Statement statement=connect.createStatement();

                ResultSet resultSet =statement.executeQuery(sql);
                while(resultSet.next()){
                    int id=resultSet.getInt(1);
                    String name= resultSet.getString(2);
                    int participant = resultSet.getInt(3);
                    RegionalDep tempRegionalDep = new RegionalDep(name,participant);
                    tempRegionalDep.setId(id);
                    result.add(tempRegionalDep);
                }

            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
            return result;

        }
    }


