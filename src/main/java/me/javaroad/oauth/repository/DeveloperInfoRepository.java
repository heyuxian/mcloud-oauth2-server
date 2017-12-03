package me.javaroad.oauth.repository;

import me.javaroad.oauth.entity.DeveloperInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperInfoRepository extends JpaRepository<DeveloperInfo, Long> {

    DeveloperInfo findByUserUsername(String username);
}
