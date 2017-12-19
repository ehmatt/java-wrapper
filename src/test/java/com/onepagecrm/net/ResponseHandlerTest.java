package com.onepagecrm.net;

import com.onepagecrm.BaseTest;
import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.exceptions.AuthenticationException;
import com.onepagecrm.exceptions.BadRequestException;
import com.onepagecrm.models.Call;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.User;
import com.onepagecrm.models.internal.FileUtilities;
import com.onepagecrm.models.serializers.BaseSerializer;
import com.onepagecrm.models.serializers.CallSerializer;
import com.onepagecrm.models.serializers.ContactListSerializer;
import com.onepagecrm.models.serializers.ContactSerializer;
import com.onepagecrm.models.serializers.DateSerializer;
import com.onepagecrm.models.serializers.LoginSerializer;

import java.util.logging.Logger;

@SuppressWarnings("unused")
public class ResponseHandlerTest extends BaseTest {

    private static Logger LOG = Logger.getLogger(ResponseHandlerTest.class.getName());

    /**
     * Normal operation of parseLoginResponse() method.
     * <p/>
     * Will normally receive a success message (user enters details correctly).
     * Method will construct User object from JSON.
     * <p/>
     * successResponse and loggedInUser directly map to each other.
     */
    public void testParseLoginResponse_Successful() throws Exception {

        // Set up fabricated User.
        User loggedInUser = new User()
                .setId("556cb8b61787fa02e000047e")
                .setAuthKey("WqLLs1n/Y3SvOpGg5CNOpdKy74GkGI6lnhwSfYmgNl4=")
                .setFirstName("Cillian")
                .setLastName("Myles")
                .setAccountType("trial")
                .setEmail("cillian.college@gmail.com")
                .setCompanyName("Myles Inc.")
                .setBccEmail("556cb8b61787fa02e000047d@users.onepagecrm.com");

        // Set up the fabricated JSON success response
        Response response = Response.okay(
                "{\"status\":0,\"message\":\"OK\",\"timestamp\":1435940522," +
                        "\"data\":{\"user_id\":\"556cb8b61787fa02e000047e\",\"auth_key\":" +
                        "\"WqLLs1n/Y3SvOpGg5CNOpdKy74GkGI6lnhwSfYmgNl4=\",\"account_type\":" +
                        "\"trial\",\"custom_fields\":[{\"custom_field\":{\"id\":" +
                        "\"556cb8c91787fa0e24000043\",\"name\":\"Birthday\",\"type\":\"anniversary\"," +
                        "\"choices\":[],\"position\":0,\"reminder_days\":0}},{\"custom_field\":{" +
                        "\"id\":\"558be3a21787fa26d8000001\",\"name\":\"Spouse_Name\",\"type\":" +
                        "\"single_line_text\",\"choices\":[],\"position\":1,\"reminder_days\":null}}]," +
                        "\"company_fields\":[]," +
                        "\"team\":[],\"settings\":{\"reminder\":{\"type\":\"new_task\",\"hour\":6}," +
                        "\"time_zone\":\"london\",\"date_format\":\"%d/%m/%Y\",\"listing_size\":25," +
                        "\"currency\":\"EUR\",\"currency_symbol\":\"€\",\"popular_countries\":[" +
                        "\"US\",\"IE\",\"GB\"],\"deal_stages\":[{\"stage\":10,\"label\":" +
                        "\"Qualification\"},{\"stage\":25,\"label\":null},{\"stage\":50,\"label\":" +
                        "\"Decision\"},{\"stage\":75,\"label\":null},{\"stage\":90,\"label\":" +
                        "\"Negotiation\"}],\"default_contact_type\":\"company\"," +
                        "\"show_tidy_stream\":false},\"cost_setup\":{},\"user\":{\"user\":{\"id\":" +
                        "\"556cb8b61787fa02e000047e\",\"first_name\":\"Cillian\",\"last_name\":" +
                        "\"Myles\",\"email\":\"cillian.college@gmail.com\",\"company_name\":" +
                        "\"Myles Inc.\",\"photo_url\":\"\",\"bcc_email\":" +
                        "\"556cb8b61787fa02e000047d@users.onepagecrm.com\",\"account_rights\":[" +
                        "\"account_owner\",\"admin\"]}}}}"
        );

        User parsedUser = LoginSerializer.fromResponse(response);

        assertNotNull("User object being set is null", parsedUser);
        assertEquals("User ID not set correctly", loggedInUser.getId(), parsedUser.getId());
        assertEquals("AuthKey not set correctly", loggedInUser.getAuthKey(), parsedUser.getAuthKey());
        assertEquals("AccountType not set correctly", loggedInUser.getAccountType(), parsedUser.getAccountType());
        assertEquals("FirstName not set correctly", loggedInUser.getFirstName(), parsedUser.getFirstName());
        assertEquals("LastName not set correctly", loggedInUser.getLastName(), parsedUser.getLastName());
        assertEquals("Email not set correctly", loggedInUser.getEmail(), parsedUser.getEmail());
        assertEquals("Company not set correctly", loggedInUser.getCompanyName(), parsedUser.getCompanyName());
        assertEquals("BCC Email not set correctly", loggedInUser.getBccEmail(), parsedUser.getBccEmail());
    }

    /**
     * When expired token message received, normal operation is to return a
     * new (empty) Contact object.
     * <p/>
     * This means no fields will be set for this object, check for ID.
     */
    public void testParseLoginResponse_Expired() {
        Response expiredResponse = new Response(
                400,
                "Error",
                "{\"error_name\":\"expired\",\"status\":400,\"message\":" +
                        "\"Expired\",\"error_message\":\"Could not find more helpful message, sorry.\"," +
                        "\"errors\":{}}"
        );

        try {
            LoginSerializer.fromResponse(expiredResponse);

        } catch (APIException exception) {
            // We are expecting this Exception to be thrown.
            assertTrue("Expired token response misinterpreted",
                    exception instanceof BadRequestException);
        }
    }

    /**
     * When no authorization data message received, normal operation is to return a
     * new (empty) Contact object.
     * <p/>
     * This means no fields will be set for this object, check for ID.
     */
    public void testParseLoginResponse_NoAuth() {
        Response noAuthResponse = new Response(
                401,
                "Error",
                "{\"error_name\":\"authorization_data_not_found\"," +
                        "\"status\":401,\"message\":\"Authorization data not found\"," +
                        "\"error_message\":\"Could not find more helpful message, sorry.\"," +
                        "\"errors\":{}}"
        );

        try {
            LoginSerializer.fromResponse(noAuthResponse);

        } catch (APIException exception) {
            // We are expecting this Exception to be thrown.
            assertTrue("No auth token response misinterpreted",
                    exception instanceof AuthenticationException);
        }
    }

    /**
     * Ensure correct operation of Contact parsing method.
     * <p/>
     * Response containing details of only one Contact should only result in
     * the construction of one Contact object.
     */
    public void testParseGetContactsResponse_CreatesContact() throws Exception {
        Response response = Response.okay(
                "{\"status\":0,\"message\":\"OK\",\"timestamp\":1436172718," +
                        "\"data\":{\"contacts\":[{\"contact\":{\"id\":\"55804f6b1787fa72b400002e\"," +
                        "\"owner_id\":\"556cb8b61787fa02e000047e\",\"first_name\":\"Tiger\"," +
                        "\"last_name\":\"Woods\",\"photo_url\":\"\",\"job_title\":\"\",\"background\":" +
                        "\"\",\"urls\":[],\"phones\":[{\"type\":\"work\",\"value\":\"0862524363\"}]," +
                        "\"emails\":[{\"type\":\"work\",\"value\":\"tigerwoods@pga.com\"}],\"status\":" +
                        "\"Lead\",\"status_id\":\"556cb8c91787fa0e24000011\",\"starred\":true," +
                        "\"lead_source_id\":\"advertisement\",\"type\":\"individual\",\"company_name\":" +
                        "\"Myles Inc.\",\"company_id\":\"55892ae31787fa5dbb001aa1\"," +
                        "\"sales_closed_for\":[],\"tags\":[\"subscriber\"],\"custom_fields\":[]," +
                        "\"created_at\":\"2015-06-16T16:31:40.075Z\",\"modified_at\":" +
                        "\"2015-06-30T16:16:20.985Z\",\"address_list\":[{\"address\":\"\",\"city\":\"\"," +
                        "\"state\":\"\",\"zip_code\":\"\",\"country_code\":\"\"}]},\"next_actions\":[{" +
                        "\"id\":\"55804f6c1787fa72b4000030\",\"assignee_id\":\"556cb8b61787fa02e000047e\"," +
                        "\"contact_id\":\"55804f6b1787fa72b400002e\",\"text\":" +
                        "\"Follow up with Tiger, who subscribed to your mailing list\"," +
                        "\"modified_at\":\"2015-06-16T16:31:40.088Z\",\"status\":\"asap\",\"date\":null}]," +
                        "\"next_action\":{\"id\":\"55804f6c1787fa72b4000030\",\"assignee_id\":" +
                        "\"556cb8b61787fa02e000047e\",\"contact_id\":\"55804f6b1787fa72b400002e\"," +
                        "\"text\":\"Follow up with Tiger, who subscribed to your mailing list\"," +
                        "\"modified_at\":\"2015-06-16T16:31:40.088Z\",\"status\":\"asap\",\"date\":null}}]}}"
        );

        ContactList parsed = ContactListSerializer.fromResponse(response);

        assertNotNull("Contact array object mis-constructed / not constructed", parsed);
        assertEquals("Wrong number of Contacts constructed", 1, parsed.size());
    }

    /**
     * Ensure correct operation of Contact parsing method.
     * <p/>
     * Response containing details of two Contacts should only result in
     * the construction two Contact objects.
     */
    public void testParseGetContactsResponse_CreatesContacts() throws Exception {
        Response response = Response.okay(
                "{\"status\":0,\"message\":\"OK\",\"timestamp\":1436172718," +
                        "\"data\":{\"contacts\":[{\"contact\":{\"id\":\"55804f6b1787fa72b400002e\"," +
                        "\"owner_id\":\"556cb8b61787fa02e000047e\",\"first_name\":\"Tiger\"," +
                        "\"last_name\":\"Woods\",\"photo_url\":\"\",\"job_title\":\"\",\"background\":" +
                        "\"\",\"urls\":[],\"phones\":[{\"type\":\"work\",\"value\":\"0862524363\"}]," +
                        "\"emails\":[{\"type\":\"work\",\"value\":\"tigerwoods@pga.com\"}],\"status\":" +
                        "\"Lead\",\"status_id\":\"556cb8c91787fa0e24000011\",\"starred\":true," +
                        "\"lead_source_id\":\"advertisement\",\"type\":\"individual\",\"company_name\":" +
                        "\"Myles Inc.\",\"company_id\":\"55892ae31787fa5dbb001aa1\"," +
                        "\"sales_closed_for\":[],\"tags\":[\"subscriber\"],\"custom_fields\":[]," +
                        "\"created_at\":\"2015-06-16T16:31:40.075Z\",\"modified_at\":" +
                        "\"2015-06-30T16:16:20.985Z\",\"address_list\":[{\"address\":\"\",\"city\":\"\"," +
                        "\"state\":\"\",\"zip_code\":\"\",\"country_code\":\"\"}]},\"next_actions\":[{" +
                        "\"id\":\"55804f6c1787fa72b4000030\",\"assignee_id\":\"556cb8b61787fa02e000047e\"," +
                        "\"contact_id\":\"55804f6b1787fa72b400002e\",\"text\":" +
                        "\"Follow up with Tiger, who subscribed to your mailing list\"," +
                        "\"modified_at\":\"2015-06-16T16:31:40.088Z\",\"status\":\"asap\",\"date\":null}]," +
                        "\"next_action\":{\"id\":\"55804f6c1787fa72b4000030\",\"assignee_id\":" +
                        "\"556cb8b61787fa02e000047e\",\"contact_id\":\"55804f6b1787fa72b400002e\"," +
                        "\"text\":\"Follow up with Tiger, who subscribed to your mailing list\"," +
                        "\"modified_at\":\"2015-06-16T16:31:40.088Z\",\"status\":\"asap\"," +
                        "\"date\":null}},{\"contact\":{\"id\":\"558037511787fa21ba000148\",\"owner_id\":" +
                        "\"556cb8b61787fa02e000047e\",\"first_name\":\"Cillian\",\"last_name\":" +
                        "\"Myles\",\"photo_url\":\"\",\"job_title\":\"\",\"background\":\"\",\"urls\":[]," +
                        "\"phones\":[{\"type\":\"work\",\"value\":\"6178187423\"}],\"emails\":[{\"type\":" +
                        "\"work\",\"value\":\"cillian@xap.ie\"}],\"status\":\"Lead\",\"status_id\":" +
                        "\"556cb8c91787fa0e24000011\",\"starred\":true,\"lead_source_id\":" +
                        "\"advertisement\",\"type\":\"individual\",\"company_name\":\"Myles Inc.\"," +
                        "\"company_id\":\"55892ae31787fa5dbb001aa1\",\"sales_closed_for\":[],\"tags\":[]," +
                        "\"custom_fields\":[],\"created_at\":\"2015-06-16T14:48:49.719Z\",\"modified_at\":" +
                        "\"2015-06-30T16:16:21.520Z\",\"address_list\":[{\"address\":" +
                        "\"85 Tur Uisce\\r\\nDoughiska\",\"city\":\"Galway City\",\"state\":\"Galway\"," +
                        "\"zip_code\":\"0000\",\"country_code\":\"IE\"}]},\"next_actions\":[{\"id\":" +
                        "\"558037511787fa21ba00014a\",\"assignee_id\":\"556cb8b61787fa02e000047e\"," +
                        "\"contact_id\":\"558037511787fa21ba000148\",\"text\":" +
                        "\"Follow up with web form enquiry from Cillian\",\"modified_at\":" +
                        "\"2015-06-16T14:48:49.723Z\",\"status\":\"asap\",\"date\":null}],\"next_action" +
                        "\":{\"id\":\"558037511787fa21ba00014a\",\"assignee_id\":\"556cb8b61787fa02e000047e\"," +
                        "\"contact_id\":\"558037511787fa21ba000148\",\"text\":" +
                        "\"Follow up with web form enquiry from Cillian\",\"modified_at\":" +
                        "\"2015-06-16T14:48:49.723Z\",\"status\":\"asap\",\"date\":null}}]}}"
        );

        ContactList parsed = ContactListSerializer.fromResponse(response);

        assertNotNull("Contact array object mis-constructed / not constructed", parsed);
        assertEquals("Wrong number of Contacts constructed", 2, parsed.size());
    }

    /**
     * Ensure correct operation of Contact parsing method.
     * <p/>
     * Response containing details of no Contacts should not result in
     * the construction of any Contact objects.
     */
    public void testParseGetContactsResponse_NoContacts() throws Exception {
        Response response = Response.okay(
                "{\"status\":0,\"message\":\"OK\",\"timestamp\":1436172718," +
                        "\"data\":{\"contacts\":[]}}"
        );

        ContactList parsed = ContactListSerializer.fromResponse(response);

        assertNotNull("Contact array object mis-constructed / not constructed", parsed);
        assertEquals("Wrong number of Contacts constructed", 0, parsed.size());
    }

    /**
     * Ensure correct operation of Contact parsing method.
     * <p/>
     * Response containing details of Exception should result in Exception
     * being thrown.
     */
    public void testParseGetContactsResponse_Expired() {
        Response expiredResponse = new Response(
                400,
                "Error",
                "{\"error_name\":\"expired\",\"status\":400,\"message\":" +
                        "\"Expired\",\"error_message\":\"Could not find more helpful message, sorry.\"," +
                        "\"errors\":{}}"
        );

        try {
            ContactListSerializer.fromResponse(expiredResponse);

        } catch (APIException exception) {
            // We are expecting this Exception to be thrown.
            assertTrue("Expired token response misinterpreted",
                    exception instanceof BadRequestException);
        }
    }

    /**
     * Ensure correct operation of Contact parsing method.
     * <p/>
     * Response containing details of Exception should result in Exception
     * being thrown.
     */
    public void testParseGetContactsResponse_NoAuth() {
        Response noAuthResponse = new Response(
                401,
                "Error",
                "{\"error_name\":\"authorization_data_not_found\"," +
                        "\"status\":401,\"message\":\"Authorization data not found\"," +
                        "\"error_message\":\"Could not find more helpful message, sorry.\"," +
                        "\"errors\":{}}"
        );

        try {
            ContactListSerializer.fromResponse(noAuthResponse);

        } catch (APIException exception) {
            // We are expecting this Exception to be thrown.
            assertTrue("No auth token response misinterpreted",
                    exception instanceof AuthenticationException);
        }
    }

    /**
     * Normal operation of the resource creation response parsing method.
     * <p/>
     * Responses are boolean, successful = true.
     */
    public void testParseCreateResourceResponse_Successful() throws Exception {
        Response response = Response.okay(
                "{\"status\":0,\"message\":\"Created\"," +
                        "\"timestamp\":1435929035,\"data\":{\"call\":{\"id\":" +
                        "\"559689cb9b79b24686000025\",\"text\":\"\",\"call_time_int\":" +
                        "\"1435929035\",\"author\":\"\",\"phone_number\":\"\",\"via\":\"unknown\"," +
                        "\"call_result\":\"interested\",\"recording_link\":\"\",\"created_at\":" +
                        "\"2015-07-03 13:10:35 UTC\",\"modified_at\":\"2015-07-03 13:10:35 UTC\"," +
                        "\"contact_id\":\"55804f6b1787fa72b400002e\",\"attachments\":[]}}}"
        );

        Call call = CallSerializer.fromResponse(response);

        assertTrue("Success message misinterpreted", call.isValid());
        assertTrue(call.getId().equals("559689cb9b79b24686000025"));
        assertTrue(("" + DateSerializer.toTimestamp(call.getTime()) + "").equals("1435929035"));
        assertTrue(call.getVia().equals("unknown"));
        assertTrue(call.getContactId().equals("55804f6b1787fa72b400002e"));
    }

    /**
     * Normal operation of the resource creation response parsing method.
     * <p/>
     * Responses are boolean, expired message means unsuccessful.
     */
    public void testParseCreateResourceResponse_Expired() {
        Response expiredResponse = new Response(
                400,
                "Error",
                "{\"error_name\":\"expired\",\"status\":400,\"message\":" +
                        "\"Expired\",\"error_message\":\"Could not find more helpful message, sorry.\"," +
                        "\"errors\":{}}"
        );

        try {
            BaseSerializer.fromResponse(expiredResponse);

        } catch (APIException exception) {
            // We are expecting this Exception to be thrown.
            assertTrue("Expired token response misinterpreted",
                    exception instanceof BadRequestException);
        }
    }

    /**
     * Normal operation of the resource creation response parsing method.
     * <p/>
     * Responses are boolean, no auth message means unsuccessful.
     */
    public void testParseCreateResourceResponse_NoAuth() {
        Response noAuthResponse = new Response(
                401,
                "Error",
                "{\"error_name\":\"authorization_data_not_found\"," +
                        "\"status\":401,\"message\":\"Authorization data not found\"," +
                        "\"error_message\":\"Could not find more helpful message, sorry.\"," +
                        "\"errors\":{}}"
        );

        try {
            BaseSerializer.fromResponse(noAuthResponse);

        } catch (APIException exception) {
            // We are expecting this Exception to be thrown.
            assertTrue("No auth token response misinterpreted",
                    exception instanceof AuthenticationException);
        }
    }

    /**
     * Test makes sure that a JSON response containing 18 contacts is parsed correctly to a
     * ContactList with 18 Contact objects.
     */
    public void testParseActionStreamPerPage_Successful() throws Exception {
        Response response = Response.okay(FileUtilities.getResourceContents(
                "src/test/res/responses/handler_test/request_01_action_stream_response.json"
        ));

        ContactList actionStream = ContactListSerializer.fromResponse(response);

        assertTrue("Contacts not parsed correctly", !actionStream.isEmpty());
        assertTrue("Incorrect number of contacts parsed", actionStream.size() == 18);
    }

    /**
     * Test makes sure that a JSON response containing 25 contacts is parsed correctly to a
     * ContactList with 18 Contact objects.
     */
    public void testParseContactsPerPage_Successful() throws Exception {
        Response response = Response.okay(FileUtilities.getResourceContents(
                "src/test/res/responses/handler_test/request_02_contacts_response.json"
        ));

        ContactList contacts = ContactListSerializer.fromResponse(response);

        assertTrue("Contacts not parsed correctly", !contacts.isEmpty());
        assertTrue("Incorrect number of contacts parsed", contacts.size() == 25);
    }

    /**
     * Test makes sure that a CALL resource has been added correctly.
     */
    public void testParseAddCall_Successful() throws Exception {
        Response response = Response.okay(FileUtilities.getResourceContents(
                "src/test/res/responses/handler_test/request_03_add_call_response.json"
        ));

        Call call = CallSerializer.fromResponse(response);

        assertTrue("Add call response not parsed correctly", call.isValid());
    }

    /**
     * Test makes sure that a CONTACT resource has been added correctly.
     */
    public void testParseAddContact_Successful() throws Exception {
        Response response = Response.okay(FileUtilities.getResourceContents(
                "src/test/res/responses/handler_test/request_04_add_contact_response.json"
        ));

        Contact contact = ContactSerializer.fromResponse(response);

        assertTrue("Add contact response not parsed correctly", contact.isValid());
    }
}
