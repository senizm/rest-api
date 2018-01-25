package senizm.rest.api.exception;

import org.springframework.web.util.NestedServletException;

/**
 * <b>Project api</b><br />
 * ExistingNumberValueException.java<br />
 *
 * @author senizm
 */
public class ExistingNumberValueException extends NestedServletException  {

	private static final long serialVersionUID = 7686376436256896756L;
	private static final String errorMessage = "Number value exists!";

	/**
	 * ExistingNumberValueException<br />
	 *
	 * 
	 * @author senizm
	 */
	public ExistingNumberValueException()
	{
		super(ExistingNumberValueException.errorMessage);
	}
	
	/**
	 * ExistingNumberValueException<br />
	 *
	 * @param argMessage
	 * 
	 * @author senizm
	 */
	public ExistingNumberValueException(String argMessage)
	{
		super(argMessage);
	}
	
	/**
	 * ExistingNumberValueException<br />
	 *
	 * @param argMessage
	 * @param argCause
	 * 
	 * @author senizm
	 */
	public ExistingNumberValueException(String argMessage, Throwable argCause)
	{
		super(argMessage, argCause);
	}
}
