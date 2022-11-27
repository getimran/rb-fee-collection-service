package ae.rakbank.fee.collect.repository;


import ae.rakbank.fee.collect.model.FeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author imran
 * Repository Layer for Fee Collection Microservice
 */
public interface FeeCollectionRepository extends JpaRepository<FeeDetail, String> {

    public List<FeeDetail> findAllByStudentId(Integer studentId);
}
