package my_slipp.slipp.web.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.userId = :userId")
  Optional<User> findUserByUserId(@Param("userId") String userId);

  @Modifying
  @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password = :password WHERE userId = :userId")
  void update(@Param("userId") String userId,
                        @Param("name") String name,
                        @Param("email") String email,
                        @Param("password") String password);
}
