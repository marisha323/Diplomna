package com.example.Diplomna.services;

import com.example.Diplomna.GrabePicture.VideoMetadataRepr;
import com.example.Diplomna.classValid.Comment_Crm;
import com.example.Diplomna.model.Comment;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public boolean existsById(Long id) {
        return commentRepo.existsById(id);
    }

    private static Comment_Crm convert(Comment comment) {
        Comment_Crm comment_crm = new Comment_Crm();
        comment_crm.setText(comment.getText());
        comment_crm.setVideo_id(comment.getVideo());
        return comment_crm;
    }

    public List<Comment_Crm> findAllConverted(Long videoId) {
        return commentRepo.findByVideoId(videoId).stream()
                .map(CommentService::convert)
                .collect(Collectors.toList());
    }
}
