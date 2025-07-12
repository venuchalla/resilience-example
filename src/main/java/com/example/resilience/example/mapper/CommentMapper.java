package com.example.resilience.example.mapper;

import com.example.resilience.example.dto.CommentVO;
import com.example.resilience.example.response.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class CommentMapper {

   public abstract CommentVO convertApiRsToCommentVO(Comment comment);

  public abstract List<CommentVO> fromApiRsToCommentsVo(List<Comment> comments);
}
