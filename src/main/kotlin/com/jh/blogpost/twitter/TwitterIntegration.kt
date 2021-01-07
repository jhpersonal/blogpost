package com.jh.blogpost.facebook

import com.jh.blogpost.integration.Integration
import com.jh.blogpost.integration.IntegrationCredentials
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity


//TODO: change integrationcredentials to Twittercredentials
@Entity
@DiscriminatorValue("twitter")
class TwitterIntegration(name: String, integrationCredentials: IntegrationCredentials
): Integration(name, integrationCredentials)
