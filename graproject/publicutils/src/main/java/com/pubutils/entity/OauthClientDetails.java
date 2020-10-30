package com.pubutils.entity;

/**
 * @author pb
 */
public class OauthClientDetails {
    /**
     * 
     */
    private String clientId;

    /**
     * 
     */
    private String resourceIds;

    /**
     * 
     */
    private String clientSecret;

    /**
     * 
     */
    private String scope;

    /**
     * 
     */
    private String authorizedGrantTypes;

    /**
     * 
     */
    private String webServerRedirectUri;

    /**
     * 
     */
    private String authorities;

    /**
     * 
     */
    private Integer accessTokenValidity;

    /**
     * 
     */
    private Integer refreshTokenValidity;

    /**
     * 
     */
    private String additionalInformation;

    /**
     * 
     */
    private String autoapprove;

    /**
     * 
     * @return client_id 
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 
     * @param clientId 
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * 
     * @return resource_ids 
     */
    public String getResourceIds() {
        return resourceIds;
    }

    /**
     * 
     * @param resourceIds 
     */
    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    /**
     * 
     * @return client_secret 
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * 
     * @param clientSecret 
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    /**
     * 
     * @return scope 
     */
    public String getScope() {
        return scope;
    }

    /**
     * 
     * @param scope 
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**
     * 
     * @return authorized_grant_types 
     */
    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    /**
     * 
     * @param authorizedGrantTypes 
     */
    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes == null ? null : authorizedGrantTypes.trim();
    }

    /**
     * 
     * @return web_server_redirect_uri 
     */
    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    /**
     * 
     * @param webServerRedirectUri 
     */
    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri == null ? null : webServerRedirectUri.trim();
    }

    /**
     * 
     * @return authorities 
     */
    public String getAuthorities() {
        return authorities;
    }

    /**
     * 
     * @param authorities 
     */
    public void setAuthorities(String authorities) {
        this.authorities = authorities == null ? null : authorities.trim();
    }

    /**
     * 
     * @return access_token_validity 
     */
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    /**
     * 
     * @param accessTokenValidity 
     */
    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    /**
     * 
     * @return refresh_token_validity 
     */
    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    /**
     * 
     * @param refreshTokenValidity 
     */
    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    /**
     * 
     * @return additional_information 
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * 
     * @param additionalInformation 
     */
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation == null ? null : additionalInformation.trim();
    }

    /**
     * 
     * @return autoapprove 
     */
    public String getAutoapprove() {
        return autoapprove;
    }

    /**
     * 
     * @param autoapprove 
     */
    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove == null ? null : autoapprove.trim();
    }
}