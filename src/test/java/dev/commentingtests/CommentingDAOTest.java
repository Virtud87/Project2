package dev.commentingtests;

import com.investing_app.entities.Commenting;
import com.investing_app.dao.CommentingDAO;
import com.investing_app.dao.CommentingDAOImp;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CommentingDAOTest {

    CommentingDAO commentingDAO = new CommentingDAOImp();

    @Test
    void testCreateComment() {
        Commenting newComment = new Commenting(0,1, 2, 1, "Trial", "2021/01/01");
        Commenting returnedComment = commentingDAO.createComment(newComment);
        Assert.assertTrue(returnedComment.getCommentId() != 0);
    }
    @Test
    void selectCommentById(){
        Commenting commenting = commentingDAO.getCommentById(1);
        Assert.assertEquals(commenting.getCommentId(), 1);
    }

    @Test
    void getAllComments(){
        List<Commenting> commenting = commentingDAO.getAllComments();
        Assert.assertTrue(commenting.size() >= 2);
        for (Commenting c : commenting){
            System.out.println(c);
            System.out.println();
        }
    }
}
