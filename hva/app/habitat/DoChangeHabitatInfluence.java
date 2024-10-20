package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(Prompt.habitatKey());
    if (!_receiver.containsHabitat(habitatId)) {
      throw new UnknownHabitatKeyException(habitatId);
    }

    String speciesId = Form.requestString(hva.app.animal.Prompt.speciesKey());
    if (!_receiver.containsSpecies(speciesId)) {
      throw new UnknownSpeciesKeyException(speciesId);
    }

    String influence = Form.requestOption(Prompt.habitatInfluence(), "POS", "NEG", "NEU");

    _receiver.setHabitatSpeciesImpact(habitatId, speciesId, influence);
  }
}
