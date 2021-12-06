import { Container, Navbar, Nav, NavLink } from "react-bootstrap";

export default function NavBar() {
  return (
    <Navbar>
      <Container>
        <Nav variant="tabs">
          <Navbar.Brand >
            <NavLink to="/">FindYourPitch</NavLink>
          </Navbar.Brand>
        </Nav>
        <Nav>
          <NavLink to="/login" disabled >Login</NavLink>
        </Nav>
      </Container>
    </Navbar>
  )
}