package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

public class ListInternshipCommand extends ListCommand {

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setItemsToDisplay("int");
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult("", String.format(MESSAGE_SUCCESS, "Internship"));
    }

}