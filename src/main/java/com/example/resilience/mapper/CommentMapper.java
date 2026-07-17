package com.example.resilience.mapper;

import com.example.resilience.dto.CommentVO;
import com.example.resilience.response.Comment;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public abstract class CommentMapper {

  public abstract CommentVO convertApiRsToCommentVO(Comment comment);

  public abstract List<CommentVO> fromApiRsToCommentsVo(List<Comment> comments);
}
