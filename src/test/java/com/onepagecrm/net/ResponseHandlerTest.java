package com.onepagecrm.net;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.User;
import com.onepagecrm.models.serializer.BaseSerializer;
import com.onepagecrm.models.serializer.ContactListSerializer;
import com.onepagecrm.models.serializer.LoginSerializer;

import java.util.logging.Logger;

public class ResponseHandlerTest extends BaseTest {

    private static Logger LOG = Logger.getLogger(ResponseHandlerTest.class.getName());

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Normal operation of parseLoginResponse() method.
     * <p/>
     * Will normally receive a success message (user enters details correctly).
     * Method will construct User object from JSON.
     * <p/>
     * successResponse and loggedInUser directly map to each other.
     */
    public void testParseLoginResponse_Successful() {

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
        String successResponse = "{\"status\":0,\"message\":\"OK\",\"timestamp\":1435940522," +
                "\"data\":{\"user_id\":\"556cb8b61787fa02e000047e\",\"auth_key\":" +
                "\"WqLLs1n/Y3SvOpGg5CNOpdKy74GkGI6lnhwSfYmgNl4=\",\"account_type\":" +
                "\"trial\",\"custom_fields\":[{\"custom_field\":{\"id\":" +
                "\"556cb8c91787fa0e24000043\",\"name\":\"Birthday\",\"type\":\"anniversary\"," +
                "\"choices\":[],\"position\":0,\"reminder_days\":0}},{\"custom_field\":{" +
                "\"id\":\"558be3a21787fa26d8000001\",\"name\":\"Spouse_Name\",\"type\":" +
                "\"single_line_text\",\"choices\":[],\"position\":1,\"reminder_days\":null}}]," +
                "\"team\":[],\"settings\":{\"reminder\":{\"type\":\"new_task\",\"hour\":6}," +
                "\"time_zone\":\"london\",\"date_format\":\"%d/%m/%Y\",\"listing_size\":25," +
                "\"currency\":\"EUR\",\"currency_symbol\":\"€\",\"popular_countries\":[" +
                "\"US\",\"IE\",\"GB\"],\"deal_stages\":[{\"stage\":10,\"label\":" +
                "\"Qualification\"},{\"stage\":25,\"label\":null},{\"stage\":50,\"label\":" +
                "\"Decision\"},{\"stage\":75,\"label\":null},{\"stage\":90,\"label\":" +
                "\"Negotiation\"}],\"default_contact_type\":\"company\"," +
                "\"show_tidy_stream\":false},\"user\":{\"user\":{\"id\":" +
                "\"556cb8b61787fa02e000047e\",\"first_name\":\"Cillian\",\"last_name\":" +
                "\"Myles\",\"email\":\"cillian.college@gmail.com\",\"company_name\":" +
                "\"Myles Inc.\",\"photo_url\":\"\",\"bcc_email\":" +
                "\"556cb8b61787fa02e000047d@users.onepagecrm.com\",\"account_rights\":[" +
                "\"account_owner\",\"admin\"]}}}}";

        User parsedUser = LoginSerializer.fromString(successResponse);
        assertNotNull("User object being set is null", parsedUser);

        assertEquals("User ID not set correctly", loggedInUser.getId(),
                parsedUser.getId());
        assertEquals("AuthKey not set correctly", loggedInUser.getAuthKey(),
                parsedUser.getAuthKey());
        assertEquals("AccountType not set correctly", loggedInUser.getAccountType(),
                parsedUser.getAccountType());
        assertEquals("FirstName not set correctly", loggedInUser.getFirstName(),
                parsedUser.getFirstName());
        assertEquals("LastName not set correctly", loggedInUser.getLastName(),
                parsedUser.getLastName());
        assertEquals("Email not set correctly", loggedInUser.getEmail(),
                parsedUser.getEmail());
        assertEquals("Company not set correctly", loggedInUser.getCompanyName(),
                parsedUser.getCompanyName());
        assertEquals("BCC Email not set correctly", loggedInUser.getBccEmail(),
                parsedUser.getBccEmail());
    }

    /**
     * When expired token message received, normal operation is to return a
     * new (empty) Contact object.
     * <p/>
     * This means no fields will be set for this object, check for ID.
     */
    public void testParseLoginResponse_Expired() {
        String expiredResponse = "{\"error_name\":\"expired\",\"status\":400,\"message\":" +
                "\"Expired\",\"error_message\":\"Could not find more helpful message, sorry.\"," +
                "\"errors\":{}}";

        assertFalse("Expired token response misinterpreted",
                LoginSerializer.fromString(expiredResponse).isValid());
    }

    /**
     * When no authorization data message received, normal operation is to return a
     * new (empty) Contact object.
     * <p/>
     * This means no fields will be set for this object, check for ID.
     */
    public void testParseLoginResponse_NoAuth() {
        String noAuthResponse = "{\"error_name\":\"authorization_data_not_found\"," +
                "\"status\":401,\"message\":\"Authorization data not found\"," +
                "\"error_message\":\"Could not find more helpful message, sorry.\"," +
                "\"errors\":{}}";

        assertFalse("No auth token response misinterpreted",
                LoginSerializer.fromString(noAuthResponse).isValid());
    }

    /**
     * Ensure correct operation of Contact parsing method.
     * <p/>
     * Response containing details of only one Contact should only result in
     * the construction of one Contact object.
     */
    public void testParseGetContactsResponse_CreatesContact() {
        String oneContactResponse = "{\"status\":0,\"message\":\"OK\",\"timestamp\":1436172718," +
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
                "\"modified_at\":\"2015-06-16T16:31:40.088Z\",\"status\":\"asap\",\"date\":null}}]}}";

        assertNotNull("Contact array object mis-constructed / not constructed",
                ContactListSerializer.fromString(oneContactResponse));

        assertEquals("Wrong number of Contacts constructed",
                1, ContactListSerializer.fromString(oneContactResponse).size());
    }

    /**
     * Ensure correct operation of Contact parsing method.
     * <p/>
     * Response containing details of two Contacts should only result in
     * the construction two Contact objects.
     */
    public void testParseGetContactsResponse_CreatesContacts() {
        String twoContactsResponse = "{\"status\":0,\"message\":\"OK\",\"timestamp\":1436172718," +
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
                "\"2015-06-16T14:48:49.723Z\",\"status\":\"asap\",\"date\":null}}]}}";

        assertNotNull("Contact array object mis-constructed / not constructed",
                ContactListSerializer.fromString(twoContactsResponse));

        assertEquals("Wrong number of Contacts constructed",
                2, ContactListSerializer.fromString(twoContactsResponse).size());
    }

    /**
     * Ensure correct operation of Contact parsing method.
     * <p/>
     * Response containing details of no Contacts should not result in
     * the construction of any Contact objects.
     */
    public void testParseGetContactsResponse_NoContacts() {
        String noContactResponse = "{\"status\":0,\"message\":\"OK\",\"timestamp\":1436172718," +
                "\"data\":{\"contacts\":[]}}";

        assertNotNull("Contact array object mis-constructed / not constructed",
                ContactListSerializer.fromString(noContactResponse));

        assertEquals("Wrong number of Contacts constructed",
                0, ContactListSerializer.fromString(noContactResponse).size());
    }

    /**
     * Normal operation of the resource creation response parsing method.
     * <p/>
     * Responses are boolean, successful = true.
     */
    public void testParseCreateResourceResponse_Successful() {
        String createdResourceResponse = "{\"status\":0,\"message\":\"Created\"," +
                "\"timestamp\":1435929035,\"data\":{\"call\":{\"id\":" +
                "\"559689cb9b79b24686000025\",\"text\":\"\",\"call_time_int\":" +
                "\"1435929035\",\"author\":\"\",\"phone_number\":\"\",\"via\":\"unknown\"," +
                "\"call_result\":\"interested\",\"recording_link\":\"\",\"created_at\":" +
                "\"2015-07-03 13:10:35 UTC\",\"modified_at\":\"2015-07-03 13:10:35 UTC\"," +
                "\"contact_id\":\"55804f6b1787fa72b400002e\",\"attachments\":[]}}}";

        assertEquals("Success message misinterpreted",
                true, BaseSerializer.createResourceFromString(createdResourceResponse));
    }

    /**
     * Normal operation of the resource creation response parsing method.
     * <p/>
     * Responses are boolean, expired message means unsuccessful.
     */
    public void testParseCreateResourceResponse_Expired() {
        String expiredResponse = "{\"error_name\":\"expired\",\"status\":400,\"message\":" +
                "\"Expired\",\"error_message\":\"Could not find more helpful message, sorry.\"," +
                "\"errors\":{}}";

        assertEquals("Expired token response misinterpreted",
                false, BaseSerializer.createResourceFromString(expiredResponse));
    }

    /**
     * Normal operation of the resource creation response parsing method.
     * <p/>
     * Responses are boolean, no auth message means unsuccessful.
     */
    public void testParseCreateResourceResponse_NoAuth() {
        String noAuthResponse = "{\"error_name\":\"authorization_data_not_found\"," +
                "\"status\":401,\"message\":\"Authorization data not found\"," +
                "\"error_message\":\"Could not find more helpful message, sorry.\"," +
                "\"errors\":{}}";

        assertEquals("No auth token response misinterpreted",
                false, BaseSerializer.createResourceFromString(noAuthResponse));
    }

    /**
     * Test makes sure that a JSON response containing 18 contacts is parsed correctly to a
     * ContactList with 18 Contact objects.
     */
    public void testParseActionStreamPerPage_Successful() {
        String response = resource("src/test/res/request_01_action_stream_response.json");
        ContactList actionStream = ContactListSerializer.fromString(response);
        assertTrue("Contacts not parsed correctly", !actionStream.isEmpty());
        assertTrue("Incorrect number of contacts parsed", actionStream.size() == 18);
    }

    /**
     * Test makes sure that a JSON response containing 25 contacts is parsed correctly to a
     * ContactList with 18 Contact objects.
     */
    public void testParseContactsPerPage_Successful() {
        String response = resource("src/test/res/request_02_contacts_response.json");
        ContactList contacts = ContactListSerializer.fromString(response);
        assertTrue("Contacts not parsed correctly", !contacts.isEmpty());
        assertTrue("Incorrect number of contacts parsed", contacts.size() == 25);
    }

    /**
     * Test makes sure that a CALL resource has been added correctly.
     */
    public void testParseAddCall_Successful() {
        String response = resource("src/test/res/request_03_add_call_response.json");
        assertTrue("Add call response not parsed correctly", BaseSerializer.createResourceFromString(response));
    }

    /**
     * Test makes sure that a CONTACT resource has been added correctly.
     */
    public void testParseAddContact_Successful() {
        String response = resource("src/test/res/request_04_add_contact_response.json");
        assertTrue("Add contact response not parsed correctly", BaseSerializer.createResourceFromString(response));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
