import React from 'react'
import './CSS/LoginSignup.css'
import { Link } from 'react-router-dom'

const Login = () => {
  return (
    <div className='loginsignup'>
      <div className="loginsignup-container">
        <h1>Zaloguj się</h1>
        <div className="loginsignup-fields">

          <input type="email" placeholder='Adres Email' />
          <input type="password" placeholder='Hasło'/>
        </div>
        <button>Kontynuuj</button>
        <p className="loginsignup-login">Nie masz konta? <Link style={{textDecoration: 'none'}} to={'/login'}><span>Zarejestruj się</span></Link></p>
        <div className="loginsignup-agree">
          <input type="checkbox" name='' id='' />
          <p>Kontynuując, zgadzam się z polityką prywatności i warunkami użytkowania </p>
        </div>
      </div>
      
    </div>
  )
}

export default Login