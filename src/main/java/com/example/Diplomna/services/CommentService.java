package com.example.Diplomna.services;

import com.example.Diplomna.GrabePicture.VideoMetadataRepr;
import com.example.Diplomna.classValid.Comment_Crm;
import com.example.Diplomna.dto.CommentDto;
import com.example.Diplomna.dto.UserDto;
import com.example.Diplomna.model.Comment;
import com.example.Diplomna.model.User;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.repo.CommentRepo;
import com.example.Diplomna.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo, UserRepo userRepo) {
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
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

    public List<CommentDto> findAllConverted(Long videoId) {
        List<Comment> comments = commentRepo.findByVideoId(videoId);
        List<CommentDto> result = new ArrayList<>();
        for (Comment comment : comments) {
            Optional<User> user = userRepo.findById(comment.getUser());
            result.add(CommentDto.builder()
                    .id(comment.getId())
                    .text(comment.getText())
                    .user(UserDto.builder()
                            .id(user.get().getId())
                            .email(user.get().getEmail())
                            .photoUrl(user.get().getPhotoUrl())
                            .displayName(user.get().getUserName())
                            .build())
                    .build());
        }

        return result;
        /*return commentRepo.findByVideoId(videoId).stream()
                .map(CommentService::convert)
                .collect(Collectors.toList());*/
    }
}
