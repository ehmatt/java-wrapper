package com.onepagecrm.net;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.User;

public class AuthenticationTest extends BaseTest {

    private User loggedInUser;

    /**
     * Method is used to set up a fabricated User object.
     * <p/>
     * The fabricated User's details are my real details. These exact details
     * (x-uid, x-ts, x-auth) and particular requests have been used before and
     * worked. This is how the method Authentication.calculateSignature() is
     * verified, against previously correct data.
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Set up fabricated User.
        loggedInUser = new User()
                .setId("556cb8b61787fa02e000047e")
                .setAuthKey("WqLLs1n/Y3SvOpGg5CNOpdKy74GkGI6lnhwSfYmgNl4=")
                .setFirstName("Cillian")
                .setLastName("Myles");
    }

    /**
     * Should be no Authentication Signature generated for login request.
     */
    public void testCalculateSignature_Login() {
        String loginUrl = "https://app.onepagecrm.com/api/v3/login.json";
        Authentication loginAuthSig = new Authentication("POST", loginUrl, "");
        assertNull("Login signature incorrectly constructed", loginAuthSig.getSignature());
    }

    /**
     * Verify Authentication Signature generated matches correct answer for
     * Action Stream.
     */
    public void testCalculateSignature_ActionStream() {
        String actionStreamUrl = "https://app.onepagecrm.com/api/v3/action_stream.json";

        Authentication actionStreamAuthSig = new Authentication(loggedInUser, 1435850641, "GET",
                actionStreamUrl, "");

        assertEquals("d3aefe439817aa7be31d8aecf5368b0bf23e7b1b0004ea6bc3692ac8fc4d0ab4",
                actionStreamAuthSig.getSignature());
    }

    /**
     * Verify Authentication Signature generated matches correct answer for Calls.
     */
    public void testCalculateSignature_AddCall() {
        String postCallUrl = "https://app.onepagecrm.com/api/v3/calls.json?contact_id=55804f6b1787fa72b400002e";
        String postCallBody = "text=&call_result=interested&contact_id=55804f6b1787fa72b400002e";

        Authentication callsAuthSig = new Authentication(loggedInUser, 1435851404, "POST",
                postCallUrl, postCallBody);

        assertEquals("56292bace8643cd06d30d44264670acf543610fb75626449091980606163a977",
                callsAuthSig.getSignature());
    }

    /**
     * Verify Authentication Signature generated matches correct answer for Calls.
     */
    public void testCalculateSignature_AddCall_JsonBody() {
        String postCallUrl = "https://app.onepagecrm.com/api/v3/calls.json?contact_id=55d5d973e57c393e8a00009c";
        String postCallBody = "{\"text\":\"JAVA\",\"call_result\":\"interested\"}";

        Authentication callsAuthSig = new Authentication(loggedInUser, 1441288055, "POST",
                postCallUrl, postCallBody);

        assertEquals("cbb42048c9617dd4a883f17579cbf698a4222c3626dd302d372375f031b5a28b",
                callsAuthSig.getSignature());
    }

    /**
     * Verify Authentication Signature generated matches correct answer for Calls.
     */
    public void testCalculateSignature_AddContact() {
        String postCallUrl = "https://app.onepagecrm.com/api/v3/contacts.json";
        String postCallBody = "{\"first_name\":\"Cillian\",\"company_name\":\"Myles Inc.\",\"last_name\":\"Myles\"}";

        Authentication callsAuthSig = new Authentication(loggedInUser, 1441288055, "POST",
                postCallUrl, postCallBody);

        assertEquals("639cab894e5ffafeaa50d9b2b5fab903eb23215c34b8b4ba518ba8381e0419f7",
                callsAuthSig.getSignature());
    }

    /**
     * Verify Authentication Signature generated matches correct answer for deleting FCM devices.
     */
    public void testCalculateSignature_DeleteFcmDevice() {
        final User user = new User().setId("57a099629007ba29115829b2")
                .setAuthKey("NVMsF8uNkFihbJRaklVSTr8RZoXbt8/s2biQssgwBoI=");

        String endpoint = "https://app.onepagecrm.com/api/v3/firebase.json";
        String body = "{\"id\":\"584a795f9007ba7c3f6bf25f\"}";

        Authentication authData = new Authentication(user, 1481280581, "DELETE",
                endpoint, body);

        assertEquals("8e2366a8410fcb74dd4ae06dd3ae7ce05498d66eb5c326e02510f0dc8bab1b32",
                authData.getSignature());
    }
}
