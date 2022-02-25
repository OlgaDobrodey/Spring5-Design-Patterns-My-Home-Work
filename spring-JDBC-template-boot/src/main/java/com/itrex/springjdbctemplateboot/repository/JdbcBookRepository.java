package com.itrex.springjdbctemplateboot.repository;

import com.itrex.springjdbctemplateboot.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBookRepository implements BookRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("select count(*) from books", Long.class);
    }

    @Override
    public int save(Book book) {
        return jdbcTemplate.update(
                "insert into books (name, price) values(?,?)",
                book.getName(), book.getPrice());
    }

    @Override
    public int update(Book book) {
        return jdbcTemplate.update(
                "update books set price = ? where id = ?",
                book.getPrice(), book.getId());
    }


    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete books where id = ?",
                id);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(
                "select * from books",
                (rs, rowNum) ->
                        new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getLong("price")
                        )
        );
    }

    // jdbcTemplate.queryForObject, populates a single object
    @Override
    public Optional<Book> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from books where id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getLong("price")
                        ))
        );
    }

    @Override
    public List<Book> findByNameAndPrice(String name, Long price) {
        return jdbcTemplate.query(
                "select * from books where name like ? and price <= ?",
                new Object[]{"%" + name.toUpperCase() + "%", price},
                (rs, rowNum) ->
                        new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getLong("price")
                        )
        );
    }

    @Override
    public String getNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "select name from books where id = ?",
                new Object[]{id},
                String.class
        );
    }
}
