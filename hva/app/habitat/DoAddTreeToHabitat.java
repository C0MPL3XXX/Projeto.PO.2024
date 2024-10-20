package hva.app.habitat;

import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(Prompt.habitatKey());
    if (!_receiver.containsHabitat(habitatId)) {
      throw new UnknownHabitatKeyException(habitatId);
    }

    String treeId = Form.requestString(Prompt.treeKey());
    if (_receiver.containsTree(treeId)) {
      throw new DuplicateTreeKeyException(habitatId);
    }

    String treeName = Form.requestString(Prompt.treeName());
    int treeAge = Form.requestInteger(Prompt.treeAge());
    int treeDifficulty = Form.requestInteger(Prompt.treeDifficulty());
    String treeType = Form.requestOption(Prompt.treeType(), "PERENE", "CADUCA");

    String treeString = _receiver.registerTree(treeId, treeName, treeType, treeAge, treeDifficulty);

    _receiver.addTreeToHabitat(habitatId, treeId);

    _display.add(treeString);
    _display.display();
  }
}
