import { Offcanvas, Button } from "react-bootstrap"
import LoginForm from "../loginForm/LoginForm"
import { useState } from "react";


export default function loginOffCanvas() {

  const [show, setShow] = useState(false);

  const handleShow = () => setShow(true);
  const handleClose = () => setShow(false);

  return(
    <> 
    <Button variant="outline-success" onClick={handleShow}>Login</Button>
    <Offcanvas show={show} onHide={handleClose} placement="end">
          <Offcanvas.Header closeButton>
            <Offcanvas.Title>Login</Offcanvas.Title>
          </Offcanvas.Header>
          <Offcanvas.Body>
            <LoginForm/>
          </Offcanvas.Body>
        </Offcanvas>
    
    </>
  )
}