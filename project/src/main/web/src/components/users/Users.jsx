import { useEffect, useState } from "react"
import apiServerUrl from "../../apiServerUrl";
import { useParams } from "react-router-dom";


export default function users() {

  const [users, setUsers] = useState([]);
  const { userID } = useParams();
  const userInfo = JSON.parse(localStorage.getItem("userTokenInfo")); 

  useEffect(() => {
    fetch(`${apiServerUrl}/users/${userID}`, {
      method: "GET",
      mode: "cors",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${userInfo.jwtToken}`
      },
    })
      .then(response => response.json())
      .then(data => {console.log(data); setUsers(data)});
  }, [])

  return(
    <>
    {console.log(users)}
    </>
  )
}