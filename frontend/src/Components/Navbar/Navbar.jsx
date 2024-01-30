import React, { useContext, useState } from 'react'
import './Navbar.css'
import logo from '../Assets/logo.jpg'
import cart_icon from '../Assets/cart_icon.png'
import { Link } from 'react-router-dom'
import { ShopContext } from '../../Context/ShopContext'
const Navbar = () => {

  const [menu,setMenu] = useState("str");
  const {getTotalCartItems} = useContext(ShopContext);
    const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('accessToken')); // Dodany stan zalogowaniaconst { getTotalCartItems } = useContext(ShopContext);
    const handleLogout = () => {
        // Dodaj logikę do wylogowania użytkownika
        // Na przykład, zresetuj stan zalogowania i usuń token z localStorage
        setIsLoggedIn(false);
        localStorage.removeItem('accessToken');
    };

  return (
    <div className='navbar'>
        <div className="nav-logo">
            <Link style={{textDecoration: 'none'}} to='/'><img src={logo} alt= ""/></Link> 
            <Link style={{textDecoration: 'none'}} to='/'><p>PCenter</p></Link> 
        </div>
        <ul className="nav-menu">
            <li onClick={() => {setMenu("obudowy")}}><Link style={{textDecoration: 'none'}} to='/obudowy'><p>Obudowy</p></Link> {menu==="obudowy"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("kartygraficzne")}}><Link style={{textDecoration: 'none'}} to='/KartyGraficzne'><p>Karty Graficzne</p></Link>{menu==="kartygraficzne"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("procesory")}}><Link style={{textDecoration: 'none'}} to='/Procesory'><p>Procesory</p></Link>{menu==="procesory"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("plytyglowne")}}><Link style={{textDecoration: 'none'}} to='/PłytyGłówne'><p>Płyty Główne</p></Link>{menu==="plytyglowne"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("zasilacze")}}><Link style={{textDecoration: 'none'}} to='/Zasilacze'><p>Zasilacze</p></Link>{menu==="zasilacze"?<hr/>:<></>}</li>
        </ul>
        <div className="nav-login-cart">
            {/* Zmieniony warunek renderowania przycisku Login/Logout */}
            {isLoggedIn ? (
                <button onClick={handleLogout}>Logout</button>
            ) : (
                <Link to='/Login'>
                    <button>Login</button>
                </Link>
            )}
            <Link to='/Koszyk'><img src={cart_icon} alt=""/></Link>
        </div>
    </div>
  )
}

export default Navbar