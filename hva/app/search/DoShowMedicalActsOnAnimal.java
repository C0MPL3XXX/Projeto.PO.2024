package hva.app.search;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

  DoShowMedicalActsOnAnimal(Hotel receiver) {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    //FIXME add command fields
  }

  @Override
  protected void execute() throws CommandException {
    //FIXME implement command
    String id = Form.requestString(hva.app.animal.Prompt.animalKey());
    if(!_receiver.containsAnimal(id)){
      throw new UnknownAnimalKeyException(id);
    }
    else {
      _display.addAll(_receiver.getHealth(id));
      _display.display();
    }
  }
}
