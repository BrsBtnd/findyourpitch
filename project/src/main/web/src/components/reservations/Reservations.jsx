import { useState, useEffect } from "react";
import apiServerUrl from "../../apiServerUrl";
import { useParams } from "react-router-dom";
import { Container, Card, ListGroup } from "react-bootstrap";
import Reservation from "../reservation/Reservation";

export default function reservations() {

  const reservationInit = {
    startDate: "",
    endDate: "",
    pitch: {
      pitchName:"",
      pitchType:"",
      country:"",
      region:"",
      city:""
    }
  }
  const [reservations, setReservations] = useState([]);

  const { userID } = useParams();

  useEffect(() => {
    fetch(`${apiServerUrl}/users/${userID}/pitches`)
      .then(response => response.json())
      .then(data => setReservations(data));
  },[]);

  return(
    
      <Container>
      {reservations.map((reservation, index) => (
        <Reservation key={reservation.calendarEventID} reservation={reservation}/>
      ))}
      </Container>    
  )
}