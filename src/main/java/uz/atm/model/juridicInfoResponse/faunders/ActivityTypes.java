package uz.atm.model.juridicInfoResponse.faunders;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uz.atm.model.base.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/26/22 12:47 PM
 **/

@Entity( name = "activity_types" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table( schema = "org_info", name = "activity_types" )
public class ActivityTypes extends Auditable {

    private String dsqId;

    private Integer code;

    private String name;

    @JsonProperty( "name_ru" )
    private String nameRu;

    @JsonProperty( "name_uz_cyrl" )
    private String nameUzCyrl;

    @JsonProperty( "name_uz_latn" )
    private String nameUzLatn;

    @JsonProperty( "level_id" )
    private Integer levelId;


}
