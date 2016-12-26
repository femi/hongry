package application;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


/**
 * 
 * This class allows messages to be sent using Twilio's messaging API.
 * I used some of the code from the Twilio QuickStart page to get started.
 * 
 * https://www.twilio.com/docs/quickstart?filter-language=java
 * 
 * @author femi
 *
 */
public class Messages {
		
		// phone number of the user
		private static String number;
		
		// ACCOUNT_SID and AUTH_TOKEN found at twilio.com/console
	    private static String ACCOUNT_SID;
		private static String AUTH_TOKEN;
	    private static String FROM_NUMBER;
	    
	    /**
	     * 
	     * This sends the text to the number with the order summary.
	     * 
	     * @param phoneNumber that message will be sent to
	     * @param order is the order summary message 
	     */
	    public static void sendOrder(String phoneNumber, String order) {
	    	 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	    	 @SuppressWarnings("unused")
	    	 Message message = Message
		                .creator(new PhoneNumber(phoneNumber),  // to
		                         new PhoneNumber(FROM_NUMBER),  // from
		                         order)
		                .create();
	    }
	    
	    /**
	     * @return the phone number of the user
	     */
	    public static String getNumber() {
			return number;
		}

		/**
		 * Sets the phone number of the user.
		 * @param number This is the user's UK phone number.
		 */
		public static void setNumber(String number) {
			Messages.number = number;
		}
	    
		/**
		 * 
		 * Sets the Twilio account SID.
		 * 
		 * @param accountSID
		 */
		public static void setACCOUNT_SID(String accountSID) {
			ACCOUNT_SID = accountSID;
		}

		/**
		 * 
		 * Sets the Twilio Application auth token. 
		 * 
		 * @param authToken
		 */
		public static void setAUTH_TOKEN(String authToken) {
			AUTH_TOKEN = authToken;
		}

		/**
		 * 
		 * Sets the Twilio number.
		 * 
		 * @param fromNumber
		 */
		public static void setFROM_NUMBER(String fromNumber) {
			FROM_NUMBER = fromNumber;
		}
	   
	    
}
