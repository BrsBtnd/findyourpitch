import { useEffect, useState } from "react";
import { Container, Navbar, Nav, NavLink, Button, Offcanvas } from "react-bootstrap";
import LoginOffCanvas from "../loginOffCanvas/LoginOffCanvas";
import LogoutButton from "../logoutButton/LogoutButton";

export default function NavBar() {

  function loginOrLogout() {
    if(!localStorage.getItem("jwtToken")) {
      return <LoginOffCanvas />
    } 
    return <LogoutButton />;
  }

  return (
    <Navbar>
      <Container>
        <Nav variant="tabs">
          <Navbar.Brand >
            <NavLink to="/">FindYourPitch</NavLink>
          </Navbar.Brand>
        </Nav>
        <Nav>
          {loginOrLogout()}
        </Nav>
      </Container>
    </Navbar>
  )
}