package senizm.rest.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import senizm.rest.api.entity.Number;
import senizm.rest.api.exception.ExistingNumberValueException;
import senizm.rest.api.exception.NumberValueNotFoundException;
import senizm.rest.api.repository.NumberRepository;

import java.util.Date;

/**
 * <b>Project api</b><br />
 * NumberService.java<br />
 *
 * @author senizm
 */
@Service
public class NumberService {
	@Autowired
	private NumberRepository numberRepository;

	/**
	 * listAll
	 *
	 * @param sortType
	 * @return 
	 * 
	 * @author senizm
	 */
	public Iterable<Number> listAll(String sortType) {
		return this.numberRepository.findAllByDeleted(Boolean.FALSE, new Sort(Sort.Direction.DESC.toString().equalsIgnoreCase(sortType) ? Sort.Direction.DESC : Sort.Direction.ASC, "value"));
	}
	
	/**
	 * save
	 *
	 * @param value
	 * @return
	 * 
	 * @author senizm
	 * @throws ExistingNumberValueException 
	 */
	public Number save(Integer value) throws ExistingNumberValueException {				
		Number number = this.numberRepository.findOneByDeletedAndValue(Boolean.FALSE, value);
		
		if (number != null) {
			throw new ExistingNumberValueException();
		}
		
		number = new Number();
		number.setValue(value);
		number.setInsertDate(new Date());
		return this.numberRepository.save(number);
	}
	
	/**
	 * findMax
	 *
	 * @return
	 * 
	 * @author senizm
	 */
	public Number findMax() {
		return this.numberRepository.findTopByOrderByValueDesc();
	}
	
	/**
	 * findMin
	 *
	 * @return
	 * 
	 * @author senizm
	 */
	public Number findMin() {
		return this.numberRepository.findTopByOrderByValueAsc();
	}
	
	/**
	 * delete
	 *
	 * @param value
	 * 
	 * @author senizm
	 * @throws NumberValueNotFoundException 
	 */
	public void delete(Integer value) throws NumberValueNotFoundException {
		Number number = this.numberRepository.findOneByDeletedAndValue(Boolean.FALSE, value);
		
		if (number == null) {
			throw new NumberValueNotFoundException();
		}
		
		number.setDeleted(Boolean.TRUE);
		this.numberRepository.save(number);
	}
}
