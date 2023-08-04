package com.gfg.shoutreview.repository;

import com.gfg.shoutreview.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByMobile(String mobile);

    User findByName(String name);
}
