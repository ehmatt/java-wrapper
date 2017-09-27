package com.onepagecrm.models.internal;

import com.onepagecrm.models.serializers.S3FileSerializer;

import java.io.Serializable;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 27/09/2017.
 */
public class S3File implements Serializable {

    private Long quota;
    private String displayQuota;
    private String url;
    private String key;
    private Integer successStatus;
    private String acl;
    private String policy;
    private String xIgnorePattern;
    private String xAmzAlgorithm;
    private String xAmzCredential;
    private String xAmzDate;
    private String xAmzSignature;

    public S3File() {

    }

    @Override
    public String toString() {
        return S3FileSerializer.toJsonString(this);
    }

    public Long getQuota() {
        return quota;
    }

    public S3File setQuota(Long quota) {
        this.quota = quota;
        return this;
    }

    public String getDisplayQuota() {
        return displayQuota;
    }

    public S3File setDisplayQuota(String displayQuota) {
        this.displayQuota = displayQuota;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public S3File setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getKey() {
        return key;
    }

    public S3File setKey(String key) {
        this.key = key;
        return this;
    }

    public Integer getSuccessStatus() {
        return successStatus;
    }

    public S3File setSuccessStatus(Integer successStatus) {
        this.successStatus = successStatus;
        return this;
    }

    public String getAcl() {
        return acl;
    }

    public S3File setAcl(String acl) {
        this.acl = acl;
        return this;
    }

    public String getPolicy() {
        return policy;
    }

    public S3File setPolicy(String policy) {
        this.policy = policy;
        return this;
    }

    public String getxIgnorePattern() {
        return xIgnorePattern;
    }

    public S3File setxIgnorePattern(String xIgnorePattern) {
        this.xIgnorePattern = xIgnorePattern;
        return this;
    }

    public String getxAmzAlgorithm() {
        return xAmzAlgorithm;
    }

    public S3File setxAmzAlgorithm(String xAmzAlgorithm) {
        this.xAmzAlgorithm = xAmzAlgorithm;
        return this;
    }

    public String getxAmzCredential() {
        return xAmzCredential;
    }

    public S3File setxAmzCredential(String xAmzCredential) {
        this.xAmzCredential = xAmzCredential;
        return this;
    }

    public String getxAmzDate() {
        return xAmzDate;
    }

    public S3File setxAmzDate(String xAmzDate) {
        this.xAmzDate = xAmzDate;
        return this;
    }

    public String getxAmzSignature() {
        return xAmzSignature;
    }

    public S3File setxAmzSignature(String xAmzSignature) {
        this.xAmzSignature = xAmzSignature;
        return this;
    }
}
