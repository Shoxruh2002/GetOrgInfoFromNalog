package uz.atm.model.juridicInfoResponse.faunders;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uz.atm.model.base.Auditable;

import jakarta.persistence.*;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/27/22 10:01 AM
 **/
@Entity( name = "founders" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table( schema = "org_info", name = "founders" )
public class Founders extends Auditable {

    @JsonProperty("id")
    private String dsqId;

    @Embedded
    private FounderIndividual founderIndividual;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "founder_legal_id" )
    private FounderLegal founderLegal;

    @Embedded
    private FounderContact founderContact; // TODO: 12/26/22

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "founder_address_id" )
    private FounderAddress founderAddress;

    private Double budgetPercent;

    private Integer budgetLevelId;

    @Data
    @Embeddable
    public static class FounderContact {

        private String phone;

        private String email;

    }

}
