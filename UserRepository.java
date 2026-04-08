package com.voting.votingportal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByVoterId(String voterId);

    Optional<User> findByVoterIdAndAadharIdAndPassword(
            String voterId, String aadharId, String password);

    boolean existsByVoterId(String voterId);

    boolean existsByAadharId(String aadharId);
}
