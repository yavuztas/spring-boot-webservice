package dev.yavuztas.boilerplate.springbootwebservice.repository;

import dev.yavuztas.boilerplate.springbootwebservice.domain.Item;
import dev.yavuztas.boilerplate.springbootwebservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Fetch a user by username without its relations
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);

    /**
     * Custom query to fetch all items of a single user by username. We fetch items separately so that we can order by their fields
     * @param username
     * @return List<Item>
     */
    @Query("select distinct t from Item t left join fetch t.properties p where t.user.username=?1 order by t.id")
    List<Item> getUserItems(String username);

}
