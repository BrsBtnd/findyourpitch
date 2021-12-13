import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import NavBar from './components/navBar/NavBar';
import PitchSearch from './components/pitchSearch/PitchSearch';
import Pitch from './components/pitch/Pitch';
import Reservations from './components/reservations/Reservations';
import Users from './components/users/Users';

import { useEffect } from 'react';

export default function App() {

  return (
    <BrowserRouter>
      <NavBar/>
      <main>
        <Routes>
          <Route exact path="/" element={<PitchSearch />}/>
          <Route exact path="/api/pitches" element={<PitchSearch />}/>
          <Route path="api/pitches/:pitchID" element={<Pitch/>} />
          <Route path="api/users/:userID/pitches" element={<Reservations/>} />
          {<Route path="api/users/:userID" element={<Users/>} />}
        </Routes>
      </main>
    </BrowserRouter>
  );
}