package com.example.resilience.example.services;

import com.example.resilience.example.clients.CommentsClient;
import com.example.resilience.example.dto.CommentVO;
import com.example.resilience.example.mapper.CommentMapper;
import com.example.resilience.example.response.Comment;
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

    public List<CommentVO>  getComments(){
        logger.info("entered in getComments");
        ArrayList<Comment> comments = commentsClient.getComments();
        logger.info("returning comments");
        return commentsMapper.fromApiRsToCommentsVo(comments);
    }
}
