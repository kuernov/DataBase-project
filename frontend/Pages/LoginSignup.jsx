import React from 'react'
import './CSS/LoginSignup.css'
import { Link } from 'react-router-dom'

const LoginSignup = () => {
  return (
    <div className='loginsignup'>
      <div className="loginsignup-container">
        <h1>Zarejestruj się</h1>
        <div className="loginsignup-fields">
          <input type="text" placeholder='Imię' />
          <input type="email" placeholder='Adres Email' />
          <input type="password" placeholder='Hasło'/>
        </div>
        <button>Kontynuuj</button>
        <p className="loginsignup-login">Masz już konto? <Link style={{textDecoration: 'none'}} to='/logowanie'><span>Zaloguj się</span></Link></p>
        <div className="loginsignup-agree">
          <input type="checkbox" name='' id='' />
          <p>Kontynuując, zgadzam się z polityką prywatności i warunkami użytkowania </p>
        </div>
      </div>
      
    </div>
  )
}

export default LoginSignup