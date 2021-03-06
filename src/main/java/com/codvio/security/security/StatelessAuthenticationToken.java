
package com.codvio.security.security;


import com.codvio.security.dto.vo.AuthenticatedUserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class StatelessAuthenticationToken implements Authentication {



    //~ --- [INSTANCE FIELDS] ------------------------------------------------------------------------------------------

    private boolean               authenticated;
    private AuthenticatedUserInfo authenticatedUserInfo;
    private final String          token;



    //~ --- [CONSTRUCTORS] ---------------------------------------------------------------------------------------------

    public StatelessAuthenticationToken(final String token) {

        this.token         = token;
        this.authenticated = true;
    }



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    public AuthenticatedUserInfo getAuthenticatedUserInfo() {

        return authenticatedUserInfo;
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (authenticatedUserInfo != null) {
            final List<String> roles = authenticatedUserInfo.getRoles();

            if (roles != null && !roles.isEmpty()) {
                final ArrayList<GrantedAuthority> authorities = new ArrayList<>();

                for (final String role : roles) {
                    authorities.add((GrantedAuthority) () -> role);
                }

                return authorities;
            }
        }

        return null;
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public Object getCredentials() {

        return token;
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public Object getDetails() {

        return authenticatedUserInfo;
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public String getName() {

        if (authenticatedUserInfo != null) {
            return authenticatedUserInfo.getDisplayName();
        }

        return null;
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public Object getPrincipal() {

        if (authenticatedUserInfo != null) {
            return authenticatedUserInfo.getPrincipal();
        }

        return null;
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public String getToken() {

        return token;
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public boolean isAuthenticated() {

        return authenticated;
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {

        this.authenticated = isAuthenticated;
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public void setAuthenticatedUserInfo(final AuthenticatedUserInfo authenticatedUserInfo) {

        this.authenticatedUserInfo = authenticatedUserInfo;
    }
}
