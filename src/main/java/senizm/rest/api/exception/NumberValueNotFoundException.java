package senizm.rest.api.exception;

import org.springframework.web.util.NestedServletException;

/**
 * <b>Project api</b><br />
 * NumberValueNotFoundException.java<br />
 *
 * @author senizm
 */
public class NumberValueNotFoundException extends NestedServletException {

	private static final long serialVersionUID = 7686376436256896756L;
	private static final String errorMessage = "Number value not found!";
	
	/**
	 * NumberValueNotFoundException<br />
	 *
	 * 
	 * @author senizm
	 */
	public NumberValueNotFoundException()
	{
		super(NumberValueNotFoundException.errorMessage);
	}

	/**
	 * NumberValueNotFoundException<br />
	 *
	 * @param argMessage
	 * 
	 * @author senizm
	 */
	public NumberValueNotFoundException(String argMessage)
	{
		super(argMessage);
	}
	
	/**
	 * NumberValueNotFoundException<br />
	 *
	 * @param argMessage
	 * @param argCause
	 * 
	 * @author senizm
	 */
	public NumberValueNotFoundException(String argMessage, Throwable argCause)
	{
		super(argMessage, argCause);
	}
}
