package vn.tourism.beta.entity.composedId;

import lombok.Data;

import java.io.Serializable;

@Data
public class TourRegisterId implements Serializable{
    private int userId;
    private int tourDetailId;
    public int hashCode() {
        return (int)(userId + tourDetailId);
    }

    public boolean equals(Object object) {
        if (object instanceof TourRegisterId) {
            TourRegisterId otherId = (TourRegisterId) object;
            return (otherId.userId == this.userId) && (otherId.tourDetailId == this.tourDetailId);
        }
        return false;
    }
}
