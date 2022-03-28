package com.example.manualauthwithbcrypt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialLookerRepository extends JpaRepository<SocialLooker,Long> {

    SocialLooker findSocialLookerByUsername(String username);


}
