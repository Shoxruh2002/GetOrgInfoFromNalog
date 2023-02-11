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
 * Time: 11:42
 */

@Entity( name = "contacts" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( schema = "org_info", name = "contacts" )
public class Contact extends Auditable {

    private String phone;

    private String email;
}
