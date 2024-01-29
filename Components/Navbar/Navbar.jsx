import React, { useContext, useState } from 'react'
import './Navbar.css'
import logo from '../Assets/logo.jpg'
import cart_icon from '../Assets/cart_icon.png'
import { Link } from 'react-router-dom'
import { ShopContext } from '../../Context/ShopContext'
const Navbar = () => {

  const [menu,setMenu] = useState("str");
  const {getTotalCartItems} = useContext(ShopContext);
  
  return (
    <div className='navbar'>
        <div className="nav-logo">
            <Link style={{textDecoration: 'none'}} to='/'><img onClick={() => {setMenu("str")}} src={logo} alt= ""/></Link> 
            <Link style={{textDecoration: 'none'}} to='/'><p onClick={() => {setMenu("str")}} >PCenter</p></Link> 
        </div>
        <ul className="nav-menu">
            <li onClick={() => {setMenu("obudowy")}}><Link style={{textDecoration: 'none'}} to='/obudowy'><p>Obudowy</p></Link> {menu==="obudowy"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("kartygraficzne")}}><Link style={{textDecoration: 'none'}} to='/KartyGraficzne'><p>Karty Graficzne</p></Link>{menu==="kartygraficzne"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("procesory")}}><Link style={{textDecoration: 'none'}} to='/Procesory'><p>Procesory</p></Link>{menu==="procesory"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("plytyglowne")}}><Link style={{textDecoration: 'none'}} to='/PłytyGłówne'><p>Płyty Główne</p></Link>{menu==="plytyglowne"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("zasilacze")}}><Link style={{textDecoration: 'none'}} to='/Zasilacze'><p>Zasilacze</p></Link>{menu==="zasilacze"?<hr/>:<></>}</li>
        </ul>
        <div className="nav-login-cart">
          {localStorage.getItem('auth-token')
          ?<button onClick={() => {localStorage.removeItem('auth-token'); window.location.replace('/')}}>Wyloguj się</button>
          :<Link  to='/Login'><button>Zaloguj się</button></Link>}
           
            <Link  to='/Koszyk'><img src={cart_icon} alt="" /></Link>
            <div className="nav-cart-count">{getTotalCartItems()}</div>
        </div>
    </div>
  )
}

export default Navbar