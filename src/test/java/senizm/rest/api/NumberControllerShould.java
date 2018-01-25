package senizm.rest.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import senizm.rest.api.exception.ExistingNumberValueException;
import senizm.rest.api.exception.NumberValueNotFoundException;
import senizm.rest.api.repository.NumberRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NumberControllerShould {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private NumberRepository numberRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		numberRepository.deleteAll();
	}
	
	@Test public void 
	insert_a_given_value() throws Exception {
		mockMvc.perform(put("/numbers?value=0"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value").value("0"));
	}
	
	@Test(expected = ExistingNumberValueException.class) public void
	throw_error_if_insert_an_existing_value() throws Exception {
		mockMvc.perform(put("/numbers?value=500"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value").value("500"));
		
		mockMvc.perform(put("/numbers?value=500"))
			.andExpect(status().is5xxServerError());
	}
	
	@Test public void 
	list_all_values_with_default_sort_asc() throws Exception {
		mockMvc.perform(put("/numbers?value=1"));
		mockMvc.perform(put("/numbers?value=2"));
		mockMvc.perform(put("/numbers?value=3"));
		
		mockMvc.perform(get("/numbers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(3)))
				.andExpect(jsonPath("$[0].value").value("1"));
	}
	
	@Test public void 
	list_all_values_with_sort_parameter_asc() throws Exception {
		mockMvc.perform(put("/numbers?value=1"));
		mockMvc.perform(put("/numbers?value=2"));
		mockMvc.perform(put("/numbers?value=3"));
		
		mockMvc.perform(get("/numbers?sortType=asc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(3)))
				.andExpect(jsonPath("$[0].value").value("1"));
	}
	
	@Test public void 
	list_all_values_with_sort_parameter_desc() throws Exception {
		mockMvc.perform(put("/numbers?value=1"));
		mockMvc.perform(put("/numbers?value=2"));
		mockMvc.perform(put("/numbers?value=3"));
		
		mockMvc.perform(get("/numbers?sortType=desc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(3)))
				.andExpect(jsonPath("$[0].value").value("3"));
	}
	
	@Test public void 
	return_max_value_of_all_inserted() throws Exception {
		mockMvc.perform(put("/numbers?value=1"));
		mockMvc.perform(put("/numbers?value=2"));
		mockMvc.perform(put("/numbers?value=3"));
		mockMvc.perform(delete("/numbers?value=3"));

		mockMvc.perform(get("/numbers/max"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value").value("3"));
	}
	
	@Test public void 
	return_min_value_of_all_inserted() throws Exception {
		mockMvc.perform(put("/numbers?value=1"));
		mockMvc.perform(delete("/numbers?value=1"));
		mockMvc.perform(put("/numbers?value=2"));
		mockMvc.perform(put("/numbers?value=3"));

		mockMvc.perform(get("/numbers/min"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value").value("1"));
	}
	
	@Test public void 
	delete_an_existing_value() throws Exception {
		mockMvc.perform(put("/numbers?value=1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value").value("1"));
		
		mockMvc.perform(get("/numbers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(1)));

		mockMvc.perform(delete("/numbers?value=1"))
				.andExpect(status().isOk());
		
		mockMvc.perform(get("/numbers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(0)));
	}
	
	@Test(expected = NumberValueNotFoundException.class) public void 
	throw_error_if_delete_a_nonexisting_value() throws Exception {
		
		mockMvc.perform(get("/numbers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(0)));

		mockMvc.perform(delete("/numbers?value=1"))
				.andExpect(status().is5xxServerError());
	}
}