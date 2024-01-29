import React, {useContext, useEffect, useState} from 'react'
import './DescriptionBox.css'
import {ShopContext} from "../../Context/ShopContext";
import {useParams} from "react-router-dom";

const DescriptionBox = () => {
    const [product, setProduct] = useState(null);
    const { productId } = useParams();

    useEffect(() => {
        // Pobieranie danych z backendu
        fetch(`http://localhost:8080/products/${productId}`)
            .then(response => response.json())
            .then(data => setProduct(data))
            .catch(error => console.error('Błąd podczas pobierania danych z backendu:', error));
    }, [productId]);

    if (!product) {
        return <p>Ładowanie danych...</p>;
    }
  return (
    <div className='descriptionbox'>
        <div className="descriptionbox-navigator">
            <div className="descriptionbox-nav-box">
                Opis
            </div>
            <div className="descriptionbox-nav-box fade">
                Opinie (122)
            </div>
        </div>
        <div className="descriptionbox-description">
            <p> {product.description} </p>

        </div>
    </div>
  )
}

export default DescriptionBox