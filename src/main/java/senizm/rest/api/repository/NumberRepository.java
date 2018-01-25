package senizm.rest.api.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import senizm.rest.api.entity.Number;

/**
 * <b>Project api</b><br />
 * NumberRepository.java<br />
 *
 * @author senizm
 */
public interface NumberRepository extends CrudRepository<Number, Integer> {
    
    /**
     * findAllByDeleted
     *
     * @param deleted
     * @param sort
     * @return returns all records except deleted ones
     * 
     * @author senizm
     */
    Iterable<Number> findAllByDeleted(Boolean deleted, Sort sort);


    /**
     * findOneByDeletedAndValue
     *
     * @param deleted
     * @param value
     * @return
     * 
     * @author senizm
     */
    Number findOneByDeletedAndValue(Boolean deleted, Integer value);
    
    /**
     * findTopByOrderByValueDesc
     *
     * @return returns the max value inserted ever (including deleted values)
     * 
     * @author senizm
     */
    Number findTopByOrderByValueDesc();
    
    /**
     * findTopByOrderByValueAsc
     *
     * @return returns the min value inserted ever (including deleted values)
     * 
     * @author senizm
     */
    Number findTopByOrderByValueAsc();
}
