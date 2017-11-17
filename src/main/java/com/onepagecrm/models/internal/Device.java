package com.onepagecrm.models.internal;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.serializers.DeleteResultSerializer;
import com.onepagecrm.models.serializers.DeviceListSerializer;
import com.onepagecrm.models.serializers.DeviceSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.DeleteRequest;
import com.onepagecrm.net.request.GetRequest;
import com.onepagecrm.net.request.PostRequest;
import com.onepagecrm.net.request.Request;

import java.util.Date;
import java.util.List;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 26/10/2016.
 */
public class Device extends ApiResource {

    public static final String TYPE_ANDROID = "android_app";
    public static final String TYPE_IOS = "ios_app";

    private String id;
    private String deviceId;
    private Boolean actionWithTime;
    private Date subscribedAt;
    private String deviceType;

    public static List<Device> list() throws OnePageException {
        Request request = new GetRequest(DEVICE_ENDPOINT);
        Response response = request.send();
        return DeviceListSerializer.fromString(response.getResponseBody());
    }

    public Device register() throws OnePageException {
        Request request = new PostRequest(
                DEVICE_ENDPOINT,
                null,
                DeviceSerializer.toJsonObject(this)
        );
        Response response = request.send();
        return DeviceSerializer.fromString(response.getResponseBody());
    }

    public DeleteResult delete() throws OnePageException {
        final String resourceId = this.id;
        Request request = new DeleteRequest(
                withId(DEVICE_ENDPOINT, resourceId),
                null
        );
        Response response = request.send();
        return DeleteResultSerializer.fromString(resourceId, response.getResponseBody());
    }

    public Device() {

    }

    private String withId(String endpoint, String id) {
        return endpoint + "/" + id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Device setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return DeviceSerializer.toJsonObject(this);
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Device setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public Boolean getActionWithTime() {
        return actionWithTime;
    }

    public Device setActionWithTime(Boolean actionWithTime) {
        this.actionWithTime = actionWithTime;
        return this;
    }

    public Date getSubscribedAt() {
        return subscribedAt;
    }

    public Device setSubscribedAt(Date subscribedAt) {
        this.subscribedAt = subscribedAt;
        return this;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public Device setDeviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }
}
