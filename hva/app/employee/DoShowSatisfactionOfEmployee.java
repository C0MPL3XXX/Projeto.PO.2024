package hva.app.employee;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeId = Form.requestString(Prompt.employeeKey());

    if (!_receiver.containsEmployee(employeeId)) {
      throw new UnknownEmployeeKeyException(employeeId);
    }

    _display.add(Math.round(_receiver.calculateEmployeeSatisfaction(employeeId)));
    _display.display();
  }
}
