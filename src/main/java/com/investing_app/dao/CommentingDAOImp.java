package com.investing_app.dao;

import com.investing_app.customexceptions.CommentNotFound;
import com.investing_app.entities.Commenting;
import com.investing_app.utility.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentingDAOImp implements CommentingDAO {

    @Override
    public Commenting createComment(Commenting commenting){
        try(Connection connection = DatabaseConnection.createConnection()) {
            String sql = "insert into commenting values(default, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, commenting.getBusinessId());
            preparedStatement.setInt(2, commenting.getPitchId());
            preparedStatement.setInt(3, commenting.getSharkId());
            preparedStatement.setString(4, commenting.getCommenting());
            preparedStatement.setString(5, commenting.getCreateDate());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            commenting.setCommentId(resultSet.getInt("businessId"));
            return commenting;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Commenting getCommentById(int id) {
        try(Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from commenting where commentId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
            if (resultSet.next()) {
                Commenting commenting = new Commenting(
                        resultSet.getInt("commentId"),
                        resultSet.getInt("businessId"),
                        resultSet.getInt("pitchId"),
                        resultSet.getInt("sharkId"),
                        resultSet.getString("commenting"),
                        resultSet.getString("createDate")
                );
                return commenting;
            } else {
                throw new CommentNotFound("Comment not found");
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Commenting> getAllComments(){
        try(Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from commenting";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Commenting> comments = new ArrayList<>();
            while(resultSet.next()){
                Commenting comment = new Commenting(
                        resultSet.getInt("commentId"),
                        resultSet.getInt("businessId"),
                        resultSet.getInt("pitchId"),
                        resultSet.getInt("sharkId"),
                        resultSet.getString("commenting"),
                        resultSet.getString("createDate")
                );
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
