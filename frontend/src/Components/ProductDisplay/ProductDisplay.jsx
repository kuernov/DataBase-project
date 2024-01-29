import React, { useContext } from 'react';
import './ProductDisplay.css';
import star_icon from "../Assets/star_icon.png";
import star_dull_icon from "../Assets/star_dull_icon.png";
import { useParams } from 'react-router-dom';
import { ShopContext } from '../../Context/ShopContext';

const ProductDisplay = ({ product }) => {
    const { addToCart } = useContext(ShopContext);

    if (!product) {
        return <p>Ładowanie danych...</p>;
    }

    const imagePath = `/product_${product.id}.png`;

    return (
        <div className='productdisplay'>
            <div className="productdisplay-left">
                <div className="productdisplay-img">
                    <img className='productdisplay-main-img' src={process.env.PUBLIC_URL + imagePath} alt="" />
                </div>
            </div>
            <div className="productdisplay-right">
                <h1>{product.name}</h1>
                <div className="productdisplay-right-stars">
                    <img src={star_icon} alt="" />
                    <img src={star_icon} alt="" />
                    <img src={star_icon} alt="" />
                    <img src={star_icon} alt="" />
                    <img src={star_dull_icon} alt="" />
                    <p>(122)</p>
                </div>
                <div className="productdisplay-right-prices">
                    <div className="productdisplay-right-price-old">
                        {product.price} zł
                    </div>
                </div>
                <div className="productdisplay-right-description">
                    OPIS
                </div>
                <button onClick={() => { addToCart(product.id) }}>DODAJ DO KOSZYKA</button>
                <p className='productdisplay-right-category'><span>Category :</span> {product.category.name} </p>
            </div>
        </div>
    )
}

export default ProductDisplay;
