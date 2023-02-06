import React from "react";
import Keycloak from "../keycloak/Keycloak";
import TalkList from "./TalkList";

const TalkListProtected = () => {

    if (`${process.env.REACT_APP_ENABLE_KEYCLOAK}` && window.Keycloak !== undefined) {
        return (
            <Keycloak>
                <TalkList/>
            </Keycloak>
        )
    } else {
        return <TalkList/>
    }
}

export default TalkListProtected;