import React, { useState } from 'react';
import './CSS/LoginSignup.css';
import { Link } from 'react-router-dom';

const LoginSignup = () => {
  const [formData, setFormData] = useState({
    firstname: '',
    lastname: '',
    email: '',
    password: '',
    phoneNumber: '',
    street: '',
    city: '',
    postalCode: '',
  });

  const [feedback, setFeedback] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleRegistration = async () => {
    try {
      const response = await fetch('http://localhost:8080/registration', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        setFeedback('Rejestracja zakończona sukcesem!');
        setFormData({
          firstname: '',
          lastname: '',
          email: '',
          password: '',
          phoneNumber: '',
          street: '',
          city: '',
          postalCode: '',
        });
      } else {
        const errorData = await response.json();
        setFeedback(`Błąd podczas rejestracji: ${errorData.message}`);
      }
    } catch (error) {
      setFeedback('Błąd podczas wysyłania żądania');
      console.error('Błąd podczas wysyłania żądania', error);
    }
  };

  return (
      <div className='loginsignup'>
        <div className="loginsignup-container">
          <h1>Zarejestruj się</h1>
          {feedback && <p className="feedback">{feedback}</p>}
          <div className="loginsignup-fields">
            <input
                type="text"
                placeholder='Imię'
                name="firstname"
                value={formData.firstname}
                onChange={handleChange}
            />
            <input
                type="text"
                placeholder='Nazwisko'
                name="lastname"
                value={formData.lastname}
                onChange={handleChange}
            />
            <input
                type="email"
                placeholder='Adres Email'
                name="email"
                value={formData.email}
                onChange={handleChange}
            />
            <input
                type="password"
                placeholder='Hasło'
                name="password"
                value={formData.password}
                onChange={handleChange}
            />
            <input
                type="text"
                placeholder='Numer telefonu'
                name="phoneNumber"
                value={formData.phoneNumber}
                onChange={handleChange}
            />
            <input
                type="text"
                placeholder='Ulica'
                name="street"
                value={formData.street}
                onChange={handleChange}
            />
            <input
                type="text"
                placeholder='Miasto'
                name="city"
                value={formData.city}
                onChange={handleChange}
            />
            <input
                type="text"
                placeholder='Kod pocztowy'
                name="postalCode"
                value={formData.postalCode}
                onChange={handleChange}
            />
          </div>
          <button onClick={handleRegistration}>Kontynuuj</button>
          <p className="loginsignup-login">
            Masz już konto?
            <Link style={{ textDecoration: 'none' }} to='/logowanie'>
              <span>Zaloguj się</span>
            </Link>
          </p>
        </div>
      </div>
  );
};

export default LoginSignup;
