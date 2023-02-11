package uz.atm.model.juridicInfoResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm.model.base.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 23/08/22
 * Time: 11:50
 */

@Entity( name = "company_banks" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( schema = "org_info", name = "company_banks" )
public class CompanyBank extends Auditable {

    private String mfo;

    private String paymentAccount;

    private Integer status;

    private String openDate;

    private String closeDate;

    private Integer attribute;

    private Integer reasonCode;

    private String currencyCode;
}
