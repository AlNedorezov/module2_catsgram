package ru.yandex.practicum.catsgram.post;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.post.model.Post;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.util.Date.from;
import static ru.yandex.practicum.catsgram.utils.DateUtils.toInstant;

@RequiredArgsConstructor
@Component
@Primary
public class DbPostStorage implements PostStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void create(Post data) {
        String sqlQuery = """
                insert into posts (
                    author, creation_date, description, photo_url
                )
                values (?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"post_id"});
            stmt.setString(1, data.getAuthor());
            stmt.setDate(2, new Date(from(data.getCreationDate()).getTime()));
            stmt.setString(3, data.getDescription());
            stmt.setString(4, data.getPhotoUrl());
            return stmt;
        }, keyHolder);
        data.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void update(Post data) {
        String sqlQuery =
                "update posts SET author = ?, creation_date = ?, description = ?, photo_url = ? WHERE post_id = ?";
        jdbcTemplate.update(
                sqlQuery,
                data.getAuthor(),
                data.getCreationDate(),
                data.getDescription(),
                data.getPhotoUrl(),
                data.getId()
        );
    }

    @Override
    public Post get(long id) {
        final String sqlQuery = "select * from posts WHERE post_id = ?";
        final List<Post> posts = jdbcTemplate.query(sqlQuery, DbPostStorage::makePost, id);
        if (posts.size() != 1) {
            throw new NotFoundException("post id=" + id);
        }
        return posts.get(0);
    }

    @Override
    public void delete(long id) {
        String sqlQuery = "delete from posts WHERE post_id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public List<Post> getAll() {
        return jdbcTemplate.query("select * from posts", DbPostStorage::makePost);
    }

    static Post makePost(ResultSet rs, int rowNum) throws SQLException {
        return new Post(
                rs.getLong("post_id"),
                rs.getString("author"),
                toInstant(rs.getDate("creation_date")),
                rs.getString("description"),
                rs.getString("photo_url"));
    }
}
