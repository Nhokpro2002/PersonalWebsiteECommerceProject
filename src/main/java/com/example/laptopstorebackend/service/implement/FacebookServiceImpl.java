package com.example.laptopstorebackend.service.implement;

//import com.example.laptopstorebackend.dto.FacebookUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class FacebookServiceImpl {

    /**
     *
     * @return path to authenticate facebook by account and password
     */
    public String loginByFacebook() {
        return """
                https://www.facebook.com/v19.0/dialog/oauth?client_id=3986236111593502&redirect_uri=http://localhost:8081/home-page&state=state&scope=public_profile&response_type=code&auth_type=reauthenticate""";
    }

    /**
     *
     * @param code
     * @return
     */
    public String getFacebookAccessToken(String code) {
        String url = UriComponentsBuilder.fromHttpUrl("https://graph.facebook.com/v19.0/oauth/access_token")
                .queryParam("client_id", "3986236111593502")
                .queryParam("redirect_uri", "http://localhost:8081/home-page")
                .queryParam("client_secret", "951b91b331b973551a68c7a86ce28863")
                .queryParam("code", code)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        return response.get("access_token").toString();
    }

    /**
     *
     * @param accessToken
     * @return
     */
//    public FacebookUserInfo getFacebookUser(String accessToken) {
//        String url = UriComponentsBuilder
//                .fromHttpUrl("https://graph.facebook.com/me")
//                .queryParam("fields", "id,name,email")
//                .queryParam("access_token", accessToken)
//                .toUriString();
//
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            return restTemplate.getForObject(url, FacebookUserInfo.class);
//        } catch (RestClientException e) {
//            throw new RuntimeException("Failed to fetch user info from Facebook", e);
//        }
//    }


}
