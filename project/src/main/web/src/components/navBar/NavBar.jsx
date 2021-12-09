import { useEffect, useState } from "react";
import { Container, Navbar, Nav, NavLink, Button, Offcanvas } from "react-bootstrap";
import LoginOffCanvas from "../loginOffCanvas/LoginOffCanvas";
import LogoutButton from "../logoutButton/LogoutButton";
import UserReservationsButton from "../userReservationsButton/UserReservationsButton";
import { Link } from "react-router-dom";

export default function NavBar() {

  const [button, setButton] = useState([]);

  function loginOrLogout() {
    if(!localStorage.getItem("userTokenInfo")) {
      return <LoginOffCanvas />
    } 
    return <LogoutButton />;
  }
  
  return (
    <Navbar>
      <Container> 
        <Nav variant="tabs">
          <Navbar.Brand >
            <NavLink to="/" as={Link}>FindYourPitch</NavLink> 
          </Navbar.Brand>
        </Nav>
        <Nav onClick={setButton}>
        {!!localStorage.getItem("userTokenInfo") ? <UserReservationsButton loggedin={true}/> :  <></>}
          {loginOrLogout()}
        </Nav>
      </Container>
    </Navbar>
  )
}