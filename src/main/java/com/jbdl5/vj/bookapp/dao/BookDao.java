package com.jbdl5.vj.bookapp.dao;

import com.jbdl5.vj.bookapp.model.Book;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao<T,Long extends Serializable> extends JpaRepository<Book,Long> {

    Book getBookById(Long id);

    @Modifying
    @Query(value = "delete from Book b where b.cost > :cost",nativeQuery = true)
    Integer deleteBooksOverCost( @Param("cost") Integer cost);
}
