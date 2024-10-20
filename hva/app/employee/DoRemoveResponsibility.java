package hva.app.employee;

import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeId = Form.requestString(Prompt.employeeKey());
    if(_receiver.containsEmployee(employeeId)){
      throw new UnknownEmployeeKeyException(employeeId);
    }

    String responsibility = Form.requestString(Prompt.responsibilityKey());
    if(!_receiver.employeeContainsResponsibility(employeeId, responsibility)){
      throw new NoResponsibilityException(employeeId, responsibility);
    }

    _receiver.removeEmployeeResponsibility(employeeId, responsibility);
  }
}
