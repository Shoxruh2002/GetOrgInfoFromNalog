package uz.atm.model.juridicInfoResponse.faunders;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/26/22 12:40 PM
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class BusinessStructureDetail {

    @Column(name = "business_str_detail_code")
    private Integer code;

    @Column(name = "business_str_detail_name")
    private String name;

    @Column(name = "business_str_detail_name_ru")
    @JsonProperty("name_ru")
    private String nameRu;

    @Column(name = "business_str_detail_name_uz_cyrl")
    @JsonProperty("name_uz_cyrl")
    private String nameUzCyrl;

    @Column(name = "business_str_detail_name_uz_latn")
    @JsonProperty("name_uz_latn")
    private String nameUzLatn;

}
