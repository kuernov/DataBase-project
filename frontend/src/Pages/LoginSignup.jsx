import React, { useState } from 'react';
import './CSS/LoginSignup.css';
import { Link } from 'react-router-dom';

const LoginSignup = () => {
  const [formData, setFormData] = useState({
    firstname: '',
    email: '',
    password: '',
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
          email: '',
          password: '',
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
          </div>
          <button onClick={handleRegistration}>Kontynuuj</button>
          <p className="loginsignup-login">
            Masz już konto?
            <Link style={{textDecoration: 'none'}} to='/logowanie'>
              <span>Zaloguj się</span>
            </Link>
          </p>
          <div className="loginsignup-agree">
            <input type="checkbox" name='' id='' />
            <p>Kontynuując, zgadzam się z polityką prywatności i warunkami użytkowania</p>
          </div>
        </div>
      </div>
  );
};

export default LoginSignup;
