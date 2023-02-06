let keycloak;

function init() {
    // If the Keycloak constructor doesn't exist we'll throw
    // an error.

    if (window.Keycloak === undefined) {
        console.log('Can\'t find Keycloak on the global scope');
    } else {
        keycloak = new window.Keycloak({
            url: `${process.env.REACT_APP_KEYCLOAK_URL}`,
            realm: `${process.env.REACT_APP_KEYECLOAK_REALM}`,
            clientId: `${process.env.REACT_APP_KEYECLOAK_ClIENT_ID}`,
            onLoad: "login-required",
        });
    }
}

export {keycloak, init};
