package com.example.Diplomna.repo;

import com.example.Diplomna.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByVideoId(Long videoId);
}
