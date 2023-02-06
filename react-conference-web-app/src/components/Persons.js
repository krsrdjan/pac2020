import React, { useState, useEffect } from "react";
import PersonService from "../services/PersonService";

const Persons = props => {
  const initialTutorialState = {
    id: null,
    name: "",
    talksString: ""
  };

  const [currentPerson, setCurrentPerson] = useState(initialTutorialState);
  const [message, setMessage] = useState("");

  const getPerson = id => {
    PersonService.get(id)
      .then(response => {
        setCurrentPerson(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  useEffect(() => {
    getPerson(props.match.params.id);
  }, [props.match.params.id]);

  const handleInputChange = event => {
    const { name, value } = event.target;
    setCurrentPerson({ ...currentPerson, [name]: value });
  };

  const updatePerson = () => {
    PersonService.update(currentPerson.id, currentPerson)
      .then(response => {
        setMessage("Person was updated successfully!");
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  return (
    <div>
      {currentPerson ? (
        <div className="edit-form">
          <h4>Persons</h4>
          <form>
            <div className="form-group">
              <label htmlFor="name">First Name</label>
              <input
                type="text"
                className="form-control"
                id="name"
                name="name"
                value={currentPerson.name}
                onChange={handleInputChange}
              />
            </div>

          </form>

          <button
            type="submit"
            className="badge badge-success"
            onClick={updatePerson}
          >
            Update
          </button>
          <p>{message}</p>
        </div>
      ) : (
        <div>
          <br />
          <p>Please click on a Person...</p>
        </div>
      )}
    </div>
  );
};

export default Persons;
