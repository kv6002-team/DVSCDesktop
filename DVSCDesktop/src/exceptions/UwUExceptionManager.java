package exceptions;

@SuppressWarnings("serial")
public class UwUExceptionManager extends Exception {
	public UwUExceptionManager (String message, Throwable err) {
		super(message, err);
	}
}
