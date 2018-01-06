package me.javaroad.mcloud.oauth.repository;

import me.javaroad.mcloud.oauth.entity.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author heyx
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @EntityGraph(value = Client.FETCH_ALL_GRAPH)
    Client findByClientId(String clientId);

    Client findByUserUsername(String username);
}
