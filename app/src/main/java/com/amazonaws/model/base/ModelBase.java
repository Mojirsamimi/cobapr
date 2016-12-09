package com.amazonaws.model.base;

import android.util.Log;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;

import java.util.UUID;

import edu.brandeis.bostonaccessibleroutes.SignInActivity;


/**
 * Created by Mojir on 12/3/2016.
 */

public class ModelBase {
    private final DynamoDBMapper mapper;
    private static final String LOG_TAG = SignInActivity.class.getSimpleName();
    public ModelBase()
    {
        mapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();

    }
    public void save(final Object o) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    mapper.save(o);
                } catch (final AmazonClientException ex) {
                    Log.e(LOG_TAG, "Failed saving item : " + ex.getMessage(), ex);
                }


            }
        }).start();
    }

    public String generateUUID()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
