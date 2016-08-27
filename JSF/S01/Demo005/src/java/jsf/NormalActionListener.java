/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/**
 *
 * @author Gustavo
 */
public class NormalActionListener implements ActionListener{

	public void processAction(ActionEvent event) throws AbortProcessingException {
		System.out.println("Any use case here?");
	}
	
}
