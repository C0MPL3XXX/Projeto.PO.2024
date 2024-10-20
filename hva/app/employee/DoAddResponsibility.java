package hva.app.employee;

import hva.app.exception.NoResponsibilityException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    //FIXME implement command
    String employeeId = Form.requestString(Prompt.employeeKey());
    String responsibilityId = Form.requestString(Prompt.responsibilityKey());

    if (!_receiver.employeeContainsResponsibility(employeeId, responsibilityId)) {
      throw new NoResponsibilityException(employeeId, responsibilityId);
    }

    _receiver.removeEmployeeResponsibility(employeeId, responsibilityId);
  }
}
