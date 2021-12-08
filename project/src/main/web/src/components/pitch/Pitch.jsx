import { useEffect, useState } from "react";
import apiServerUrl from "../../apiServerUrl";
import { useParams } from "react-router-dom";
import { Card, Container, FloatingLabel, Form, ListGroup, Button } from "react-bootstrap";
import CreateAccountForm from "../createAccountForm/CreateAccountForm";
import ReservationForm from "../reservationForm/ReservationForm";
import './Pitch.css';

function Pitch(props) {

  const pitchInit = {
    pitchName: "",
    user: {firstName: "", lastName: ""},
    country: "",
    region: "",
    city: "",
    pitchType: ""
  }
  const [pitch, setPitch] = useState(pitchInit);
  const { pitchID } = useParams();

  useEffect(() => {
    fetch(`${apiServerUrl}/pitches/${Number(pitchID)}`)
      .then(response => response.json())
      .then(data => setPitch(data));
  },[]);

  return( 
    <>
    <Container>
      <Card border="success">
        <Card.Body>
          <Card.Title variant="success">{pitch.pitchName}</Card.Title>
          <Card.Subtitle className="pitch-card-subtitle">{`Pitch owner: ${pitch.user.firstName} ${pitch.user.lastName}`} </Card.Subtitle>
          <Card.Subtitle className="pitch-card-subtitle">{`Pitch type: ${pitch.pitchType}`} </Card.Subtitle>
            <ListGroup>
              <ListGroup.Item variant="success">Country: {pitch.country}</ListGroup.Item>
              <ListGroup.Item variant="success">Region: {pitch.region}</ListGroup.Item>
              <ListGroup.Item variant="success">City: {pitch.city}</ListGroup.Item>
            </ListGroup>
        </Card.Body>
      </Card>
    </Container>

    {!localStorage.getItem("userTokenInfo") ? <CreateAccountForm/> : <ReservationForm key={pitchID} pitchID={pitchID} />}
    
    </>
  );
}

export default Pitch;