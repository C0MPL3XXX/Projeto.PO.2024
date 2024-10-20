package hva.app.employee;

import hva.app.exception.DuplicateEmployeeKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeId = Form.requestString(Prompt.employeeName());
    if(_receiver.containsEmployee(employeeId)){
      throw new DuplicateEmployeeKeyException(employeeId);
    }

    String employeeName = Form.requestString(Prompt.employeeName());

    String type = Form.requestString(Prompt.employeeType());

    _receiver.registerEmployee(employeeId, employeeName, type);
  }
}
