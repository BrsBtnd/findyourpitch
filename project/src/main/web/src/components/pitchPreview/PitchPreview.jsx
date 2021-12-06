import Card from 'react-bootstrap/Card';
import './PitchPreview.css';
import { Link } from 'react-router-dom';
import { Button, Container, Row } from 'react-bootstrap'

export default function Pitch(props) {
  
  return (
      <Card className="pitch-card">
        <Card.Body>
          
          <Card.Title>{props.pitch.pitchName}</Card.Title>
          <Card.Subtitle className="pitch-card-subtitle">{`Pitch type: ${props.pitch.pitchType}` }</Card.Subtitle>
          
           
          <Link to={`/api/pitches/${props.pitch.pitchID}`} className="pitch-card-link"> 
            <Button variant="outline-success"  >View Pitch</Button>
          </Link>
          </Card.Body>
      </Card>
  )
}