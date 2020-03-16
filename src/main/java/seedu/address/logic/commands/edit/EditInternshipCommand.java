package seedu.address.logic.commands.edit;

import seedu.address.commons.core.index.Index;

/**
 * Edits an Internship Item in the address book.
 */
public class EditInternshipCommand extends EditCommand {
    /**
     * @param index                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditInternshipCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        super(index, editPersonDescriptor);
    }
}