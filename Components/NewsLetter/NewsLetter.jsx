import React from 'react'
import './NewsLetter.css'

const NewsLetter = () => {
  return (
    <div className='newsletter'>
        <h1>Otrzymuj specjalne oferty na e-mail</h1>
        <p>Dołącz do newslettera</p>
        <div>
            <input type="email" placeholder='Your Email id'/>
            <button>Dołącz</button>
        </div>
    </div>
  )
}

export default NewsLetter