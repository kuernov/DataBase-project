import React from 'react'
import './Offers.css'
import exclusive_image from '../Assets/exclusive_image.png'
import { Link } from 'react-router-dom'

const Offers = () => {
  return (
    <div className='offers'>
        <div className="offers-left">
            <h1>Najlepsza </h1>
            <h1>Oferta</h1>
            <p>Tylko z kodem promocyjnym</p>
            <Link style={{textDecoration: 'none'}} to={`/product/5`}> <button>Przejdź</button></Link>
        </div>
        <div className="offers-right">
            <img src={exclusive_image} alt="" />
        </div>
    </div>
  )
}

export default Offers