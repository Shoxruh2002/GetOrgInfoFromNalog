package uz.atm.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 22/07/22
 * Time: 09:55
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class SubAuditable extends Auditable {

    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column
    private Long createdBy;

    @Column(columnDefinition = "bool default 'false'")
    private boolean deleted = false;

}
