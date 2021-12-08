import { useState } from "react";
import { Card, FloatingLabel, Form, Button, Container} from "react-bootstrap"
import { useNavigate, useLocation } from "react-router-dom";
import "./CreateAccountForm.css";
import apiServerUrl from "../../apiServerUrl";

export default function createAccountForm() {


  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [userAge, setUserAge] = useState("");
  const [afterResponse, setAfterResponse] = useState(false);
  
  const navigate = useNavigate();
  const location = useLocation();
  function formHandler() {
    const reqBody = {
      firstName,
      lastName,
      password,
      username,
      userAge
    }
    fetch(`${apiServerUrl}/auth/signup`, {
      method: "POST", 
      mode: "cors",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(reqBody)
    })
    .then(response => response.json())
    .then((data) => {
      console.log(data);
      setAfterResponse(true);
      navigate(location.pathname);
    });
  }

  return (
    <Container className="create-account-form-container">
    <Card >
      <Card.Body >
        <Card.Title >Make your reservation on this pitch!</Card.Title> 
        {afterResponse ? 
        <Card.Subtitle className="text-success create-account-form-subtitle">
          Account created successfully. Now you need to login!
        </Card.Subtitle> : 
        
        <Card.Subtitle className="text-danger create-account-form-subtitle">
          But first you need to create an account!
        </Card.Subtitle>
        } 
        <FloatingLabel controlId="floatingFirstName" label="First name">
        <Form.Control type="text" placeholder="First name" className="create-account-form-control" 
        value={firstName}
        onChange={(event) => {setFirstName(event.target.value)}}/>
        </FloatingLabel>

        <FloatingLabel controlId="floatingLastName" label="Last name">
        <Form.Control type="text" placeholder="Last name" className="create-account-form-control"
        value={lastName}
        onChange={(event) => {setLastName(event.target.value)}}/>
        </FloatingLabel>

        <FloatingLabel controlId="floatingUserName" label="Username">
        <Form.Control type="text" placeholder="Username" className="create-account-form-control"
        value={username}
        onChange={(event) => {setUsername(event.target.value)}}/>
        </FloatingLabel>

        <FloatingLabel controlId="floatingPassword" label="Password">
        <Form.Control type="password" placeholder="Password" className="create-account-form-control"
        value={password}
        onChange={(event) => {setPassword(event.target.value)}}/>
        </FloatingLabel>

        <FloatingLabel controlId="floatingAge" label="Age">
        <Form.Control type="text" placeholder="Age" className="create-account-form-control"
        value={userAge}
        onChange={(event) => {setUserAge(event.target.value)}}/>
        </FloatingLabel>

      <Button variant="outline-success" onClick={formHandler}>Create Account</Button>
      </Card.Body>
    </Card>
    </Container>
  )
}