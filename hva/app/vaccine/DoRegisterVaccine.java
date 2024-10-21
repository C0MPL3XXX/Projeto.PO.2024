package hva.app.vaccine;

import hva.app.exception.DuplicateVaccineKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    //FIXME add command fields
  }

  @Override
  protected final void execute() throws CommandException {
    String vaccineId = Form.requestString(Prompt.vaccineKey());
    if (_receiver.containsVaccine(vaccineId)) {
      throw new DuplicateVaccineKeyException(vaccineId);
    }

    String vaccineName = Form.requestString(Prompt.vaccineName());
    String speciesList = Form.requestString(Prompt.listOfSpeciesKeys());

    _receiver.registerVaccine(vaccineId, vaccineName, speciesList.split(","));
  }
}
