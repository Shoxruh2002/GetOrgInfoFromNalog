package uz.atm.model.juridicInfoResponse.faunders;

import lombok.*;
import uz.atm.model.base.Auditable;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/26/22 12:23 PM
 **/

@Entity(name = "founder_address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table( schema = "org_info", name = "founder_address" )
public class FounderAddress extends Auditable {

    private String dsqId;

    private String countryCode;

    @Embedded
    private Region region;

    @Embedded
    private District district;

    private String sectorCode;

    private String village;

    private String streetName;

    private String house;

    private String flat;

    private String soatoCode;

}
