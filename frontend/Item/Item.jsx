import React from 'react'
import './Item.css'
import { Link } from 'react-router-dom'

const Item = (props) => {
  return (
    <div className='item'>
      <div className="item-photo">
      <Link to={`/product/${props.id}`}><img onClick={window.scrollTo(0,0)} src={props.image} alt="" /></Link>
      </div>
      <Link style={{textDecoration: 'none'}} to={`/product/${props.id}`}> <p>{props.name}</p></Link>
        <div className="item-prices">
            <div className="item-prices-new">
                {props.new_price} zł
            </div>
            <div className="item-price-old">
                {props.old_price} zł
            </div>
        </div>
    </div>
  )
}

export default Item