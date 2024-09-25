package in.mvc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import in.mvc.entity.CardsTable;

@Repository
public interface CardsRepository extends JpaRepository<CardsTable, Long>, JpaSpecificationExecutor<CardsTable> {

}
