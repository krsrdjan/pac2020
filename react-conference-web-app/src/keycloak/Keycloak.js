import React, {useState, useEffect} from 'react';
import {keycloak} from "../keycloak";

function Keycloak({children}) {
    // We'll use this variable to halt the app
    // execution until the user is Authenticated
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    // The `init()` method we'll be in charge of starting
    // the authentication flow.

    useEffect(() => {
        keycloak
            .init({
                // The `onLoad` option can be configured
                // with two possible values:
                // - `login-required`
                // - `check-sso`
                // Both do the same, except the first one
                // redirects the user to the login page if
                // he's not authenticated.
                onLoad: 'login-required',
                checkLoginIframe: false,
            })
            .then((authenticated) => {
                if (authenticated) {
                    localStorage.setItem('kc_token', keycloak.token);
                    localStorage.setItem('kc_refreshToken', keycloak.refreshToken);
                }

                // We can continue rendering the app
                // now that the user has been authenticated
                setIsAuthenticated(authenticated)
            })
            .catch((err) => {
                // Log an error method if something went
                // wrong.
                console.error(err);
            });
    }, []);
    // We'll render the component `children` only after the
    // user has been authenticated.
    if (isAuthenticated === false) {
        return <div></div>
    }
    return children;
}

export default Keycloak;