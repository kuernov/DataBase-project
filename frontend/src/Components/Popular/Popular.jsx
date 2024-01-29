import React, { useState, useEffect } from 'react';
import './Popular.css';
import Item from '../Item/Item';

const Popular = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        // Pobieranie danych z serwera
        const fetchData = async () => {
            try {
                const response = await fetch('http://localhost:8080/products/1'); // Pobierz pierwszy produkt
                const product1 = await response.json();

                const response2 = await fetch('http://localhost:8080/products/2'); // Pobierz drugi produkt
                const product2 = await response2.json();

                const response3 = await fetch('http://localhost:8080/products/3'); // Pobierz trzeci produkt
                const product3 = await response3.json();

                const response4 = await fetch('http://localhost:8080/products/4'); // Pobierz czwarty produkt
                const product4 = await response4.json();

                setProducts([product1, product2, product3, product4]);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []); // Użycie pustej tablicy dependencies powoduje, że useEffect wykonuje się tylko raz po zamontowaniu komponentu

    return (
        <div className='popular'>
            <h1>Popularne</h1>
            <hr />
            <div className="popular-item">
                {products.map((item, i) => (
                    <Item key={i} id={item.id} name={item.name} image={item.image} price={item.price}  />
                ))}
            </div>
        </div>
    );
}

export default Popular;
