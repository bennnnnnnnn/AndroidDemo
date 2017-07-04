package com.ben.utiltest_app.utils;

import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.security.auth.x500.X500Principal;

/**
 * Created on 17/7/4.
 *
 * @author Ben
 *
 * refrence from Drakeet
 */


public class DataStore {

    static final String TAG = DataStore.class.getSimpleName();

    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private static final String TYPE_RSA = "RSA";

    private final String alias;
    private final Context context;


    public DataStore(Context context, String alias) {
        this.context = context;
        this.alias = alias;
    }


    /**
     * Creates a public and private key and stores it using the AndroidKeyStore,
     * so that only this application will be able to access the keys.
     */
    @SuppressWarnings("deprecation")
    public void createKeys() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        keyStore.load(null);
        if (keyStore.containsAlias(alias)) {
            Log.d(TAG, "[containsAlias]");
            return;
        }

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.YEAR, 30);
        KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(context)
                .setAlias(alias)
                .setSubject(new X500Principal("CN=" + alias))
                .setSerialNumber(BigInteger.TEN)
                .setStartDate(start.getTime())
                .setEndDate(end.getTime())
                .build();
        KeyPairGenerator generator = KeyPairGenerator.getInstance(TYPE_RSA, ANDROID_KEY_STORE);
        generator.initialize(spec);
        KeyPair keyPair = generator.generateKeyPair();
        Log.d(TAG, "Public Key is: " + keyPair.getPublic().toString());
    }


    /**
     * Encrypt the secret with RSA.
     *
     * @param secret the secret.
     * @return the encrypted secret.
     * @throws Exception
     */
    public String encrypt(String secret) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        keyStore.load(null);
        KeyStore.PrivateKeyEntry privateKeyEntry =
                (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, null);

        Cipher inputCipher = Cipher.getInstance(RSA_ALGORITHM);
        inputCipher.init(Cipher.ENCRYPT_MODE, privateKeyEntry.getCertificate().getPublicKey());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, inputCipher);
        cipherOutputStream.write(secret.getBytes());
        cipherOutputStream.close();

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }


    /**
     * Decrypt the encrypted secret.
     *
     * @param encrypted the encrypted secret.
     * @return the decrypted secret.
     * @throws Exception
     */
    public String decrypt(String encrypted) throws Exception {
        byte[] encryptedBytes = Base64.decode(encrypted, Base64.DEFAULT);
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        keyStore.load(null);
        KeyStore.PrivateKeyEntry privateKeyEntry =
                (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, null);
        Cipher output = Cipher.getInstance(RSA_ALGORITHM);
        output.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());
        CipherInputStream cipherInputStream = new CipherInputStream(
                new ByteArrayInputStream(encryptedBytes), output);
        ArrayList<Byte> values = new ArrayList<>();
        int nextByte;
        while ((nextByte = cipherInputStream.read()) != -1) {
            values.add((byte) nextByte);
        }

        byte[] bytes = new byte[values.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = values.get(i);
        }
        return new String(bytes);
    }
}
