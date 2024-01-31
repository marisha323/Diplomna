package com.example.Diplomna.Controller;

import com.example.Diplomna.classValid.Comment_Crm;
import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.model.Comment;
import com.example.Diplomna.repo.CommentRepo;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.services.CommentService;
import com.example.Diplomna.services.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private VideoService videoService;
    private CommentRepo commentRepo;
    private UserRepo userRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    public CommentController(CommentRepo commentRepo, UserRepo userRepo) {
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/add-comment")
    public String comment(@RequestHeader("Authorization")String authorizationHeader, Comment_Crm comment_crm) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        logger.info("userId " + userId);
        logger.info("comment_crm " + comment_crm.getVideo_id());
        logger.info("commentService " + commentService.existsById(comment_crm.getVideo_id()));

        if (videoService.existsById(comment_crm.getVideo_id()) && userId != null) {
            Comment comment=new Comment();
            comment.setUser(userId);
            comment.setText(comment_crm.getText());
            comment.setVideo(comment_crm.getVideo_id());
            comment.setDateTime(LocalDateTime.now());
            logger.info("comment" + comment);
            commentRepo.save(comment);
            return "Comment added successfully";
        }
        else {
            return "ERROR";
        }
    }

    @GetMapping("/all")
    public List<Comment_Crm> findAllCommentsByVideoId(@RequestParam Long videoId) {
        return commentService.findAllConverted(videoId);
    }
}
