import React, { useState, useEffect } from "react";
import DayOverviewService from "../services/DayOverviewService";


const DayOverviews = props => {
  const initialTutorialState = {
    name: "",
    date: null,
    rooms: [],
    items: []
  };

  const [currentEvent, setCurrentEvent] = useState(initialTutorialState);
  const [message, setMessage] = useState("");

  const getEvent = id => {
    DayOverviewService.get(id)
      .then(response => {
        setCurrentEvent(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  useEffect(() => {
    getEvent(props.match.params.id);
  }, [props.match.params.id]);

  const handleInputChange = event => {
    const { name, value } = event.target;
    setCurrentEvent({ ...currentEvent, [name]: value });
  };

  const updateEvent = () => {
    DayOverviewService.update(currentEvent.id, currentEvent)
      .then(response => {
        setMessage("Event was updated successfully!");
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  return (
    <div>
      {currentEvent ? (
        <div className="edit-form">
          <h4>Events</h4>
          <form>
            <div className="form-group">
              <label htmlFor="title">Name</label>
              <input
                type="text"
                className="form-control"
                id="name"
                name="name"
                value={currentEvent.name}
                onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="description">Begin date</label>
              <input
                  type="text"
                  className="form-control"
                  id="beginDate"
                  name="beginDate"
                  value={currentEvent.beginDate}
                  onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="description">End date</label>
              <input
                  type="text"
                  className="form-control"
                  id="endDate"
                  name="endDate"
                  value={currentEvent.endDate}
                  onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="description">Location</label>
              <input
                  type="text"
                  className="form-control"
                  id="location"
                  name="location"
                  value={currentEvent.location.name}
                  onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="description">Topics</label>
              <input
                  type="text"
                  className="form-control"
                  id="topics"
                  name="topics"
                  value={currentEvent.topics}
                  onChange={handleInputChange}
              />
            </div>
          </form>

          <button
            type="submit"
            className="badge badge-success"
            onClick={updateEvent}
          >
            Update
          </button>
          <p>{message}</p>
        </div>
      ) : (
        <div>
          <br />
          <p>Please click on a Event...</p>
        </div>
      )}
    </div>
  );
};

export default DayOverviews;
