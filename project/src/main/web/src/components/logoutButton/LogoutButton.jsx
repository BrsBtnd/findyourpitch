import { Button } from "react-bootstrap" 
import { useNavigate, useLocation } from "react-router-dom";
export default function logoutButton() {

  const navigate = useNavigate();
  const location = useLocation();

  function logout() {
    localStorage.removeItem("userTokenInfo");
    navigate(location.pathname);
  }

  return (
    <Button variant="outline-success" onClick={logout}>Logout</Button>
  )
}