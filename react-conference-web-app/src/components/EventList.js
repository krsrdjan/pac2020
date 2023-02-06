import React, { useState, useEffect } from "react";
import EventService from "../services/EventsService";

const EventList = () => {
  const [events, setEvents] = useState([]);
  const [currentEvent, setCurrentEvent] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);

  useEffect(() => {
    retrieveEvents();
  }, []);

  const retrieveEvents = () => {
    EventService.getAll()
      .then(response => {
        setEvents(response.data);
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  const setActiveEvent = (event, index) => {
    setCurrentEvent(event);
    setCurrentIndex(index);
  };

  return (
    <div className="list row">
      <div className="col-md-6">
        <h4>Event List</h4>
        <ul className="list-group">
          {events &&
            events.map((event, index) => (
              <li
                className={
                  "list-group-item " + (index === currentIndex ? "active" : "")
                }
                onClick={() => setActiveEvent(event, index)}
                key={index}
              >
                {event.name}
              </li>
            ))}
        </ul>

      </div>
      <div className="col-md-6">
        {currentEvent ? (
          <div>
            <h4>Event</h4>
            <div>
              <label>
                <strong>Name:</strong>
              </label>{" "}
              {currentEvent.name}
            </div>
            <div>
              <label>
                <strong>Start date:</strong>
              </label>{" "}
              {currentEvent.beginDate.split("T")[0]}
            </div>
            <div>
              <label>
                <strong>End date:</strong>
              </label>{" "}
              {currentEvent.endDate.split("T")[0]}
            </div>
            <div>
              <label>
                <strong>Location:</strong>
              </label>{" "}
              {currentEvent.location.name}
            </div>
            <div>
              <label>
                <strong>Topics:</strong>
              </label>{" "}
              {currentEvent.topics}
            </div>
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Event...</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default EventList;
