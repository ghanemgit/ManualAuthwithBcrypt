package com.example.manualauthwithbcrypt;


import lombok.*;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class SocialLooker {

    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;


    @OneToMany(mappedBy = "socialLooker")
    private Set<SocialLookPost> socialLookPost;


}
