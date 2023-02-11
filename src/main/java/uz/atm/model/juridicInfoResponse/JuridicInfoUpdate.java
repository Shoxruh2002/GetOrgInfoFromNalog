package uz.atm.model.juridicInfoResponse;

import lombok.*;
import uz.atm.model.base.Auditable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import java.util.Date;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/26/22 5:40 PM
 **/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JuridicInfoUpdate extends Auditable {

    private String sendType;

    @Embedded
    private JuridicData data;

    private String transactionId;

    private Date date;

    private Boolean success;

    private String projectType;

//    @Column(columnDefinition = "DEFAULT false")
    private Boolean updated = false;

    @Data
    @Embeddable
    public static class JuridicData {

        private Long tin;

    }

}
