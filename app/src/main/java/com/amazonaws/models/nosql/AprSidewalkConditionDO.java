package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.amazonaws.model.base.ModelBase;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "apr-mobilehub-394112265-apr_sidewalk_condition")

public class AprSidewalkConditionDO extends ModelBase{
    private String _scId;
    private String _appCode;
    private String _attachedComment;
    private byte[] _attachedImage;
    private byte[] _attachedVoice;
    private Double _gpsLat;
    private Double _gpsLong;
    private String _sidewalkConditionDescription;
    private Double _sidewalkConditionId;
    private String _userId;
    private String _username;
    private Double _scTimestamp;

    public AprSidewalkConditionDO(){
        super();
    }
public void save()
{
    super.save(this);
}
    @DynamoDBHashKey(attributeName = "sc_id")
    @DynamoDBAttribute(attributeName = "sc_id")
    public String getScId() {
        return _scId;
    }

    public void setScId(final String _scId) {
        this._scId = _scId;
    }

    @DynamoDBAttribute(attributeName = "sc_timestamp")
    public Double getScTimestamp() {
        return _scTimestamp;
    }

    public void setScTimestamp(final Double _scTimestamp) {
        this._scTimestamp = _scTimestamp;
    }

    @DynamoDBAttribute(attributeName = "app_code")
    public String getAppCode() {
        return _appCode;
    }

    public void setAppCode(final String _appCode) {
        this._appCode = _appCode;
    }
    @DynamoDBAttribute(attributeName = "attached_comment")
    public String getAttachedComment() {
        return _attachedComment;
    }

    public void setAttachedComment(final String _attachedComment) {
        this._attachedComment = _attachedComment;
    }
    @DynamoDBAttribute(attributeName = "attached_image")
    public byte[] getAttachedImage() {
        return _attachedImage;
    }

    public void setAttachedImage(final byte[] _attachedImage) {
        this._attachedImage = _attachedImage;
    }
    @DynamoDBAttribute(attributeName = "attached_voice")
    public byte[] getAttachedVoice() {
        return _attachedVoice;
    }

    public void setAttachedVoice(final byte[] _attachedVoice) {
        this._attachedVoice = _attachedVoice;
    }
    @DynamoDBAttribute(attributeName = "gps_lat")
    public Double getGpsLat() {
        return _gpsLat;
    }

    public void setGpsLat(final Double _gpsLat) {
        this._gpsLat = _gpsLat;
    }
    @DynamoDBAttribute(attributeName = "gps_long")
    public Double getGpsLong() {
        return _gpsLong;
    }

    public void setGpsLong(final Double _gpsLong) {
        this._gpsLong = _gpsLong;
    }
    @DynamoDBAttribute(attributeName = "sidewalk_condition_description")
    public String getSidewalkConditionDescription() {
        return _sidewalkConditionDescription;
    }

    public void setSidewalkConditionDescription(final String _sidewalkConditionDescription) {
        this._sidewalkConditionDescription = _sidewalkConditionDescription;
    }
    @DynamoDBAttribute(attributeName = "sidewalk_condition_id")
    public Double getSidewalkConditionId() {
        return _sidewalkConditionId;
    }

    public void setSidewalkConditionId(final Double _sidewalkConditionId) {
        this._sidewalkConditionId = _sidewalkConditionId;
    }
    @DynamoDBAttribute(attributeName = "user_id")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "username")
    public String getUsername() {
        return _username;
    }

    public void setUsername(final String _username) {
        this._username = _username;
    }

}
