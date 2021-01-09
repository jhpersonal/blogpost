package com.jh.blogpost.security

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.PrintWriter
import javax.naming.AuthenticationException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BlogPostBasicAuthenticationEntryPoint: BasicAuthenticationEntryPoint() {

    @Override    // throws IOException, ServletException
    fun commence(request: HttpServletRequest, response: HttpServletResponse, authEx: AuthenticationException)
    {
        response.addHeader("WWW-Authenticate", "Basic realm='$realmName'")
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val writer: PrintWriter = response.writer
        writer.println("HTTP Status 401 - ${authEx.message}")
    }


//    @Throws(Exception)
    override fun afterPropertiesSet()  {
        realmName = "BlogPost"
        super.afterPropertiesSet()
    }

}