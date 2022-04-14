package exceptions;


@SuppressWarnings("serial")
public class UwUException extends Exception {
	private String[] emotes = new String[] {
			"rawr :3",
			"OwO",
			"UwU",
			">w<",
			"^-^",
			"xD",
			"(^.^)",
			"nyaa~~",
	};
	public UwUException(String message, Throwable err) throws Exception {
		String emote = getEmote();
		message = message.replaceAll("l", "w");
		message = message.replaceAll("c", "cw");
		message += emote;
		throw new UwUExceptionManager(message, err);
	}

	private String getEmote() {
		return emotes[(int) (Math.floor(Math.random()) * emotes.length)];
	}
	
}
