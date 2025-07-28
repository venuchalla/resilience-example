package com.example.resilience.services;

import com.example.resilience.annotations.LogAfterMethod;
import com.example.resilience.clients.CommentsClient;
import com.example.resilience.dto.CommentVO;
import com.example.resilience.mapper.CommentMapper;
import com.example.resilience.response.Comment;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentApiService {
    @Autowired
    CommentsClient commentsClient;
    Logger logger = LoggerFactory.getLogger(CommentApiService.class);

    CommentMapper commentsMapper = Mappers.getMapper(CommentMapper.class);

    @LogAfterMethod(value = "getComments")
    public List<CommentVO> getComments() {

        ArrayList<Comment> comments = commentsClient.getComments();
        return commentsMapper.fromApiRsToCommentsVo(comments);
    }
}
