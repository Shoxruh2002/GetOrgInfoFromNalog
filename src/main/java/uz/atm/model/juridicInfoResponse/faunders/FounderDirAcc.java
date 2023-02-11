package uz.atm.model.juridicInfoResponse.faunders;

import lombok.*;
import uz.atm.model.base.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 23/08/22
 * Time: 11:36
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "founder_dir_acc")
@Table( schema = "org_info", name = "founder_dir_acc" )
public class FounderDirAcc extends Auditable {

    private String lastName;//

    private String firstName;//

    private String middleName;//

    private Integer gender;//

    private Integer nationality;

    private Integer citizenship;

    private String passportSeries;

    private String passportNumber;

    private Long pinfl;//

    private Long tin;//

    private String birthDate;

    private String individualId;

    private String countryCode;

}
