package io.github.akoskm;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Follow https://dev.twitter.com/web/sign-in/implementing
 */
@Path("twitter")
public class SignUpResource {

    private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";
    private final AppConfig config;

    public SignUpResource(AppConfig config) {
        this.config = config;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response redirectToAuthorization() {
        OAuthService service = createService()
                .callback("https://localhost:9090/twitter/continue")
                .build();

        // Step 1: Obtaining a request token
        Token requestToken = service.getRequestToken();

        String authURL = service.getAuthorizationUrl(requestToken);

        // Step 2: Redirecting the user
        return Response.seeOther(URI.create(authURL)).build();
    }

    @GET
    @Path("continue")
    @Produces(MediaType.TEXT_PLAIN)
    public Response redirectToApp(@QueryParam("oauth_token") String oauthToken,
                                  @QueryParam("oauth_verifier") String oauthVerifier) {
        OAuthService service = createService().build();

        // Step 3: Converting the request token to an access token

        /**
         * To render the request token into a usable access token,
         * your application must make a request to the POST oauth / access_token endpoint,
         * containing the oauth_verifier value obtained in step 2.
         * The request token is also passed in the oauth_token portion of the header,
         * but this will have been added by the signing process.
         *
         * Source https://dev.twitter.com/web/sign-in/implementing
         */
        Token requestToken = new Token(oauthToken, oauthVerifier);
        Verifier verifier = new Verifier(oauthVerifier);

        // POST oauth/access_token
        Token accessToken = service.getAccessToken(requestToken, verifier);

        OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        service.signRequest(accessToken, request);

        org.scribe.model.Response response = request.send();

        return Response.ok(response.getBody()).build();
    }

    private ServiceBuilder createService() {
        return new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey(config.getConsumerKey())
                .apiSecret(config.getConsumerSecret());
    }
}
