package vn.tourism.beta.entity.composedId;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserAuthorityId implements Serializable{
    @Column(name = "userId")
    private Long userId;
    @Column(name = "authorityId")
    private Long authorityId;
    private UserAuthorityId(){}
    private UserAuthorityId(Long userId, Long authorityId){
        this.userId = userId;
        this.authorityId = authorityId;
    }
    public int hashCode() {
        return (int)(userId + authorityId);
    }

    public boolean equals(Object object) {
        if (object instanceof UserAuthorityId) {
            UserAuthorityId otherId = (UserAuthorityId) object;
            return (otherId.userId == this.userId) && (otherId.authorityId == this.authorityId);
        }
        return false;
    }
}
