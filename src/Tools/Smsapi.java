/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import com.twilio.Twilio;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 * @author MSI
 */
public class Smsapi {

    public static final String ACCOUNT_SID = "AC8ab524692dbb8053978f0045f3f626eb";
    public static final String AUTH_TOKEN = "c1a40c3211132dd55808e93137d43971";

    public static void sendSMS(String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(/*num ili bch yjih il msg */new PhoneNumber("+21696227122"),new PhoneNumber("+18066020019"), msg).create();

        System.out.println(message.getSid());

    }
}
