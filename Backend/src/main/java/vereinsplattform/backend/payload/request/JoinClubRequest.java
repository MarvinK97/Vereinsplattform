package vereinsplattform.backend.payload.request;

import javax.validation.constraints.NotBlank;

public class JoinClubRequest {

    @NotBlank
    private Long clubId;

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

}
