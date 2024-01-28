import React from 'react'
import './Hero.css'
import hand_icon from '../Assets/hand_icon.png'
import arrow_icon from '../Assets/arrow.png'
import { Link } from 'react-router-dom'


const Hero = () => {
  return (
    <div className='hero'>
        <div className="hero-left">
            <h2>Najnowsze Technologie</h2>
            <div>
                <div className="hero-hand-icon">
                    <p>Odkryj nowy wymiary technologii</p>
                </div>
            </div>
            <div className="hero-latest-btn">
            <Link style={{textDecoration: 'none'}} to={`/product/6`}> <button>Sprawdź</button> <img src={arrow_icon} alt=""/></Link>
               
            </div>
        </div>

        <div className="hero-right">
         <img src={hand_icon} alt=""/>
        </div>
    </div>
  )
}

export default Hero