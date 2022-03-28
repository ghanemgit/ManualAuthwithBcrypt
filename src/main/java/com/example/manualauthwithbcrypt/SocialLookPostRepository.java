package com.example.manualauthwithbcrypt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialLookPostRepository extends JpaRepository<SocialLookPost,Long> {

}
