import React, { useState } from 'react';
import './CSS/LoginSignup.css';
import { Link } from 'react-router-dom';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loginStatus, setLoginStatus] = useState(null);
  const [accessToken, setAccessToken] = useState('');

  const handleLogin = async () => {
    const data = { email, password };

    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      });

      if (response.ok) {
        const responseData = await response.json();
        const token = responseData.accessToken;

        // Obsługa poprawnej odpowiedzi
        setLoginStatus('success');
        setAccessToken(token);
        setEmail('');
        setPassword('');

        // Przechowywanie tokenu w localStorage
        localStorage.setItem('accessToken', token);
      } else {
        // Obsługa błędnej odpowiedzi
        setLoginStatus('error');
      }
    } catch (error) {
      console.error('Wystąpił błąd podczas wysyłania żądania', error);
      setLoginStatus('error');
    }
  };

  return (
      <div className='loginsignup'>
        <div className="loginsignup-container">
          <h1>Zaloguj się</h1>
          {loginStatus === 'success' && <p className="success-message">Logowanie udane!</p>}
          {loginStatus === 'error' && <p className="error-message">Błąd logowania. Spróbuj ponownie.</p>}
          <div className="loginsignup-fields">
            <input
                type="email"
                placeholder='Adres Email'
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <input
                type="password"
                placeholder='Hasło'
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button onClick={handleLogin}>Kontynuuj</button>
          <p className="loginsignup-login">Nie masz konta? <Link style={{ textDecoration: 'none' }} to={'/login'}><span>Zarejestruj się</span></Link></p>
          <div className="loginsignup-agree">
            <input type="checkbox" name='' id='' />
            <p>Kontynuując, zgadzam się z polityką prywatności i warunkami użytkowania </p>
          </div>
        </div>
      </div>
  );
}

export default Login;
