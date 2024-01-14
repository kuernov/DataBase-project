import React from 'react'
import './Hero.css'
import hand_icon from '../Assets/hand_icon.png'
import arrow_icon from '../Assets/arrow.png'


const Hero = () => {
  return (
    <div className='hero'>
        <div className="hero-left">
            <h2>Tekst na stronie głównej</h2>
            <div>
                <div className="hero-hand-icon">
                    <p>new</p>
                    <img src={hand_icon} alt=""/>
                </div>
                <p>Tekst na stronie głównej</p>
                <p>Tekst na stronie głównej</p>
            </div>
            <div className="hero-latest-btn">
                <div>Sale</div>
                <img src={arrow_icon} alt=""/>
            </div>
        </div>

        <div className="hero-right">
         <img src={hand_icon} alt=""/>
        </div>
    </div>
  )
}

export default Hero