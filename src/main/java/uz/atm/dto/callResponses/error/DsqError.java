package uz.atm.dto.callResponses.error;

import lombok.*;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 03/10/22
 * Time: 10:31
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DsqError {

    private String path;

    private String error;

    private Long timestamp;

    private Integer status;
}
