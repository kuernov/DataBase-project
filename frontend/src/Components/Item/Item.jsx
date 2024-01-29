import React from 'react';
import './Item.css';
import { Link } from 'react-router-dom';

const Item = (props) => {
    // Użyj props.imageURL jako źródła obrazu

    const imagePath = `/product_${props.id}.png`;
    console.log('Image path:', imagePath);
    return (
        <div className='item'>
            <div className="item-photo">
                <Link to={`/product/${props.id}`}>
                    <img onClick={() => window.scrollTo(0, 0)} src={process.env.PUBLIC_URL + imagePath} alt="" />
                </Link>
            </div>
            <Link style={{ textDecoration: 'none' }} to={`/product/${props.id}`}>
                <p>{props.name}</p>
            </Link>
            <div className="item-prices">
                <div className="item-prices-new">
                    {props.price} zł
                </div>
            </div>
        </div>
    );
}

export default Item;
