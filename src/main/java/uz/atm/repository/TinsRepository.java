package uz.atm.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.atm.model.Tins;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/28/22 10:53 AM
 **/
public interface TinsRepository extends JpaRepository<Tins, Long> {


    List<Tins> findAllByIsDoneFalse();


    @Modifying
    @Transactional
    @Query( "update Tins t set t.isDone =true where t.tin = ?1" )
    void updateIsDone(@Param( "inn" ) String inn);
}
