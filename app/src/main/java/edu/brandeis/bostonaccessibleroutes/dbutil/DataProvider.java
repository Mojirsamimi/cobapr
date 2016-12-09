package edu.brandeis.bostonaccessibleroutes.dbutil;

import android.util.Log;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import edu.brandeis.bostonaccessibleroutes.SignInActivity;

/**
 * Created by Mojir on 12/3/2016.
 */

public class DataProvider {
    private final DynamoDBMapper mapper;
    private static final String LOG_TAG = SignInActivity.class.getSimpleName();
    private static DataProvider dp;

    public DataProvider()
    {
        mapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();

    }
    public static DataProvider getInstance()
    {
        if(dp==null)
            dp=new DataProvider();
        return dp;
    }
    public void save(final Object o)
    {
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



public <T> ArrayList<T> loadItemsForUser(Class<T> cls)
{
    Map<String, AttributeValue> filterExpressionAttributeValues = new HashMap<>();
    filterExpressionAttributeValues.put(":userId",
            new AttributeValue(AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID()));
    String filterExpression="userId = :userId";
    ArrayList<T> result= scanWithFilter(cls,filterExpression,null,filterExpressionAttributeValues);
    return result;
}

    public <T> ArrayList batchLoad(Class<T> cls,final List keys,final String tableName) {
        LinkedBlockingQueue queue = new LinkedBlockingQueue(1);
        new Thread(new BatchLoad<T>(queue,keys,tableName)).start();
        ArrayList<T> result=null;
        try {
            result= (ArrayList<T>) queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> T loadItem(final T key) {
        LinkedBlockingQueue queue = new LinkedBlockingQueue(1);
        new Thread(new LoadItem<T>(queue,key)).start();
        T result=null;
        try {
            result= (T) queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> ArrayList scanWithFilter(final Class<T> cls,final String filterExpression,final Map <String, String> filterExpressionAttributeNames ,final Map<String, AttributeValue> filterExpressionAttributeValues)
    {
        LinkedBlockingQueue queue = new LinkedBlockingQueue(1);

        new Thread(new ScanWithFilter<T>(queue,cls,filterExpression,filterExpressionAttributeNames,filterExpressionAttributeValues)).start();
        ArrayList<T> result=null;
        try {
          result= (ArrayList<T>) queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public class LoadItem<T> implements Runnable{
        private LinkedBlockingQueue queue;
        private T key;

        public LoadItem(LinkedBlockingQueue queue,T key)
        {
            this.queue=queue;
            this.key=key;
        }
        @Override
        public void run() {

            T result=mapper.load(key);
            try {
                queue.put(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public class BatchLoad<T> implements Runnable{
        private LinkedBlockingQueue queue;
        private String tableName;
        private List keys;

        public BatchLoad(LinkedBlockingQueue queue,List keys,String tableName)
        {
            this.queue=queue;
            this.tableName=tableName;
            this.keys=keys;
        }
        @Override
        public void run() {

            Map m=mapper.batchLoad(keys);
            List<T> res=new ArrayList<T>((List)m.get(tableName));

                try {
                    queue.put(res);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

public class ScanWithFilter<T> implements Runnable{
    private LinkedBlockingQueue queue;
    private String filterExpression;
    private Map <String, String> filterExpressionAttributeNames;
    private Map<String, AttributeValue> filterExpressionAttributeValues;
    private Class<T> cls;
    public ScanWithFilter(LinkedBlockingQueue queue,Class<T> cls,String filterExpression,Map <String, String> filterExpressionAttributeNames,Map<String, AttributeValue> filterExpressionAttributeValues)
    {
        this.queue=queue;
        this.filterExpression=filterExpression;
        this.filterExpressionAttributeNames=filterExpressionAttributeNames;
        this.filterExpressionAttributeValues=filterExpressionAttributeValues;
        this.cls=cls;
    }
    @Override
    public void run() {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression(filterExpression)
                .withExpressionAttributeNames(filterExpressionAttributeNames)
                .withExpressionAttributeValues(filterExpressionAttributeValues);

        PaginatedScanList<T> results = mapper.scan(cls, scanExpression);
        if (results != null) {
            Iterator<T> resultsIterator = results.iterator();
            ArrayList<T> res=new ArrayList<>();
            while (resultsIterator.hasNext()) {
                res.add(resultsIterator.next());
            }
            try {
                queue.put(res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
}
