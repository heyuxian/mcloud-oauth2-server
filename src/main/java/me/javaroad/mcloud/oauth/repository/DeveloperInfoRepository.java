package me.javaroad.mcloud.oauth.repository;

import me.javaroad.mcloud.oauth.entity.DeveloperInfo;
import me.javaroad.mcloud.oauth.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperInfoRepository extends JpaRepository<DeveloperInfo, Long> {

    DeveloperInfo findByUserUsername(String username);

    Page<DeveloperInfo> findByStatus(Status status, Pageable pageable);
}
