package vereinsplattform.backend.dto.request;

import javax.validation.constraints.NotBlank;

public class CreateNotificationRequest {

    @NotBlank
    private String message;

    @NotBlank
    private Long clubid;

    public Long getClubid() {
        return clubid;
    }

    public void setClubid(Long clubid) {
        this.clubid = clubid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
