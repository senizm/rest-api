package senizm.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import senizm.rest.api.entity.Number;
import senizm.rest.api.exception.ExistingNumberValueException;
import senizm.rest.api.exception.NumberValueNotFoundException;
import senizm.rest.api.service.NumberService;

/**
 * <b>Project api</b><br />
 * NumberController.java<br />
 *
 * @author senizm
 */
@RestController
public class NumberController {
	
	@Autowired
	private NumberService numberService;

	/**
	 * list
	 *
	 * @param sortType
	 * @return
	 * 
	 * @author senizm
	 */
	@RequestMapping(value = "/numbers", method = RequestMethod.GET)
    public Iterable<Number> list(@RequestParam(value="sortType", required=false) String sortType) {
        return this.numberService.listAll(sortType);
    }	
	
	/**
	 * save
	 *
	 * @param number
	 * @return
	 * 
	 * @author senizm
	 * @throws ExistingNumberValueException 
	 */
	@RequestMapping(value = "/numbers", method = RequestMethod.PUT)
    public Number save(@RequestParam(value="value") Integer value) throws ExistingNumberValueException {
        return this.numberService.save(value);
    }
	
	/**
	 * delete
	 *
	 * @param number
	 * 
	 * @author senizm
	 * @throws NumberValueNotFoundException 
	 */
	@RequestMapping(value = "/numbers", method = RequestMethod.DELETE)
    public void delete(@RequestParam Integer value) throws NumberValueNotFoundException {
        this.numberService.delete(value);
    }
	
	/**
	 * max
	 *
	 * @return
	 * 
	 * @author senizm
	 */
	@RequestMapping(value = "/numbers/max", method = RequestMethod.GET)
    public Number max() {
        return this.numberService.findMax();
    }	
	
	/**
	 * min
	 *
	 * @return
	 * 
	 * @author senizm
	 */
	@RequestMapping(value = "/numbers/min", method = RequestMethod.GET)
    public Number min() {
        return this.numberService.findMin();
    }
}
