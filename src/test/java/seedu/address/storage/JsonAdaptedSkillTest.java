package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LEVEL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LEVEL_BASIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_NAME_REACT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.storage.JsonAdaptedSkill.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.field.Level;
import seedu.address.model.item.field.Name;
import seedu.address.testutil.SkillBuilder;
import seedu.address.testutil.TypicalSkill;

public class JsonAdaptedSkillTest {
    private List<JsonAdaptedTag> emptyTags = new ArrayList<>();
    private List<JsonAdaptedTag> tags = Stream.of(VALID_TAG_FRONTEND, VALID_TAG_TECH)
            .map(JsonAdaptedTag::new).collect(Collectors.toList());

    @Test
    public void toModelType_validSkill_returnsSkill() throws IllegalValueException {
        // Without tags
        JsonAdaptedSkill jsonAdaptedSkill =
                new JsonAdaptedSkill(new SkillBuilder(TypicalSkill.REACT).withTags().build());
        assertEquals(new SkillBuilder(TypicalSkill.REACT).withTags().build(), jsonAdaptedSkill.toModelType());

        jsonAdaptedSkill =
                new JsonAdaptedSkill(VALID_SKILL_NAME_REACT, "1", LEVEL_BASIC, emptyTags);
        assertEquals(new SkillBuilder(TypicalSkill.REACT).withTags().build(), jsonAdaptedSkill.toModelType());

        // With tags
        jsonAdaptedSkill =
                new JsonAdaptedSkill(new SkillBuilder(TypicalSkill.REACT).build());
        assertEquals(new SkillBuilder(TypicalSkill.REACT).build(), jsonAdaptedSkill.toModelType());

        jsonAdaptedSkill =
                new JsonAdaptedSkill(VALID_SKILL_NAME_REACT, "1", LEVEL_BASIC, tags);
        assertEquals(new SkillBuilder(TypicalSkill.REACT).build(), jsonAdaptedSkill.toModelType());
    }

    @Test
    public void toModelType_missingName_throwsIllegalValueException() {
        JsonAdaptedSkill jsonAdaptedSkill =
                new JsonAdaptedSkill(null,
                        "1", LEVEL_BASIC, emptyTags);
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()),
                jsonAdaptedSkill::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedSkill jsonAdaptedSkill =
                new JsonAdaptedSkill(INVALID_NAME_DESC,
                        "1", LEVEL_BASIC, emptyTags);
        assertThrows(IllegalValueException.class,
                Name.MESSAGE_CONSTRAINTS,
                jsonAdaptedSkill::toModelType);
    }

    @Test
    public void toModelType_missingLevel_throwsIllegalValueException() {
        JsonAdaptedSkill jsonAdaptedSkill =
                new JsonAdaptedSkill(VALID_SKILL_NAME_REACT,
                        "1", null, emptyTags);
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Level.class.getSimpleName()),
                jsonAdaptedSkill::toModelType);
    }

    @Test
    public void toModelType_invalidLevel_throwsIllegalValueException() {
        JsonAdaptedSkill jsonAdaptedSkill =
                new JsonAdaptedSkill(VALID_SKILL_NAME_REACT,
                        "1", INVALID_LEVEL_DESC, emptyTags);
        assertThrows(IllegalValueException.class,
                Level.MESSAGE_CONSTRAINTS,
                jsonAdaptedSkill::toModelType);
    }

    @Test
    public void toModelType_missingId_throwsIllegalValueException() {
        JsonAdaptedSkill jsonAdaptedSkill =
                new JsonAdaptedSkill(VALID_SKILL_NAME_REACT,
                        null, LEVEL_BASIC, emptyTags);
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, "Id"),
                jsonAdaptedSkill::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedSkill jsonAdaptedSkill =
                new JsonAdaptedSkill(VALID_SKILL_NAME_REACT,
                        "-1", LEVEL_BASIC, emptyTags);
        assertThrows(IllegalValueException.class,
                "The id field must not be negative.",
                jsonAdaptedSkill::toModelType);
        jsonAdaptedSkill =
                new JsonAdaptedSkill(VALID_SKILL_NAME_REACT,
                        "abc", LEVEL_BASIC, emptyTags);
        assertThrows(IllegalValueException.class,
                "The id field can only be an integer.",
                jsonAdaptedSkill::toModelType);
    }

    @Test
    public void equals() {
        JsonAdaptedSkill jsonAdaptedReact = new JsonAdaptedSkill(new SkillBuilder(TypicalSkill.REACT).build());
        JsonAdaptedSkill jsonAdaptedGit = new JsonAdaptedSkill(new SkillBuilder(TypicalSkill.GIT).build());

        // Two constructors gives the same result
        assertEquals(jsonAdaptedReact,
                new JsonAdaptedSkill(VALID_SKILL_NAME_REACT, "1", LEVEL_BASIC, tags));

        // Different json adapted notes are not equal
        assertNotEquals(jsonAdaptedReact, jsonAdaptedGit);
    }
}
