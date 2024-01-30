import React, { useContext } from 'react';
import './ProductDisplay.css';
import star_icon from "../Assets/star_icon.png";
import star_dull_icon from "../Assets/star_dull_icon.png";
import { useParams, useNavigate } from 'react-router-dom';
import { ShopContext } from '../../Context/ShopContext';

const ProductDisplay = ({ product }) => {
    const { addToCart } = useContext(ShopContext);
    const navigate = useNavigate();
    if (!product) {
        return <p>Ładowanie danych...</p>;
    }

    const imagePath = `/product_${product.id}.png`;

    const handleAddToCart = async () => {
        const token = localStorage.getItem('accessToken');

        if (!token) {
            // Jeśli użytkownik nie jest zalogowany, przekieruj go do strony logowania
            navigate('/logowanie');
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/cart/add?id=${product.id}&quantity=1`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                credentials: 'include',
            });

            if (response.ok) {
                // Tutaj można dodać dodatkową logikę po pomyślnym dodaniu do koszyka, np. wyświetlić powiadomienie.
                console.log('Produkt został dodany do koszyka!');
            } else {
                console.error('Failed to add product to cart');
            }
        } catch (error) {
            console.error('Error adding product to cart:', error);
        }
    };

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
                    <div className="productdisplay-right-price-new">
                        {product.price} zł
                    </div>
                </div>
                <button onClick={handleAddToCart}>DODAJ DO KOSZYKA</button>
                <p className='productdisplay-right-category'><span>Category :</span> {product.category.name} </p>
            </div>
        </div>
    )
}

export default ProductDisplay;
