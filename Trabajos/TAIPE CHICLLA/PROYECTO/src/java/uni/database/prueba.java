/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.database;

import java.sql.Connection;

public class prueba {

    public static void main(String[] args) {
        try {
            Connection cn = AccesoDB.getConnection();
            System.out.println("Conexion conforme....");
        } catch (Exception e) {
            System.out.println("error :" + e.getMessage());
        }

    }
}
