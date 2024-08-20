package com.ra.hn_jv231229_md03_watchfilmonline_project.service.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.CommentDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> findAll(int currentPage, int size);
    void addComment(CommentDto commentDto);
    List<Comment> findByContentContaining(String content);
    void deleteById(Long id);
    List<Comment> findAllSorted();
    Long countAllComment();
    List<Comment> findCommentByFilm(Long filmId);
    Double averageRating();
}
