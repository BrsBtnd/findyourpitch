import { useState } from "react"
import { FloatingLabel, Form, Button, Container } from "react-bootstrap"
import "./LoginForm.css"
import { useNavigate, useLocation } from "react-router-dom";
import apiServerUrl from "../../apiServerUrl";
export default function loginForm() {
  
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();
  const location = useLocation();

  function login() {

    const reqBody = {
      userName: username,
      password: password,
    }

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
      const userTokenInfo = {
        jwtToken: userData.token, 
        userID: userData.userID,
        userName: userData.userName,
        userRole: userData.roles[0]
      }
      localStorage.setItem("userTokenInfo", JSON.stringify(userTokenInfo));
      navigate(location.pathname);
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