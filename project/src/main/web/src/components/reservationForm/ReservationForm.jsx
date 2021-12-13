import { Card, FloatingLabel, Form, Button, Container  } from "react-bootstrap"
import { useState } from "react";
import "./ReservationForm.css";
import apiServerUrl from "../../apiServerUrl";

export default function reservationForm(props) {
  
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");  
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");
  const [reservation, setReservation] = useState(false);

  const userInfo = JSON.parse(localStorage.getItem("userTokenInfo"));

  function reservationFormHandler() {
    
    const reqBody = {
      startDate: `${startDate} ${startTime}:00`,
      endDate: `${endDate} ${endTime}:00`,
      user: userInfo.userID,
      pitch: props.pitchID
    }

    fetch(`${apiServerUrl}/users/${userInfo.userID}/pitches`, {
      method: "POST", 
      mode: "cors",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${userInfo.jwtToken}`
      },
      body: JSON.stringify(reqBody)
    })
    .then(response => response.json())
    .then((data) => {
      setReservation(true);
      //navigate(location.pathname);
    });
  }
  return(
    <Container className="reservation-form-container">
    <Card style={{width: "60%"}} >
      <Card.Body>
        <Card.Title >Make your reservation on this pitch!</Card.Title> 
          
        { reservation ? 
        <Card.Subtitle className="text-success create-account-form-subtitle">
          Successfull reservation!
        </Card.Subtitle> : <></> }

          <FloatingLabel controlId="floatingStartDate" label="Event start date">
          <Form.Control type="date" placeholder="Event start date" className="reservation-form-control" 
          value={startDate}
          onChange={(event) => {setStartDate(event.target.value)}}/>
          </FloatingLabel>

          <FloatingLabel controlId="floatingStartTime" label="Event start time">
          <Form.Control type="time" placeholder="Event start time" className="reservation-form-control"
          value={startTime}
          onChange={(event) => {setStartTime(event.target.value)}}/>
          </FloatingLabel>

          <FloatingLabel controlId="floatingEndDate" label="Event end date">
          <Form.Control type="date" placeholder="Event end date" className="reservation-form-control"
          value={endDate}
          onChange={(event) => {setEndDate(event.target.value)}}/>
          </FloatingLabel>

          <FloatingLabel controlId="floatingEndTime" label="Event end time">
          <Form.Control type="time" placeholder="Event end time" className="reservation-form-control"
          value={endTime}
          onChange={(event) => {setEndTime(event.target.value)}}/>
          </FloatingLabel>

          <Button variant="outline-success" onClick={reservationFormHandler}>Reserve</Button>
      </Card.Body>
    </Card>
    </Container>
  )
}
