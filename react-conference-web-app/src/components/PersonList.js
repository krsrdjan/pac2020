import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import PersonService from "../services/PersonService";

const PersonList = () => {
  const [persons, setPersons] = useState([]);
  const [currentPerson, setCurrentPerson] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);

  useEffect(() => {
    retrievePersons();
  }, []);

  const retrievePersons = () => {
    PersonService.getAll()
      .then(response => {
        setPersons(response.data);
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  const setActivePerson = (person, index) => {
    setCurrentPerson(person);
    setCurrentIndex(index);
  };

  return (
    <div className="list row">
      <div className="col-md-6">
        <h4>Person List</h4>

        <ul className="list-group">
          {persons &&
            persons.map((person, index) => (
              <li
                className={
                  "list-group-item " + (index === currentIndex ? "active" : "")
                }
                onClick={() => setActivePerson(person, index)}
                key={index}
              >
                {person.name}
              </li>
            ))}
        </ul>

      </div>
      <div className="col-md-6">
        {currentPerson ? (
          <div>
            <h4>Person</h4>
            <div>
              <label>
                <strong>First Name:</strong>
              </label>{" "}
              {currentPerson.name}
            </div>
            <div>
              <label>
                <strong>Talks:</strong>
              </label>{" "}
              {currentPerson.talksString}
            </div>
            <Link
              to={"/persons/" + currentPerson.id}
              className="badge badge-warning"
            >
              Edit
            </Link>
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Person...</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default PersonList;
