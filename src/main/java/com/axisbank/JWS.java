
package com.axisbank;

import java.security.SecureRandom;
import java.text.ParseException;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;

public class JWS {

    public static void main(String[] args){
        SecureRandom secureRandom = new SecureRandom();
        byte [] sharedSecret = new byte[64];
        secureRandom.nextBytes(sharedSecret);

        System.out.println("Key size is : " + sharedSecret.length);

        JWSSigner jwsSigner;

        try{
            jwsSigner = new MACSigner(sharedSecret);
        }catch(KeyLengthException e){
            System.out.println("key length issue");
            throw new RuntimeException(e);
        }

        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS512) , new Payload("Welcome to HeliosTech"));


        try {
            jwsObject.sign(jwsSigner);
        } catch (JOSEException e) {
            System.out.println("Exception occurred during signing");
            throw new RuntimeException(e);
        }

        String signedObject = jwsObject.serialize();

        System.out.println("Here is your JWS !!");
        System.out.println(signedObject);


        JWSObject parsedObject;

        try {
            parsedObject = JWSObject.parse(signedObject);
        } catch (ParseException e) {
            System.out.println("Exception occurred while parsing the signed jws string");
            throw new RuntimeException(e);
        }


        JWSVerifier jwsVerifier;

        try {
            jwsVerifier = new MACVerifier(sharedSecret);
        } catch (JOSEException e) {
            System.out.println("Exception while creating JWS verifier");
            throw new RuntimeException(e);
        }


        try {
            jwsObject.verify(jwsVerifier);
        } catch (JOSEException e) {
            System.out.println("Exception while verifying the parsed JWS");
            throw new RuntimeException(e);
        }

        System.out.println(parsedObject.getPayload().toString());

    }
}
