package seedu.us.among.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.us.among.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.us.among.commons.exceptions.IllegalValueException;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Email;
import seedu.us.among.model.endpoint.Name;
import seedu.us.among.model.endpoint.Phone;
import seedu.us.among.testutil.Assert;
import seedu.us.among.testutil.TypicalEndpoints;

public class JsonAdaptedEndpointTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TypicalEndpoints.BENSON.getName().toString();
    private static final String VALID_PHONE = TypicalEndpoints.BENSON.getPhone().toString();
    private static final String VALID_EMAIL = TypicalEndpoints.BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = TypicalEndpoints.BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalEndpoints.BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEndpointDetails_returnsEndpoint() throws Exception {
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(TypicalEndpoints.BENSON);
        Assertions.assertEquals(TypicalEndpoints.BENSON, endpoint.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint =
                new JsonAdaptedEndpoint(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedEndpoint.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint =
                new JsonAdaptedEndpoint(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedEndpoint.MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint =
                new JsonAdaptedEndpoint(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedEndpoint.MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint =
                new JsonAdaptedEndpoint(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedEndpoint endpoint = new JsonAdaptedEndpoint(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedEndpoint.MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, endpoint::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedEndpoint endpoint =
                new JsonAdaptedEndpoint(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags);
        Assert.assertThrows(IllegalValueException.class, endpoint::toModelType);
    }

}
