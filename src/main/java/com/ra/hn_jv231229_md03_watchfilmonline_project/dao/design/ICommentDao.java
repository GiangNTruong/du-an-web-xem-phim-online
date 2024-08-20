package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Comment;
import java.util.List;

public interface ICommentDao {
    // Hiển thị danh sách bình luận
    List<Comment> findAll(int currentPage, int size);
    void addComment(Comment comment);
    // Tìm kiếm bình luận
    List<Comment> findByContentContaining(String content);

    // Xóa bình luận
    void deleteById(Long id);

    Long countAllComment();
    // Sắp xếp bình luận
    List<Comment> findAllSorted();

    Double averageRating();

    List<Comment> findCommentByFilm(Long filmId);
}
