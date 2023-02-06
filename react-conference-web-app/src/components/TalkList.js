import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import TalkService from "../services/TalkService";

const TalkList = () => {
  const [talks, setTalks] = useState([]);
  const [currentTalk, setCurrentTalk] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);

  useEffect(() => {
    retrieveTalks();
  }, []);

  const retrieveTalks = () => {
    TalkService.getAll()
      .then(response => {
        setTalks(response.data);
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  const setActiveTalk = (Talk, index) => {
    setCurrentTalk(Talk);
    setCurrentIndex(index);
  };


  return (
    <div className="list row">
      <div className="col-md-6">
        <h4>Talk List</h4>

        <ul className="list-group">
          {talks &&
            talks.map((Talk, index) => (
              <li
                className={
                  "list-group-item " + (index === currentIndex ? "active" : "")
                }
                onClick={() => setActiveTalk(Talk, index)}
                key={index}
              >
                {Talk.title}
              </li>
            ))}
        </ul>

      </div>
      <div className="col-md-6">
        {currentTalk ? (
          <div>
            <h4>Talk</h4>
            <div>
              <label>
                <strong>Title:</strong>
              </label>{" "}
              {currentTalk.title}
            </div>
            <div>
              <label>
                <strong>Level:</strong>
              </label>{" "}
              {currentTalk.level.name}
            </div>
            <div>
              <label>
                <strong>Language:</strong>
              </label>{" "}
              {currentTalk.language.name}
            </div>
            <div>
              <label>
                <strong>Persons:</strong>
              </label>{" "}
              {currentTalk.persons}
            </div>
            <div>
              <label>
                <strong>Topics:</strong>
              </label>{" "}
              {currentTalk.topicsString}
            </div>
            <div>
              <label>
                <strong>Event:</strong>
              </label>{" "}
              {currentTalk.event.name}
            </div>
            <Link
              to={"/talks/" + currentTalk.id}
              className="badge badge-warning"
            >
              Edit
            </Link>
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Talk...</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default TalkList;
