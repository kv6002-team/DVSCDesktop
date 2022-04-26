package logging;

import connection.Connection;
/**
 * Logger class for providing types and levels and then runs the log method in connection
 * @author Scrub
 *
 */
public class Logger {
	private static Connection con = new Connection();
	public static enum TYPE {
		LOGIN_EVENT,
		MESSAGE_EVENT,
		DATA_CREATED_EVENT,
		DATA_UPDATED_EVENT,
		DATA_DELETED_EVENT,
		CONNECTION_EVENT
	}
	public static enum LEVEL {
		INFO,
		ALERT,
		WARN,
		CRITICAL
	}
	public static void log(TYPE logType,LEVEL logLevel, String message) {
		con.log(logType, logLevel, message);
	}
}
