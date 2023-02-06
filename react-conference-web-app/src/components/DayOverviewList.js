import React, {useState, useEffect, forwardRef} from "react";
import DayOverviewService from "../services/DayOverviewService";

const DayOverviewList = () => {
  const [events, setEvents] = useState([]);
  const [currentEvent, setCurrentEvent] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);

  useEffect(() => {
    retrieveEvents();
  }, []);

  const retrieveEvents = () => {
    DayOverviewService.getAll()
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
        <div className="col-md-4">
          <h4>Day overview list</h4>
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
                  <br/>
                  {event.date.substr(0, 10)}
                </li>
            ))}
          </ul>

        </div>
        <div className="col-md-8">
          {currentEvent ? (
              <div>
                <h4>Day overview</h4>
                <div>
                  <label>
                    <strong>Name:</strong>
                  </label>{" "}
                  {currentEvent.name}
                </div>
                <div>
                  <label>
                    <strong>Date:</strong>
                  </label>{" "}
                  {currentEvent.date.substr(0, 10)}
                </div>
                <div className="row">
                    <div className="col-lg-4 border-dark border ">Time\Rooms</div>
                      {
                          currentEvent.rooms.map((item, index) =>
                                <div className="col-lg-4 border-dark border ">{item}</div>
                          )
                      }
                </div>
                  {
                          currentEvent.items.map((item, i) =>
                              <div className="row">
                                  <div className="col-lg-4 border-dark border ">{item.time.split("T")[1].split(":00.")[0]}</div>
                                  <div className="col-lg-4 border-dark border ">{item.cells[0] ? item.cells[0].title +"\n"+ item.cells[0].topics + "\n"
                                      + item.cells[0].persons + "\n" + item.cells[0].level: "n/a"}</div>
                                  <div className="col-lg-4 border-dark border ">{item.cells[1] ? item.cells[1].title +"\n"+  item.cells[1].topics + "\n"
                                      + item.cells[1].persons + "\n" + item.cells[1].level: "n/a"}</div>
                              </div>

                          )
                  }
              </div>
          ) : (
              <div>
                <br />
                <p>Please click on a Day overview...</p>
              </div>
          )}
        </div>
      </div>
  );
};

export default DayOverviewList;
