package com.axisbank;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jose.jwk.RSAKey;

import java.util.Base64;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
public class App {




    private static PrivateKey stringToRSAPrivateKey(String keyStr) throws Exception {
        byte[]              buffer1       = Base64.getDecoder().decode(keyStr);
        PKCS8EncodedKeySpec keySpec1      = new PKCS8EncodedKeySpec(buffer1);
        KeyFactory          keyFactory1   = KeyFactory.getInstance("RSA");
        PrivateKey          privateKey    = (RSAPrivateKey) keyFactory1.generatePrivate(keySpec1);
        return privateKey;
    }

    private static PublicKey stringToRSAPublicKey(String keyStr) throws Exception {
        byte[]             buffer2     = Base64.getDecoder().decode(keyStr);
        KeyFactory         keyFactory2 = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec2      = new X509EncodedKeySpec(buffer2); // Fix 2: Apply X509EncodedKeySpec for the import of the public key
        PublicKey          publicKey   = (RSAPublicKey) keyFactory2.generatePublic(keySpec2);
        return publicKey;
    }

   
    
    
    public static void main(String[] args) throws Exception {
        try {
            // Generate RSA key pair
            // KeyPair keyPair = generateRSAKeyPair();
            
            // // Get private and public keys
            // PrivateKey privateKey = keyPair.getPrivate();
            // PublicKey publicKey = keyPair.getPublic();



            // PrivateKey privateKey = convertStringToRSAPrivateKey();
            // PublicKey publicKey = convertStringToRSAPublicKey();

            String privateKeyString = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC6cXloNrocJ8swwj8xktPmEOTfTgJT7KkUwWIGOjBB1QxApgdn5+SUHsakvEJq3Fgn+FnuuN8cfcqWrbT9jURtgNGinJNJ+StPM/PCxfhSv+XbkK11CV2EcJMyDB/8S/9u4E2ht/N1kT4OF2/mVDAq2MjjeUMq8CLmQR63ZMXB8lwmsGJEl4Rwt9WBZNcZFCfuCeBYrKRS7mtLzd4BTXEf0UuiNB/KJrz38TKSI47v/dRbB34wBNn0cuNLHb8t/eDaOvzV6J8SZgOWuL85mng6Fm77QGjUteWgJN76+YhDZgJfsRq1Q67JAy3ZXDHi5X538DcM/o+0wYEqkXxK3iIbAgMBAAECggEASlJj0ExIomKmmBhG8q8SM1s2sWG6gdQMjs6MEeluRT/1c2v79cq2Dum5y/+UBl8x8TUKPKSLpCLs+GXkiVKgHXrFlqoN+OYQArG2EUWzuODwczdYPhhupBXwR3oX4g41k/BsYfQfZBVzBFEJdWrIDLyAUFWNlfdGIj2BTiAoySfyqmamvmW8bsvc8coiGlZ28UC85/Xqx9wOzjeGoRkCH7PcTMlc9F7SxSthwX/k1VBXmNOHa+HzGOgO/W3k1LDqJbq2wKjZTW3iVEg2VodjxgBLMm0MueSGoI6IuaZSPMyFEM3gGvC2+cDBI2SL/amhiTUa/VDlTVw/IKbSuar9uQKBgQDd76M0Po5Lqh8ZhQ3obhFqkfO5EBXy7HUL15cw51kVtwF6Gf/J2HNHjwsg9Nb0eJETTS6bbuVd9bn884JoRS986nVTFNZ4dnjEgKjjQ8GjfzdkpbUxsRLWiIxuOQSpIUZGdMi2ctTTtspvMsDsjRRYdYIQCe/SDsdHGT3vcUCybwKBgQDXDz6iVnY84Fh5iDDVrQOR4lYoxCL/ikCDJjC6y1mjR0eVFdBPQ4j1dDSPU9lahBLby0VyagQCDp/kxQOl0z2zBLRI4I8jUtz9/9KW6ze7U7dQJ7OTfumd5I97OyQOG9XZwKUkRgfyb/PAMBSUSLgosi38f+OC3IN3qlvHFzvxFQKBgQCITpUDEmSczih5qQGIvolN1cRF5j5Ey7t7gXbnXz+Umah7kJpMIvdyfMVOAXJABgi8PQwiBLM0ySXo2LpARjXLV8ilNUggBktYDNktc8DrJMgltayaj3HNd2IglD5rjfc2cKWRgOd7/GlKcHaTEnbreYhfR2sWrWLxJOyoMfuVWwKBgFalCbMV6qU0LfEo8aPlBN8ttVDPVNpntP4h0NgxPXgPK8Pg+gA1UWSy4MouGg/hzkdHaj9ifyLlCX598a5JoT4S0x/ZeVHd/LNI8mtjcRzD6cMde7gdFbpLb5NSjIAyrsIAX4hxvpnqiOYRePkVIz0iLGziiaMbfMwlkrxvm/LRAoGBALPRbtSbE2pPgvOHKHTGPr7gKbmsWVbOcQA8rG801T38W/UPe1XtynMEjzzQ29OaVeQwvUN9+DxFXJ6Yvwj6ih4Wdq109i7Oo1fDnMczOQN9DKch2eNAHrNSOMyLDCBm++wbyHAsS2T0VO8+gzLABviZm5AFCQWfke4LZo5mOS10";


            
            String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAunF5aDa6HCfLMMI/MZLT5hDk304CU+ypFMFiBjowQdUMQKYHZ+fklB7GpLxCatxYJ/hZ7rjfHH3Klq20/Y1EbYDRopyTSfkrTzPzwsX4Ur/l25CtdQldhHCTMgwf/Ev/buBNobfzdZE+Dhdv5lQwKtjI43lDKvAi5kEet2TFwfJcJrBiRJeEcLfVgWTXGRQn7gngWKykUu5rS83eAU1xH9FLojQfyia89/EykiOO7/3UWwd+MATZ9HLjSx2/Lf3g2jr81eifEmYDlri/OZp4OhZu+0Bo1LXloCTe+vmIQ2YCX7EatUOuyQMt2Vwx4uV+d/A3DP6PtMGBKpF8St4iGwIDAQAB";

            // Remove spaces and line breaks
            privateKeyString = privateKeyString.replaceAll("\\s", "");
            publicKeyString = publicKeyString.replaceAll("\\s", "");


            

    

            PrivateKey privateKey = stringToRSAPrivateKey(privateKeyString);
            PublicKey publicKey = stringToRSAPublicKey(publicKeyString);

            // PrivateKey privateKey = stringToRSAPrivateKey(strPrivateKey);
            // PublicKey publicKey = stringToRSAPublicKey(strPublicKey);

            System.out.println("Private key is : " + privateKey);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("Public key is : " + publicKey);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();


            // Sign the JWS using the private key
            JWSSigner jwsSigner = new RSASSASigner(privateKey);

            // Define your payload
            String payloadJSON = "{\"Data\":{\"userName\":\"admin\",\"password\":\"Axis@1234\"},\"Risk\":{}}";

            // Create JWS object with the specified algorithm and payload
            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.RS256), new Payload(payloadJSON));

            jwsObject.sign(jwsSigner);

            String signedObject = jwsObject.serialize();

            System.out.println("Here is your JWS!!");
            System.out.println(signedObject);
            
            System.out.println();
            System.out.println();
            System.out.println();


            // Parse the JWS
            JWSObject parsedObject = JWSObject.parse(signedObject);

            // Verify the JWS using the public key
            JWSVerifier jwsVerifier = new RSASSAVerifier((RSAPublicKey) publicKey);

            if (parsedObject.verify(jwsVerifier)) {
                System.out.println("JWS Signature Verified Successfully!");
                System.out.println(parsedObject.getPayload().toString());
            } else {
                System.out.println("JWS Signature Verification Failed!");
            }
        } catch (ParseException | JOSEException e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // this is used to generate RSA key..............

    private static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom();
        keyPairGenerator.initialize(2048, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

}











