//
// Copyright 2016 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.13
//
package com.amazonaws.mobile;

import com.amazonaws.regions.Regions;

/**
 * This class defines constants for the developer's resource
 * identifiers and API keys. This configuration should not
 * be shared or posted to any public source code repository.
 */
public class AWSConfiguration {
    // AWS MobileHub user agent string
    public static final String AWS_MOBILEHUB_USER_AGENT =
        "MobileHub 6472c4f8-d60c-4c70-b914-ca0337774ec1 aws-my-sample-app-android-v0.13";
    // AMAZON COGNITO
    public static final Regions AMAZON_COGNITO_REGION =
      Regions.fromName("us-east-1");
    public static final String  AMAZON_COGNITO_IDENTITY_POOL_ID =
        "us-east-1:39d635f8-4662-4314-bd15-5393ebcbe393";
    // AMAZON MOBILE ANALYTICS
    public static final String  AMAZON_MOBILE_ANALYTICS_APP_ID =
        "e84799b2ad1a438aa41f12217b57a722";
    // Amazon Mobile Analytics region
    public static final Regions AMAZON_MOBILE_ANALYTICS_REGION = Regions.US_EAST_1;
    public static final String AMAZON_CONTENT_DELIVERY_S3_BUCKET =
        "apr-contentdelivery-mobilehub-394112265";
    public static final Regions AMAZON_CONTENT_DELIVERY_S3_REGION =
       Regions.fromName("us-east-1");
    public static final Regions AMAZON_DYNAMODB_REGION =
       Regions.fromName("us-east-1");
    public static final String AMAZON_COGNITO_USER_POOL_ID =
        "us-east-1_CqD6wsl8S";
    public static final String AMAZON_COGNITO_USER_POOL_CLIENT_ID =
        "4ggrnkmfj1ft407ri87k30i0dt";
    public static final String AMAZON_COGNITO_USER_POOL_CLIENT_SECRET =
        "1e1p08kra30imal7va49uc4ih8anf0040qqkhcl8ukugp0ki3ef";
}
