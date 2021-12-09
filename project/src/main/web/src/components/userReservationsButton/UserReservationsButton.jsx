import { useState } from "react";
import { Button, Container } from "react-bootstrap";
import "./UserReservationsButton.css";
import apiServerUrl from "../../apiServerUrl";
import { useNavigate, useLocation } from "react-router-dom";

export default function userReservationsButton() {

  const userInfo = JSON.parse(localStorage.getItem("userTokenInfo"));

  const navigate = useNavigate();
  const location = useLocation();

  function reservations() {
    navigate(`api/users/${userInfo.userID}/pitches`);
  }
  return (

    <Container>
    <Button className="user-reservations-button" variant="outline-success" onClick={reservations}>My reservations</Button>
    </Container>  
    
  )
}