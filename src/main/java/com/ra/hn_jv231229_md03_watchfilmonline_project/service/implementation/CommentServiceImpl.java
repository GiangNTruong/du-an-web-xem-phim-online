package com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.ICommentDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IFilmManageDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IUserDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.CommentDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Comment;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ICommentDao commentDao;

    @Autowired
    private IFilmManageDao manageDao;

    @Autowired
    private HttpSession session;

    @Autowired
    private IUserDao userDao;

    @Override
    public List<Comment> findAll(int currentPage, int size) {
        return commentDao.findAll(currentPage, size);
    }

    @Override
    public void addComment(CommentDto commentDto) {
        Film film = manageDao.getFilmById(commentDto.getFilmId());
//        User user = userDao.findById(2L);
        User user = userDao.findById(commentDto.getUserId());
        if (film != null && user != null) {
            Comment comment = new Comment();
            comment.setStars(commentDto.getStars());
            comment.setContent(commentDto.getContent());
            comment.setFilm(film);
            comment.setUser(user);
            commentDao.addComment(comment);
        } else {
            throw new RuntimeException("Film or User Ko tim thay");
        }
    }


    @Override
    public List<Comment> findByContentContaining(String content) {
        return commentDao.findByContentContaining(content);
    }

    @Override
    public void deleteById(Long id) {
        commentDao.deleteById(id);
    }

    @Override
    public List<Comment> findAllSorted() {
        return commentDao.findAllSorted();
    }

    @Override
    public Long countAllComment() {
        return commentDao.countAllComment();
    }

    @Override
    public List<Comment> findCommentByFilm(Long filmId) {
        return commentDao.findCommentByFilm(filmId);
    }
    @Override
    public Double averageRating() {
        return commentDao.averageRating();
    }
}
