import React, { useState } from 'react'
import './CSS/LoginSignup.css'
import { Link } from 'react-router-dom'

const LoginSignup = () => {

  const [state,setState] = useState("Zaloguj się");

  const handleLinkClick = () => {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const [formData, setFormData] = useState({
    firstname: "",
    lastname: "",
    email: "",
    password: "",
    phoneNumber: "",
    street: "",
    city: "",
    postalCode: ""
  });

  const [feedback, setFeedback] = useState(null);

  const handleChange = (e) => {
    setFormData({...formData, [e.target.name]: e.target.value });
  };

  const handleRegistration = async () => {
    console.log("Signup dziala",formData);
    let responseData;
    try {
        const response = await fetch('http://localhost:8080/registration', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      }).then((response) => response.json()).then((data)=>responseData = data)

      if (responseData.success) {
        localStorage.setItem('auth-token',responseData.token);
        window.location.replace("/");
        setFeedback('Rejestracja zakończona sukcesem!');
      } else {
        const errorData = await response.json();
        setFeedback(`Błąd podczas rejestracji: ${errorData.message}`);
      }
    } catch (error) {
      setFeedback('Błąd podczas wysyłania żądania');
      console.error('Błąd podczas wysyłania żądania', error);
    }
  };

  const handleLogin = async () => {
    console.log("Login dziala",formData);
     let responseData;
    try {
        const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      }).then((response) => response.json()).then((data)=>responseData = data)

      if (responseData.success) {
        localStorage.setItem('auth-token',responseData.token);
        window.location.replace("/");
        setFeedback('Logowanie zakończone sukcesem!');
      } else {
        const errorData = await response.json();
        setFeedback(`Błąd podczas Logowania: ${errorData.message}`);
      }
    } catch (error) {
      setFeedback('Błąd podczas wysyłania żądania');
      console.error('Błąd podczas wysyłania żądania', error);
    }
  };



  return (
    <div className='loginsignup'>
      <div className="loginsignup-container">
        <h1>{state}</h1>
        <div className="loginsignup-fields">
          {state==="Zarejestruj się"?<input name='firstname' value={formData.firstname} onChange={handleChange} type="text" placeholder='Imię' />:<></>}
          {state==="Zarejestruj się"?<input name='lastname' value={formData.lastname} onChange={handleChange} type="text" placeholder='Nazwisko' />:<></>}
          <input  name='email' value={formData.email} onChange={handleChange} type="email" placeholder='Adres Email' />
          <input name='password' value={formData.password} onChange={handleChange} type="password" placeholder='Hasło'/>
          {state==="Zarejestruj się"?<input name='phoneNumber' value={formData.phoneNumber} onChange={handleChange} type="text" placeholder='Numer Telefonu' />:<></>}
          {state==="Zarejestruj się"?<input name='street' value={formData.street} onChange={handleChange} type="text" placeholder='Ulica'/>:<></>}
          {state==="Zarejestruj się"?<input name='city' value={formData.city} onChange={handleChange} type="text" placeholder='Miasto' />:<></>}
          {state==="Zarejestruj się"?<input name='postalCode' value={formData.postalCode} onChange={handleChange} type="text" placeholder='Kod Pocztowy'/>:<></>}
        </div>
        <button onClick={()=>{state==="Zaloguj się"?handleLogin():handleRegistration()}}>Kontynuuj</button>
        {state==="Zarejestruj się"?<p className="loginsignup-login">Masz już konto? <span onClick={()=>{setState("Zaloguj się")}}>    Zaloguj się</span></p>
        :<p className="loginsignup-login">Nie posiadasz konta? <span onClick={()=>{setState("Zarejestruj się")}}> Zarejestruj się</span></p>}
        <div className="loginsignup-agree">
          <input type="checkbox" name='' id='' />
          <p>Kontynuując, zgadzam się z polityką prywatności i warunkami użytkowania </p>
        </div>
      </div>
      
    </div>






     
  )
}

export default LoginSignup