import React from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import Persons from "./components/Persons";
import PersonList from "./components/PersonList";
import EventList from "./components/EventList";
import Events from "./components/Events";
import TalkList from "./components/TalkList";
import Talks from "./components/Talks";
import DayOverviewList from "./components/DayOverviewList";
import Keycloak from "./keycloak/Keycloak";
import TalkListProtected from "./components/TalkListProtected";


function App() {
  return (
    <Router>
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <a href="/events" className="navbar-brand">
            PAC 2020 Conference Web APP
          </a>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/events"} className="nav-link">
                Events
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/persons"} className="nav-link">
                Persons
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/talks"} className="nav-link">
                Talks
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/talks-protected"} className="nav-link">
                Talks protected
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/overviews"} className="nav-link">
                DayOverviews
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/events"]} component={EventList} />
            <Route exact path={["/", "/persons"]} component={PersonList} />
            <Route exact path={["/", "/talks"]} component={TalkList} />
            <Route exact path={["/", "/talks-protected"]} component={TalkListProtected} />
            <Route exact path={["/", "/overviews"]} component={DayOverviewList} />

            <Route path="/events/:id" component={Events} />
            <Route path="/persons/:id" component={Persons} />
            <Route path="/talks/:id" component={Talks} />
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
