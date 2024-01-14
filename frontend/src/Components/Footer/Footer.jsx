import React from 'react'
import './Footer.css'
import footer_logo from '../Assets/logo.png'
import instagram_icon from '../Assets/instagram_icon.png'
import facebook_icon from '../Assets/facebook_icon.png'
import yt_icon from '../Assets/yt_icon.png'


const Footer = () => {
  return (
    <div className='footer'>
        <div className="footer-logo">
            <img src={footer_logo} alt="" />
            <p>PCenter</p>
        </div>
        <ul className="footer-links">
            <li>Tekst</li>
            <li>Tekst</li>
            <li>Tekst</li>
            <li>Tekst</li>
            <li>Tekst</li>
        </ul>
        <div className="footer-social-icon">
            <div className="footer-icons-container">
                <img src={instagram_icon} alt="" />
            </div>
            <div className="footer-icons-container">
                <img src={facebook_icon} alt="" />
            </div>
            <div className="footer-icons-container">
                <img src={yt_icon} alt="" />
            </div>
        </div>
        <div className="footer-copyright">
            <hr />
            <p>Copyright @ 2024 Wszystgkie prawa zastrzeżone</p>
        </div>
    </div>
  )
}

export default Footer