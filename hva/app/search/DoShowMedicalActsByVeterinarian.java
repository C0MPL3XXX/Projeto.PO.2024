package hva.app.search;

import hva.app.exception.UnknownVeterinarianKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    //FIXME implement command
    String id = Form.requestString(hva.app.employee.Prompt.employeeKey());
    if(!_receiver.containsEmployee(id)){
      throw new UnknownVeterinarianKeyException(id);
    }
    else {
      _display.addAll(_receiver.getVetWork(id));
      _display.display();
    }
  }
}
