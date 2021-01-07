package com.jh.blogpost.facebook

import com.jh.blogpost.integration.IntegrationCredentials
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("local")
class LocalCredentials(name: String, password: String): IntegrationCredentials(name, password) {
}
