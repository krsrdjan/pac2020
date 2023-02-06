import React, { useState, useEffect } from "react";
import TalkService from "../services/TalkService";

const Talks = props => {
  const initialTutorialState = {
    id: null,
    name: "",
    level: "",
    language: "",
    persons: "",
    topicsString: "",
    event: ""
  };

  const [currentTalk, setCurrentTalk] = useState(initialTutorialState);
  const [message, setMessage] = useState("");

  const getTalk = id => {
    TalkService.get(id)
      .then(response => {
        setCurrentTalk(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  useEffect(() => {
    getTalk(props.match.params.id);
  }, [props.match.params.id]);

  const handleInputChange = event => {
    const { name, value } = event.target;
    setCurrentTalk({ ...currentTalk, [name]: value });
  };

  const updateTalk = () => {
    TalkService.update(currentTalk.id, currentTalk)
      .then(response => {
        setMessage("Talk was updated successfully!");
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  return (
    <div>
      {currentTalk ? (
        <div className="edit-form">
          <h4>Talks</h4>
          <form>
            <div className="form-group">
              <label htmlFor="title">Title</label>
              <input
                type="text"
                className="form-control"
                id="title"
                name="title"
                value={currentTalk.title}
                onChange={handleInputChange}
              />
            </div>

          </form>

          <button
            type="submit"
            className="badge badge-success"
            onClick={updateTalk}
          >
            Update
          </button>
          <p>{message}</p>
        </div>
      ) : (
        <div>
          <br />
          <p>Please click on a Talk...</p>
        </div>
      )}
    </div>
  );
};

export default Talks;
