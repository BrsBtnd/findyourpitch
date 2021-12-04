import { useState } from "react";
import { Form, FormControl, Button, FloatingLabel, Container } from "react-bootstrap";
import PitchPreview from "../pitchPreview/PitchPreview";
import apiServerUrl from "../../apiServerUrl";

export default function pitchSearch() {

  const [pitches, setPitches] = useState([]);

  function handleSearch(event) { 

    if(event.target.value.length !== 0) {
      fetch(`${apiServerUrl}/pitches/search/${event.target.value}`)
    .then(response => response.json())
    .then(data => setPitches(data));
  }
  setPitches([]);
}

  return(   
    <>
    <Container >
      <FloatingLabel controlId="floatingInput" label="Search for a pitch" className="d-flex">
        <FormControl type="search" placeholder="Search for a pitch" aria-label="Search" 
        onChange = {handleSearch}/>
        {/*<Button variant="outline-success" onClick={handleSearch}>Search</Button>*/ }
      </FloatingLabel>
    </Container>
    <Container>
      {pitches.map((pitch, index) => (
        <PitchPreview key={index} pitch={pitch}/>
      ))}
    </Container>
    </>
  );
}