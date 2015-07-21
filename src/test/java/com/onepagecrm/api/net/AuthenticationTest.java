package com.onepagecrm.api.net;

import junit.framework.TestCase;

import com.onepagecrm.api.models.User;


public class AuthenticationTest extends TestCase {

    private User loggedInUser;

    /**
     * Method is used to set up a fabricated User object.
     *
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
        loggedInUser = new User("556cb8b61787fa02e000047e",
                "WqLLs1n/Y3SvOpGg5CNOpdKy74GkGI6lnhwSfYmgNl4=", "trial",
                "Cillian", "Myles", "cillian.college@gmail.com", "Myles Inc.",
                "", "556cb8b61787fa02e000047d@users.onepagecrm.com", null);
    }

    /**
     * Should be no Authentication Signature generated for login request.
     */
//    @SmallTest
    public void testCalculateSignature_Login() {
        Authentication loginAuthSig = new Authentication("POST", ApiClient.POST_LOGIN_URL, "");
        assertNull("Login signature incorrectly constructed", loginAuthSig.getSignature());
    }

    /**
     * Verify Authentication Signature generated matches correct answer for Action Stream.
     */
//    @SmallTest
    public void testCalculateSignature_ActionStream() {
        Authentication actionStreamAuthSig = new Authentication(loggedInUser, 1435850641,
                "GET", ApiClient.GET_ACTION_STREAM_URL, "");

        assertEquals("d3aefe439817aa7be31d8aecf5368b0bf23e7b1b0004ea6bc3692ac8fc4d0ab4",
                actionStreamAuthSig.getSignature());
    }

    /**
     * Verify Authentication Signature generated matches correct answer for Calls.
     */
//    @SmallTest
    public void testCalculateSignature_Calls() {
        String postCallUrl =
                "https://app.onepagecrm.com/api/v3/calls.json?contact_id=55804f6b1787fa72b400002e";
        String postCallBody =
                "text=&call_result=interested&contact_id=55804f6b1787fa72b400002e";

        Authentication callsAuthSig = new Authentication(loggedInUser, 1435851404,
                "POST", postCallUrl, postCallBody);

        assertEquals("56292bace8643cd06d30d44264670acf543610fb75626449091980606163a977",
                callsAuthSig.getSignature());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
