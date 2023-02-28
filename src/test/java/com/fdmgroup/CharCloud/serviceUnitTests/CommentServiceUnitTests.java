package com.fdmgroup.CharCloud.serviceUnitTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.CharCloud.Application;
import com.fdmgroup.CharCloud.exceptions.CommentValidationException;
import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.Comment;
import com.fdmgroup.CharCloud.model.Role;
import com.fdmgroup.CharCloud.model.User;
import com.fdmgroup.CharCloud.repository.CommentRepository;
import com.fdmgroup.CharCloud.security.Roles;
import com.fdmgroup.CharCloud.service.CollectionService;
import com.fdmgroup.CharCloud.service.CommentService;
import com.fdmgroup.CharCloud.service.UserService;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class CommentServiceUnitTests {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;
    
    @Mock
    private CollectionService collectionService;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    public void testAddCommentToCollection() throws CommentValidationException {
    
    	Role roleUser = new Role(Roles.User);
		User dummyUser1=new User(roleUser, "Dummy", "Dan", "Dum", "D", "d@dd.com");
		User dummyUser2=new User(roleUser, "Dummy2", "Danny", "Dummy", "DD", "dd@d.com");
		Collection collection1=new Collection("TestCollection", (int) 2000.0, dummyUser2,"aa" , "aa.img");
      
        Comment comment = new Comment();
        comment.setText("This is a comment");
        comment.setUser(dummyUser1);
        comment.setCollection(collection1);
        commentService.saveComment(comment);

        List<Comment> comments = commentService.findByCollectionId(collection1.getId());
    }

    @Test
    public void testFindByCollectionId_callsFindByCollectionIdMethodOfCommentRepository() {
        Integer collectionId = 3;
        List<Comment> expectedComments = new ArrayList<>();
        when(commentRepository.findByCollectionId(collectionId)).thenReturn(expectedComments);

        List<Comment> result = commentService.findByCollectionId(collectionId);

        assertEquals(expectedComments, result);
        verify(commentRepository, times(1)).findByCollectionId(collectionId);
    }

    @Test
    public void testSaveComment_callsSaveMethodOfCommentRepository() throws CommentValidationException {
        Role roleUser = new Role(Roles.User);
		User dummyUser1=new User(roleUser, "Dummy", "Dan", "Dum", "D", "d@dd.com");
		User dummyUser2=new User(roleUser, "Dummy2", "Danny", "Dummy", "DD", "dd@d.com");
		Collection collection1=new Collection("TestCollection", (int) 2000, dummyUser2,"aa" , "aa.img");
      
        Comment comment = new Comment();
        comment.setText("This is a comment");
        comment.setUser(dummyUser1);
        comment.setCollection(collection1);
        
        commentService.saveComment(comment);

        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    public void testDeleteComment_callsDeleteMethodOfCommentRepository() {
        Comment comment = new Comment();
        commentService.deleteComment(comment);

        verify(commentRepository, times(1)).delete(comment);
    }

}
