package com.springboot.mission.domain.user.repository;

import com.springboot.mission.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //소셜 식별값(mail)을 통해 이미 가입된 유저인지 확인합니다.
    //중복 체크 시 사용됩니다.
    boolean existsByMail(String mail);

    Optional<User> findByMail(String mail);
}
