import { useEffect } from "react";
import { Card, ListGroup } from "react-bootstrap";
import "./Reservation.css";

export default function reservation(props) {

  const splittedStartDate = props.reservation.startDate.split("T");
  const splittedEndDate = props.reservation.endDate.split("T");
  return(
    <Card border="success" className="reservation-card">
      <Card.Body>
        <Card.Title variant="success" className="text-success">
          Reservation date: from {`${splittedStartDate[0]} ${splittedStartDate[1]}`} to {`${splittedEndDate[0]} ${splittedEndDate[1]}`} at pitch {props.reservation.pitch.pitchName}

          </Card.Title>
        <Card.Subtitle className="pitch-card-subtitle">{`Pitch type: ${props.reservation.pitch.pitchType}`} </Card.Subtitle>
          <ListGroup>
            <ListGroup.Item variant="success">Country: {props.reservation.pitch.country}</ListGroup.Item>
            <ListGroup.Item variant="success">Region: {props.reservation.pitch.region}</ListGroup.Item>
            <ListGroup.Item variant="success">City: {props.reservation.pitch.city}</ListGroup.Item>
          </ListGroup>
      </Card.Body>
    </Card>
  )
}