import React from 'react';
import './Breadcrum.css';

const Breadcrum = ({ product }) => {
    return (
        <div className='breadcrum'>
            {product && product.category && (
                <p>{product.category.name} / {product.name}</p>
            )}
        </div>
    );
}

export default Breadcrum;