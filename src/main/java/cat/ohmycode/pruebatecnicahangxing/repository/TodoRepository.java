package cat.ohmycode.pruebatecnicahangxing.repository;

import cat.ohmycode.pruebatecnicahangxing.entity.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

    @Query("Select t from TodoEntity t where (:title is null or t.title like %:title%) and (:username is null or :username like '' or t.user.username like :username)")
    Page<TodoEntity> findAllByTitleAndUsername(@Param("title") String title, @Param("username") String username, Pageable pageable);

}
