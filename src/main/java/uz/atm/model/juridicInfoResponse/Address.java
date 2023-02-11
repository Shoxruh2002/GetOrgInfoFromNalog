package uz.atm.model.juridicInfoResponse;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm.model.base.Auditable;




/**
 * Author: Bekpulatov Shoxruh
 * Date: 23/08/22
 * Time: 11:46
 */

@Entity(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( schema = "org_info", name = "addresses" )
public class Address extends Auditable {

    private String countryCode;

    private String soatoCode;

    private String village;

    private String sectorCode;

    private String streetName;

    private String house;

    private String flat;

    private Integer postcode;

    private String cadastreNumber;

}
