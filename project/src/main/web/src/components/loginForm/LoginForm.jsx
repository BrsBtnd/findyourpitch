import { useState } from "react"
import { FloatingLabel, Form, Button, Container } from "react-bootstrap"
import "./LoginForm.css"
import apiServerUrl from "../../apiServerUrl";
export default function loginForm() {
  
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  function login() {

    const request = {
      method: 'POST',
      mode: 'no-cors',
      headers: {
        'Content-Type': 'application/json'
      },
      body: {
        userName: JSON.stringify(username),
        password: JSON.stringify(password)
      }
    }
    
    const reqBody = {
      userName: username,
      password: password,
    }

    console.log(JSON.stringify(reqBody));
    fetch(`http://localhost:8080/api/auth/login`, {
      method: "POST",
      mode: "cors",
      headers: {
        'Content-Type': 'application/json'
      }, 
      body: JSON.stringify(reqBody)
    })
    .then(response => response.json())
    .then((userData) => {
      console.log(userData);
    });
  }

  return(
    <>
    <Container>
      <FloatingLabel
        controlId="floatingInputUsername"
        label="Username"
        className="mb-3"
      >
        <Form.Control type="text" placeholder="Username" 
          value={username}
          onChange={(event) => setUsername(event.target.value)} />
      </FloatingLabel>
      <FloatingLabel controlId="floatingPassword" label="Password">
        <Form.Control type="password" placeholder="Password" 
        value={password}
        onChange={(event) => setPassword(event.target.value)}/>
      </FloatingLabel>

      <Button variant="outline-success" className="login-form-button mx-auto" onClick={login}>Login</Button>
    </Container>
  </>
    
  )
}