package com.jh.blogpost.facebook

import com.jh.blogpost.integration.Integration
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("facebook")
class FacebookIntegration(
    name: String,
    integrationCredentials: FacebookCredentials
): Integration(name, integrationCredentials)

