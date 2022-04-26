/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Tools.MyConnexion;
import java.sql.Connection;


public class UserService  {

    Connection cnx;

    public UserService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

   

}
