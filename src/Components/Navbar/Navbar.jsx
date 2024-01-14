import React, { useState } from 'react'
import './Navbar.css'
import logo from '../Assets/logo.png'
import cart_icon from '../Assets/cart_icon.png'
import { Link } from 'react-router-dom'
const Navbar = () => {

  const [menu,setMenu] = useState("str");
  
  return (
    <div className='navbar'>
        <div className="nav-logo">
            <img src={logo} alt= ""/>
            <p>PCenter</p>
        </div>
        <ul className="nav-menu">
            <li onClick={() => {setMenu("str")}}><Link style={{textDecoration: 'none'}} to='/'>Strona główna</Link> {menu==="str"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("1")}}><Link style={{textDecoration: 'none'}} to='/1'>podstrona1</Link>{menu==="1"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("2")}}><Link style={{textDecoration: 'none'}} to='/2'>podstrona2</Link>{menu==="2"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("3")}}><Link style={{textDecoration: 'none'}} to='/3'>podstrona3</Link>{menu==="3"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("4")}}><Link style={{textDecoration: 'none'}} to='/4'>podstrona4</Link>{menu==="4"?<hr/>:<></>}</li>
        </ul>
        <div className="nav-login-cart">
            <Link  to='/login'><button>Login</button></Link>
            <Link  to='/cart'><img src={cart_icon} alt="" /></Link>
            <div className="nav-cart-count">0</div>
        </div>
    </div>
  )
}

export default Navbar